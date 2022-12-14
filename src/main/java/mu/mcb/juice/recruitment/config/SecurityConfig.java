//package mu.mcb.juice.recruitment.config;
//
//import lombok.RequiredArgsConstructor;
//import mu.mcb.juice.recruitment.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//
///**
// * Created by brume on 16/07/18.
// */
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserServiceImpl userDetailsService;
//    private final AuthEntryPointJwt authEntryPointJwt;
//
//    @Value("${security.signing-key}")
//    private String signingKey;
//
//    @Value("${security.security-realm}")
//    private String securityRealm;
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(userDetailsService);
//        return provider;
//    }
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider())
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(signingKey);
//        return converter;
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .headers().frameOptions().disable().and()
//                .authorizeRequests()
//                .antMatchers(
//                        "/v3/api-docs/**",
//                        "/swagger-ui/**",
//                        "university/swagger-ui/**",
//                        "/university/h2-ui/**",
//                        "/university/rest/users/login",
//                        "/university/rest/users/create")
//                .permitAll()
//                .antMatchers("/rest/**").authenticated()
////                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(authEntryPointJwt).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic()
//                .realmName(securityRealm);
//    }
//}
