package br.com.ifeira.auth.boundary;

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
public class LogoutBoundary {

    private final String TOKEN_PREFIX = "Bearer";

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    @GetMapping("/expire")
    public void logout(HttpServletRequest request) {
        String autorizacao = request.getHeader("Authorization");
        if (autorizacao.contains(this.TOKEN_PREFIX) ||
                autorizacao.contains(this.TOKEN_PREFIX.toLowerCase())) {
            String tokenId = autorizacao.split(" ")[1];
            System.out.println("Deslogando!!!" + tokenId);
            this.tokenServices.revokeToken(tokenId);
        }
    }

}
