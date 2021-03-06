package todoservice.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import todoservice.dao.TodoServiceDao;
import todoservice.domain.TodoPost;
import todoservice.services.TodoPostResponse;
import todoservice.web.dto.TodoPostDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class TodoServiceControllerTest {

    private String ERROR_MESSAGE_LAST_PART = " field must be filled. \n ";

    private TodoServiceDao todoServiceDAO = mock(TodoServiceDao.class);

    private TodoServiceController restController = new TodoServiceController(todoServiceDAO);

    @Test
    public void whenSentGetRequest_allPostsShown() {
        when(todoServiceDAO.findAll()).thenReturn(expectedDatabaseContent());
        final List<TodoPostDto> actualRecords = restController.getAllPosts();
        final List<TodoPostDto> fakeDatabase = createFakeDatabase();
        assertEquals(actualRecords.toString(), fakeDatabase.toString());
    }

    @Test
    public void whenCorrectPostDataSent_returnNewPost() {
        final TodoPostDto postDto = new TodoPostDto(1L, "Datum", "whatTODO", "Done");
        final TodoPostResponse expectedResponse = new TodoPostResponse(postDto, "OK");
        when(todoServiceDAO.saveAndFlush(any(TodoPost.class))).thenReturn(expectedRecord());
        final TodoPostResponse createdResponse = restController.addTodoPost("Datum", "whatTODO", "Done");
        assertEquals(expectedResponse.toString(), createdResponse.toString());
        assertEquals(expectedResponse.getErrors(), createdResponse.getErrors());
    }

    @Test
    public void whenCorrectDataSent_validatorReturnsNoErrors() {
        when(todoServiceDAO.saveAndFlush(any(TodoPost.class))).thenReturn(expectedRecord());
        final TodoPostResponse createdResponse = restController.addTodoPost("Datum", "whatTODO", "Done");
        assertEquals("OK", createdResponse.getErrors());
    }

    @Test
    public void whenDatumMissed_validatorReturnsCorrectError() {
        when(todoServiceDAO.saveAndFlush(any(TodoPost.class))).thenReturn(expectedRecord());
        final TodoPostResponse createdResponse = restController.addTodoPost("", "whatTODO", "Done");
        assertEquals("Date-time" + ERROR_MESSAGE_LAST_PART, createdResponse.getErrors());
    }

    @Test
    public void whenWhatTODOMissed_validatorReturnsCorrectError() {
        when(todoServiceDAO.saveAndFlush(any(TodoPost.class))).thenReturn(expectedRecord());
        final TodoPostResponse createdResponse = restController.addTodoPost("Datum", "", "Done");
        assertEquals("Task" + ERROR_MESSAGE_LAST_PART, createdResponse.getErrors());
    }

    @Test
    public void whenDoneStatusMissed_validatorReturnsCorrectError() {
        when(todoServiceDAO.saveAndFlush(any(TodoPost.class))).thenReturn(expectedRecord());
        final TodoPostResponse createdResponse = restController.addTodoPost("Datum", "whatTODO", "Bla");
        assertEquals("Only TO DO or Done is allowed for Status. \n", createdResponse.getErrors());
    }

    @Test
    public void whenCorrectIdSentToDelete_returnedStatus200() {
        doNothing().when(todoServiceDAO).deleteById(anyLong());
        final ResponseEntity<String> createdResponse = restController.deleteTodoPost(1L);
        assertEquals("Successfully deleted task with ID: ", createdResponse.getBody());
        assertEquals(HttpStatus.OK, createdResponse.getStatusCode());
    }

    @Test
    public void whenIncorrectIdSentToDelete_returnedStatus500() {
        doThrow(new IllegalArgumentException()).when(todoServiceDAO).deleteById(anyLong());
        final ResponseEntity<String> createdResponse = restController.deleteTodoPost(-1L);
        assertEquals("", createdResponse.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, createdResponse.getStatusCode());
    }

    @Test
    public void whenCorrectIdSentToChangeStatus_HttpStatus200Returned() {
        when(todoServiceDAO.findAll()).thenReturn(expectedDatabaseContent());
        when(todoServiceDAO.saveAndFlush(any(TodoPost.class))).thenReturn(expectedRecord());
        final ResponseEntity<String> createdResponse = restController.changeStatus(1);
        assertEquals(HttpStatus.OK, createdResponse.getStatusCode());
    }

    @Test
    public void whenIncorrectIdSentToChangeStatus_HttpStatus404Returned() {
        when(todoServiceDAO.findAll()).thenReturn(expectedDatabaseContent());
        final ResponseEntity<String> createdResponse = restController.changeStatus(10);
        assertEquals(HttpStatus.NOT_FOUND, createdResponse.getStatusCode());
    }

    //
    // Auxiliary methods
    //

    private List<TodoPostDto> createFakeDatabase() {
        final List<TodoPostDto> fakeDatabase = new ArrayList<>();
        TodoPostDto postDto;
        Long id;
        for (int i = 0; i < 5; i++) {
            id = Long.parseLong("" + i);
            postDto = new TodoPostDto(id, "Fakedate" + (i + 1), "Task " + (i + 1), "false");
            fakeDatabase.add(postDto);
        }
        return fakeDatabase;
    }

    private List<TodoPost> expectedDatabaseContent() {
        final List<TodoPost> expectedContent = new ArrayList<>();
        TodoPost post;
        Long id;
        for (int i = 0; i < 5; i++) {
            id = Long.parseLong("" + i);
            post = new TodoPost();
            post.setId(id);
            post.setDatum("Fakedate" + (i + 1));
            post.setWhatTODO("Task " + (i + 1));
            post.setDoneStatus(false);
            expectedContent.add(post);
        }
        return expectedContent;
    }

    private TodoPost expectedRecord() {
        final TodoPost post = new TodoPost();
        post.setId(1L);
        post.setDatum("Datum");
        post.setWhatTODO("whatTODO");
        post.setDoneStatus(true);
        return post;
    }

    public ResponseEntity<String> expectedResponseEntity() {
        return new ResponseEntity<>("Successfully deleted task with ID: ", HttpStatus.OK);
    }
}
