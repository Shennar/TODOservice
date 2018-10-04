package TODOservice;
import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class StartTODOserviceApp extends SpringBootServletInitializer {
    private TODOServiceDAO todoServiceDAO;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StartTODOserviceApp.class);
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartTODOserviceApp.class, args);
    }
}
