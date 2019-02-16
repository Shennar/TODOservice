package todoservice.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todoservice.controllers.TodoServiceController;

@Configuration
public class ContextConfig {

    @Bean
    public FakeDatabase fakeDatabase() {
        return new FakeDatabase();
    }

    @Bean
    public TodoServiceController todoServiceController(final FakeDatabase dataBase) {
        return new TodoServiceController(dataBase);
    }
}
