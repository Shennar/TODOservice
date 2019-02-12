package todoservice.controllers;

import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import todoservice.dao.TodoServiceDao;
import todoservice.domain.TodoPost;
import todoservice.web.dto.TodoPostDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TodoServiceControllerTest {

    private List<TodoPost> fakeDatabase;
    final private DozerBeanMapper mapper = new DozerBeanMapper();

    @Autowired
    private TodoServiceDao todoServiceDAO;

    @Autowired
    TodoServiceController restController;



    @Before
    public void setUp() {
        createFakeDatabase();
    }

    @After
    public void tearDown() {
        fakeDatabase = null;
    }

    @Test
    public void whenSentGetRequest_allPostsShown() {
        when(todoServiceDAO.findAll()).thenReturn(fakeDatabase);
        List<TodoPostDto> expectedRecords = restController.getAllPosts();
        assertEquals(expectedRecords, fakeDatabase);
    }

    @Test
    public void whenCorrectPostDataSent_returnNewPost() {

    }

    @Test
    public void whenCorrectDataSent_validatorReturnsNoErrors() {

    }

    @Test
    public void whenDatumMissed_validatorReturnsCorrectError() {

    }

    @Test
    public void whenWhatTODOMissed_validatorReturnsCorrectError() {

    }

    @Test
    public void whenDoneStatusMissed_validatorReturnsCorrectError() {

    }

    @Test
    public void whenCorrectIdSentToDelete_recordDeleted() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectIdSentToDelete_exceptionThrown() {

    }

    @Test
    public void whenCorrectIdSentToChangeStatus_HttpStatus200Returned() {

    }

    @Test
    public void whenIncorrectIdSentToChangeStatus_HttpStatus404Returned() {

    }

    //
    // Auxiliary methods
    //

    private void createFakeDatabase() {
        fakeDatabase = new ArrayList<>();
//             fakeDatabase.add(new TodoPost(1L, "Fakedate1", "Task 1", false));
//        use mapper, create 2 lists;
    }

}
