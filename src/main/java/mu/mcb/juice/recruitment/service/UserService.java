package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dao.LoginRequest;
import mu.mcb.juice.recruitment.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Brume
 **/
public interface UserService {
    UserDao create(UserDao userDao);
    List<UserDao> findAll();
    UserDao update(UserDao userDao);
    String delete(Long id);
     UserDetails login(HttpServletRequest request, LoginRequest login);
     void logout(HttpServletRequest request, HttpServletResponse response);
    UserDao changePassword(String emailOrUsername, String password, String confirmPassword);
}
