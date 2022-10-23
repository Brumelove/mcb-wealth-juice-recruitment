package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.mcb.juice.recruitment.dao.LoginRequest;
import mu.mcb.juice.recruitment.dao.UserDao;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.UserRepository;
import mu.mcb.juice.recruitment.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Brume
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository repository;
    private final JuiceMapper mapper;
    @Lazy @Autowired
    private  AuthenticationManager authenticationManager;


    private UserDao findByUserNameOrEmail(String userName, String email) {
        var optionalUser = repository.findByUserNameOrEmail(userName, email);
        if (optionalUser.isPresent()) return mapper.mapUserModelToDto(optionalUser.get());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with " + userName + "or " + email + " does not exists");
    }

    private UserDao getByUserNameOrEmail(String userName, String email) {
        var optionalUser = repository.findByUserNameOrEmail(userName, email);
        if (optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with " + userName + "or " + email + "  exists");
        return null;
    }

    @Override
    public UserDao create(UserDao userDao) {
        var oldUser = getByUserNameOrEmail(userDao.getUserName(), userDao.getEmail());
        return createUser(Objects.requireNonNullElse(oldUser, userDao));
    }

    private UserDao createUser(UserDao userDao) {
        var user = mapper.mapUserDtoToModelMapper(userDao);
        user.setPassword(new BCryptPasswordEncoder().encode(userDao.getPassword()));

        var newUser = repository.save(user);
        return mapper.mapUserModelToDto(newUser);
    }

    private mu.mcb.juice.recruitment.entity.User findById(Long id) {
        var optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) return optionalUser.get();
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with " + id + " does not exists");
    }

    @Override
    public List<UserDao> findAll() {
        return mapper.mapUserModelListToDto(repository.findAll());
    }

    @Override
    public UserDao update(UserDao userDao) {
        BeanUtils.copyProperties(userDao, mu.mcb.juice.recruitment.entity.User.class, "password");
        return create(userDao);
    }

    @Override
    public String delete(Long id) {

        var user = findById(id);
        repository.delete(user);

        return user.getUserName() + " Deleted Successfully";
    }


    @Override
    public UserDetails loadUserByUsername(String userName) {
        var userDto = findByUserNameOrEmail(userName, userName);
        if (userDto.getUserName().equals(userName)) {
            GrantedAuthority authority = new SimpleGrantedAuthority(userDto.getUserRole().name());

            return new User(userDto.getUserName(), userDto.getPassword(), Collections.singletonList(authority));
        } else throw new UsernameNotFoundException("User with " + userName + " not found");
    }


    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            log.info("Logging out " + username);

            new SecurityContextLogoutHandler().logout(request, response, authentication);

            log.info("User " + username + ": logout successful");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to logout the user", e);
        }
    }

    @Override
    public UserDao changePassword(String emailOrUsername, String password, String confirmPassword) {
        var user = findByUserNameOrEmail(emailOrUsername, emailOrUsername);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user with Email or Username " + emailOrUsername + " does not exists");
        }

        if (!StringUtils.hasText(password) || !StringUtils.hasText(confirmPassword) || !password.equalsIgnoreCase(confirmPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password do not match. Kindly recheck and try again");
        }

        return createUser(user);
    }

    public UserDetails login(HttpServletRequest request, LoginRequest login) {
        String username = login.getUserName();
        String password = login.getPassword();
        String remoteAddr = request.getRemoteAddr();

        log.info("Trying to login " + username + " from " + remoteAddr);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // if request is needed during authentication
        token.setDetails(new WebAuthenticationDetails(request));
        try {

            Authentication auth = authenticationManager.authenticate(token);

            log.info("Authorities for " + username + ": " + auth.getAuthorities().stream().map(Object::toString).collect(Collectors.joining(",")));

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            // if user has a http session we need to save the context in the session for next requests
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            log.info("User " + username + ": login successful");

            return (UserDetails) auth.getPrincipal();
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
