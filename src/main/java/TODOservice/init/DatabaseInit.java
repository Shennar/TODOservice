package TODOservice.init;

import TODOservice.dao.TodoServiceDao;
import TODOservice.domain.TodoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.joda.time.DateTime;

@Component
public class DatabaseInit implements ApplicationRunner {
    private TodoServiceDao todoServiceDAO;

    @Autowired
    public DatabaseInit(TodoServiceDao todoServiceDAO) {
        this.todoServiceDAO = todoServiceDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = todoServiceDAO.count();
        if (count == 0) {
            for (int i = 0; i < 7; i++) {
                TodoPost todoPost = new TodoPost();
                String dateString = DateTime.now().toString();
                int index = dateString.indexOf(".");
                String datum = dateString.substring(0, index - 3).replace('T', ' ');
                todoPost.setDatum(datum);
                todoPost.setWhatTODO("Do the exercise #" + (i + 1) + "!");
                todoPost.setDoneStatus(false);

                todoServiceDAO.save(todoPost);
            }
            TodoPost todoPost = new TodoPost();
            String dateString = DateTime.now().toString();
            int index = dateString.indexOf(".");
            String datum = dateString.substring(0, index - 3).replace('T', ' ');
            todoPost.setDatum(datum);
            todoPost.setWhatTODO("Do the exercise #" + 8 + "!");
            todoPost.setDoneStatus(false);
            todoServiceDAO.save(todoPost);
        }
    }
}