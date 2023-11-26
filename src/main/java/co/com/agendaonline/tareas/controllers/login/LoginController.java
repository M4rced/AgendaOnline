package co.com.agendaonline.tareas.controllers.login;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	 @GetMapping
	    public String welcome() {
	        return "Welcome to Google !!";
	    }

	    @GetMapping("/user")
	    public Principal user(Principal principal) {
	        System.out.println("username : " + principal.getName());
	        return principal;
	    }
}
