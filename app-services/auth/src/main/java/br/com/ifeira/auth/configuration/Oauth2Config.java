package br.com.ifeira.auth.configuration;

import br.com.ifeira.auth.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {

    @Value("security.oauth2.client.cliend-id")
    private String clientId;

    @Value("security.oauth2.client.cliend-secret")
    private String clientSecret;

    @Value("security.oauth2.client.resource-id")
    private String resourceId;

    @Value("security.oauth2.client.acess-token-validity")
    private int accessTokenValidity;

    @Value("security.oauth2.client.scopes")
    private String[] scopes;

    @Value("security.oauth2.client.authorized-grant-types")
    private String[] authorizedGrantTypes;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore());
//                .userDetailsService();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        Stream<Object> roles = Arrays.stream(Roles.values()).map(roles1 -> roles1.name());
        String[] rolesArray = (String[]) roles.toArray();
        clients.inMemory()
                .withClient(this.clientId)
                .authorizedGrantTypes(this.authorizedGrantTypes)
                .authorities(rolesArray)
                .scopes(this.scopes)
                .accessTokenValiditySeconds(this.accessTokenValidity)
                .refreshTokenValiditySeconds(this.accessTokenValidity*2);
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
