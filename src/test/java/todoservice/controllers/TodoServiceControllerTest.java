package todoservice.controllers;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import todoservice.services.TodoPostResponse;
import todoservice.utils.ContextConfig;
import todoservice.utils.FakeDatabase;
import todoservice.web.dto.TodoPostDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContextConfig.class)
public class TodoServiceControllerTest {

    private String ERROR_MESSAGE_LAST_PART = " field must be filled. \n ";
    final private DozerBeanMapper mapper = new DozerBeanMapper();

    @Autowired
    private FakeDatabase fakeTodoServiceDAO;

    @Autowired
    private TodoServiceController restController;

    @Test
    public void whenSentGetRequest_allPostsShown() {
        List<TodoPostDto> actualRecords = restController.getAllPosts();
        final List<TodoPostDto> fakeDatabase = createFakeDatabase();
        assertEquals(actualRecords, fakeDatabase);
    }

    @Test
    public void whenCorrectPostDataSent_returnNewPost() {
        final TodoPostDto postDto = new TodoPostDto(1L, "Datum", "whatTODO", "Done");
        final TodoPostResponse expectedResponse = new TodoPostResponse(postDto, "OK");
        final TodoPostResponse createdResponse = restController.addTodoPost("Datum", "whatTODO", "Done");
        assertEquals(expectedResponse, createdResponse);
    }

    @Test
    public void whenCorrectDataSent_validatorReturnsNoErrors() {
        final TodoPostResponse createdResponse = restController.addTodoPost("Datum", "whatTODO", "Done");
        assertEquals("OK", createdResponse.getErrors());
    }

    @Test
    public void whenDatumMissed_validatorReturnsCorrectError() {
        final TodoPostResponse createdResponse = restController.addTodoPost("", "whatTODO", "Done");
        assertEquals("Date-time" + ERROR_MESSAGE_LAST_PART, createdResponse.getErrors());
    }

    @Test
    public void whenWhatTODOMissed_validatorReturnsCorrectError() {
        final TodoPostResponse createdResponse = restController.addTodoPost("Datum", "", "Done");
        assertEquals("Task" + ERROR_MESSAGE_LAST_PART, createdResponse.getErrors());
    }

    @Test
    public void whenDoneStatusMissed_validatorReturnsCorrectError() {
        final TodoPostResponse createdResponse = restController.addTodoPost("Datum", "whatTODO", "Bla");
        assertEquals("Only TO DO or Done is allowed for Status. \n", createdResponse.getErrors());
    }

    @Test
    public void whenCorrectIdSentToDelete_returnedStatus200() {
        final ResponseEntity<String> createdResponse = restController.deleteTodoPost(1L);
        assertEquals("Successfully deleted task with ID: ", createdResponse.getBody());
        assertEquals(HttpStatus.OK, createdResponse.getStatusCode());
    }

    @Test
    public void whenIncorrectIdSentToDelete_returnedStatus500() {
        final ResponseEntity<String> createdResponse = restController.deleteTodoPost(-1L);
        assertEquals("", createdResponse.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, createdResponse.getStatusCode());
    }

    @Test
    public void whenCorrectIdSentToChangeStatus_HttpStatus200Returned() {
        final ResponseEntity<String> createdResponse = restController.changeStatus(1);
        assertEquals(HttpStatus.OK, createdResponse.getStatusCode());
    }

    @Test
    public void whenIncorrectIdSentToChangeStatus_HttpStatus404Returned() {
        final ResponseEntity<String> createdResponse = restController.changeStatus(10);
        assertEquals(HttpStatus.NOT_FOUND, createdResponse.getStatusCode());
    }

    //
    // Auxiliary methods
    //

    private List<TodoPostDto> createFakeDatabase() {
        final List<TodoPostDto> fakeDatabase = new ArrayList<>();
        TodoPostDto postDto;
        for (int i = 0; i < 5; i++) {
            postDto = new TodoPostDto(1L, "Fakedate" + (i + 1), "Task " + (i + 1), "false");
            fakeDatabase.add(postDto);
        }
        return fakeDatabase;
    }
}
