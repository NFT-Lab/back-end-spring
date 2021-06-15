package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Component; */
/*
@Configuration
//@EnableResourceServer
@EnableWebSecurity
public class ServerConfiguration extends /*ResourceServerConfigurerAdapter WebSecurityConfigurerAdapter  { 
     /*@Override
     public void configure(HttpSecurity http) throws Exception {         
         http
             .cors().and()
             .csrf().disable()
             .authorizeRequests().antMatchers("/**").permitAll();
     }
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        // ...
	        http.cors();
	    }
}
*/
/*
@Component
public class ServerConfiguration implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}*/