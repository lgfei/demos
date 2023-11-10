package com.lgfei.demo.oauth2.resource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("order")
			//.tokenServices(tokenServices())
			.tokenStore(tokenStore)
			.stateless(true);
	}
	
//	private ResourceServerTokenServices tokenServices(){
//	    RemoteTokenServices tokenServices = new RemoteTokenServices();
//	    tokenServices.setClientId("c1");
//	    tokenServices.setClientSecret("secret");
//	    tokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:8070/oauth/check_token");
//	    return tokenServices;
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/order/**")
			.access("#oauth2.hasScope('all')")
			.and()
			.csrf().disable();
	}
	
}
