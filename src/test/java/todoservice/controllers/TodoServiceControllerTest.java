package todoservice.controllers;

import org.dozer.DozerBeanMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import todoservice.services.TodoPostResponse;
import todoservice.web.dto.TodoPostDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TodoServiceControllerTest {

    final private DozerBeanMapper mapper = new DozerBeanMapper();

    @Autowired
    private FakeDatabase fakeTodoServiceDAO;

    private TodoServiceController restController = new TodoServiceController(fakeTodoServiceDAO);

//    @Ignore
    @Test
    public void whenSentGetRequest_allPostsShown() {
        // when(todoServiceDaoMock.findAll()).thenReturn(fakeTodoServiceDAO.findAll());
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
