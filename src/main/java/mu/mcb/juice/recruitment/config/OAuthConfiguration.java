//package mu.mcb.juice.recruitment.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
///**
// * Brume
// */
//@Configuration
//@EnableAuthorizationServer
//@RequiredArgsConstructor
//@Order(1)
//public class OAuthConfiguration implements AuthorizationServerConfigurer {
//    private final AuthenticationManager authenticationManager;
//    private final PasswordEncoder passwordEncoder;
//    private final UserServiceImpl userDetailsService;
//    @Value("${jwt.clientId}")
//    private String clientId;
//    @Value("${jwt.client-secret}")
//    private String clientSecret;
//    @Value("${jwt.authorizedGrantTypes}")
//    private String authorizedGrantTypes;
//    @Value("${jwt.refreshTokenValiditySeconds}")
//    private int refreshTokenValiditySeconds;
//    @Value("${jwt.accessTokenValiditySeconds}")
//    private int accessTokenValiditySeconds;
//    @Value("${security.signing-key}")
//    private String signingKey;
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(signingKey);
//        return converter;
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) {
//        security
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()")
//                .allowFormAuthenticationForClients() // (1)
//        ;
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//                .inMemory() // (2)
//                .withClient(clientId)
//                .secret(passwordEncoder.encode(clientSecret)) // (3)
//                .accessTokenValiditySeconds(accessTokenValiditySeconds)
//                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
//                .scopes("read", "write")
//                .resourceIds("university")// (4)
//                .autoApprove(true)
//                .authorizedGrantTypes(authorizedGrantTypes)
//        ;
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints
//                .authenticationManager(this.authenticationManager)
//                .accessTokenConverter(accessTokenConverter())
//                .userDetailsService(userDetailsService)
//                .tokenStore(tokenStore())
//        ;
//    }
//
//
//}