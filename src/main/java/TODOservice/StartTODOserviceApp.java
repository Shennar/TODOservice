package TODOservice;
import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

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

  /*  @PostConstruct
    public void initdb() {

        long count = todoServiceDAO.count();
        if (count == 0) {
            for (int i = 0; i < 5; i++) {
                TODOPost todoPost = new TODOPost();
                todoPost.setDateTime(DateTime.parse("2018-05-05 10:11:12.123"));// Think about string for date-time
                todoPost.setWhatTODO("Do the excercise #" + (i + 1) + "!");
                todoPost.setDoneStatus(false);

                todoServiceDAO.save(todoPost);
            }
            TODOPost todoPost = new TODOPost();
            todoPost.setDateTime(DateTime.now());
            todoPost.setWhatTODO("Do the excercise #" + 6 + "!");
            todoPost.setDoneStatus(false);
            todoServiceDAO.save(todoPost);
        }
    }
    */
}
