package optimusinventory.api;

/**
 * Created by Acha Bill on 7/17/2017.
 */

import optimusinventory.api.auth.ITokenService;
import optimusinventory.api.dao.IUsersDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    private IUsersDao usersDao;
    private IHelpers helpers;
    private ITokenService tokenService;

    public Application(IUsersDao usersDao, IHelpers helpers, ITokenService tokenService) {
        this.usersDao = usersDao;
        this.helpers = helpers;
        this.tokenService = tokenService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    @Bean
    public Docket vodAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Optimus Inventory")
                .description("Rest API Optimus Inventory")
                .contact("achabill12@gmail.com")
                .version("1.0")
                .build();
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            List<User> userList = usersDao.findAll();
            if (userList == null || userList.size() == 0) {
                User root = new User();
                root.setUsername("admin");
                root.setCreatedOn(new Date());
                root.setEmail("admin@admin.com");
                root.setFirstName("admin");
                root.setLastName("admin");
                root.setPhoneNumber("679873401");
                root.setPassword(tokenService.digest("admin@123"));
                List<Privilege> privileges = helpers.getAllPrivileges();
                System.out.println(privileges);
                root.setPrivileges(privileges);
                usersDao.save(root);
            }
        };
    }


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("5048MB");
        factory.setMaxRequestSize("5048MB");
        return factory.createMultipartConfig();
    }
}
