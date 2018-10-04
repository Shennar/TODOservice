package TODOservice.init;

import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
// import org.joda.time.DateTimeZone;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.joda.time.DateTime;


@Component
public class DatabaseInit implements ApplicationRunner {
    private TODOServiceDAO todoServiceDAO;
    //private DateTimeFormatter formatter =  DateTimeFormat.forPattern("MM/dd/YYYY HH:mm:ss");
    @Autowired
    public DatabaseInit(TODOServiceDAO todoServiceDAO) {
        this.todoServiceDAO = todoServiceDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = todoServiceDAO.count();
        if (count == 0) {
            for (int i = 0; i < 7; i++) {
                TODOPost todoPost = new TODOPost();
                //DateTimeZone dZone = DateTimeZone.forID("Europe/Moscow");
                todoPost.setDatum(DateTime.now().toString());
                todoPost.setWhatTODO("Do the exercise #" + (i + 1) + "!");
                todoPost.setDoneStatus(false);

                todoServiceDAO.save(todoPost);
            }
         /*   TODOPost todoPost = new TODOPost();
            todoPost.setDatum(DateTime.now());
            todoPost.setWhatTODO("Do the exercise #" + 7 + "!");
            todoPost.setDoneStatus(false);
            todoServiceDAO.save(todoPost); */
        }
    }
}

