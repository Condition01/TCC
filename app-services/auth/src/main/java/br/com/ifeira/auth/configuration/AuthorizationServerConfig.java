package br.com.ifeira.auth.configuration;

import br.com.ifeira.auth.enums.Role;
import br.com.ifeira.auth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.resource-id}")
    private String resourceId;

    @Value("${security.oauth2.client.acess-token-validity}")
    private int accessTokenValidity;

    @Value("${security.oauth2.client.scopes}")
    private String[] scopes;

    @Value("${security.oauth2.client.authorized-grant-types}")
    private String[] authorizedGrantTypes;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService ;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(this.userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String[] rolesArrayStrg = getRoles();
        clients.inMemory()
                .withClient(this.clientId)
                .secret(this.passwordEncoder.encode(this.clientSecret))
                .authorizedGrantTypes(this.authorizedGrantTypes)
                .authorities(rolesArrayStrg)
                .scopes(this.scopes)
                .accessTokenValiditySeconds(this.accessTokenValidity)
                .refreshTokenValiditySeconds(this.accessTokenValidity*2);
    }

    private String[] getRoles() {
        Role[] roleArrayObj = Role.values();
        String[] rolesArrayStrg = new String[roleArrayObj.length];
        int lenght = roleArrayObj.length;
        for(int i = 0; i < lenght; i++){
            rolesArrayStrg[i] = roleArrayObj[i].name();
        }
        return rolesArrayStrg;
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }
}
