package co.com.agendaonline.tareas.config.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	public void configure(HttpSecurity http) throws Exception {
	       http
           .authorizeRequests()
           .antMatchers("/tarea/**").authenticated() // Requiere autenticación para rutas relacionadas con TareaController
           .anyRequest().permitAll()
           .and()
       .oauth2Login()
           .loginPage("/login") // Puedes personalizar la página de inicio de sesión si es necesario
           .defaultSuccessUrl("/tarea/list") // Redirige a TareaController después de la autenticación
           .userInfoEndpoint()
             ;
	    }

}
