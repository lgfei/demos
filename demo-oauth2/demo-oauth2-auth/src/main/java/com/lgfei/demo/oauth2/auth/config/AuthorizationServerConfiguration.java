package com.lgfei.demo.oauth2.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class AuthorizationServerConfiguration  extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenStore tokenStore;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private JwtAccessTokenConverter accessTokenEnhancer;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
			.checkTokenAccess("permitAll()")
			.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("c1")
			.secret(new BCryptPasswordEncoder().encode("secret"))
			.resourceIds("order")
			.authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
			.scopes("all")
			.autoApprove(false)
			.redirectUris("https://www.lgfei.com");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
			.tokenServices(tokenServices())
			.userDetailsService(userDetailsService)
			.authorizationCodeServices(authorizationCodeServices())
			.allowedTokenEndpointRequestMethods(HttpMethod.POST);
	}
	
	AuthorizationServerTokenServices tokenServices() {
    	DefaultTokenServices tokenServices = new DefaultTokenServices();
    	tokenServices.setClientDetailsService(clientDetailsService);
    	tokenServices.setTokenStore(tokenStore);
    	tokenServices.setTokenEnhancer(accessTokenEnhancer);
    	tokenServices.setSupportRefreshToken(true);
    	return tokenServices;
    }
	
	AuthorizationCodeServices authorizationCodeServices() {
		return new InMemoryAuthorizationCodeServices();
	}

}
