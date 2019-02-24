package todoservice.init;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import todoservice.domain.TodoPost;
import todoservice.utils.ContextConfig;
import todoservice.utils.FakeDatabase;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContextConfig.class)
public class DatabaseInitTest {

    @Autowired
    private FakeDatabase fakeTodoServiceDAO;

    @Autowired
    private DatabaseInit databaseInit;

    @Before
    public void setUp() {
        fakeTodoServiceDAO.save(null);
    }

    @Test
    public void whenDatabaseIsEmpty_createNewData() throws Exception {
        databaseInit.run(null);
        assertEquals(8, fakeTodoServiceDAO.getFakeRepository().size());
    }

    @Test
    public void whenDatabaseNotEmpty_DoNotAddData() throws Exception {
        final TodoPost post = new TodoPost();
        post.setId(1L);
        post.setWhatTODO("To do");
        post.setDatum("Datum");
        post.setDoneStatus(false);
        fakeTodoServiceDAO.save(post);
        databaseInit.run(null);
        assertEquals(1, fakeTodoServiceDAO.getFakeRepository().size());
    }
}
