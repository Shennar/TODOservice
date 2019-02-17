package todoservice.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todoservice.controllers.TodoServiceController;
import todoservice.init.DatabaseInit;

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

    @Bean
    public DatabaseInit databaseInit(final FakeDatabase database) {
        return new DatabaseInit(database);
    }
}
