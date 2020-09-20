package bd.edu.seu;

import bd.edu.seu.Backend.Model.Request;
import bd.edu.seu.Backend.Model.User;
import bd.edu.seu.Backend.Service.RequestService;
import bd.edu.seu.Backend.Service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class,args);
        UserService userService = run.getBean(UserService.class);
        RequestService requestService = run.getBean(RequestService.class);
        User user = new User();
        user.setId(0);
        userService.SaveUser(user);

        Request request = new Request();
        request.setId(0);
        requestService.SaveRequest(request);
    }

}
