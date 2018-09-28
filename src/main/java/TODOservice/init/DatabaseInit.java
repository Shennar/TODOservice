package TODOservice.init;

import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.joda.time.DateTime;

import javax.annotation.PostConstruct;


@Component
public class DatabaseInit implements ApplicationRunner {
    private TODOServiceDAO todoServiceDAO;

    @Autowired
    public DatabaseInit(TODOServiceDAO todoServiceDAO) {
        this.todoServiceDAO = todoServiceDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = todoServiceDAO.count();
        if (count == 0) {
            for (int i = 0; i < 5; i++) {
                TODOPost todoPost = new TODOPost();
                todoPost.setDatum(DateTime.now().toString());// parse("2018-05-05T10:11:12.123") Think about string for date-time
                todoPost.setWhatTODO("Do the excercise #" + (i + 1) + "!");
                todoPost.setDoneStatus(false);

                todoServiceDAO.save(todoPost);
            }
            TODOPost todoPost = new TODOPost();
            todoPost.setDatum(DateTime.now().toString());
            todoPost.setWhatTODO("Do the excercise #" + 6 + "!");
            todoPost.setDoneStatus(false);
            todoServiceDAO.save(todoPost);
        }
    }
}

