package todoservice.integrationtests;

import org.jbehave.core.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import todoservice.controllers.TodoServiceController;
import todoservice.dao.TodoServiceDao;
import todoservice.init.DatabaseInit;
import todoservice.services.TodoPostResponse;
import todoservice.web.dto.TodoPostDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ActiveProfiles("test")
public class SimpleSteps {

    private List<TodoPostDto> loadedRecords = new ArrayList<>();
    private TodoPostResponse response;
    private ResponseEntity<String> entity;

    @Autowired
    private TodoServiceController todoServiceController;

    @Autowired
    private TodoServiceDao todoServiceDao;

    @Autowired
    private DatabaseInit databaseInit;

    @Given("I create an in-memory database with data")
    public void createDatabase() throws Exception {
        databaseInit.run(null);
    }

    @AfterScenario
    public void dropDatabase() {
    }

    @When("I open the home page")
    public void openHomePage() {
        loadedRecords = todoServiceController.getAllPosts();
    }

    @Then("I see all posts")
    public void showAllPosts() {
        assertEquals(8, loadedRecords.size());
    }

    @When("I send correct data for new post: $datum, $whatTODO, $doneStatus")
    public void sendCorrectData(@Named("datum") String datum, @Named("whatTODO") String whatTODO,
                                @Named("doneStatus") String doneStatus) {
        response = todoServiceController.addTodoPost(datum, whatTODO, doneStatus);
    }

    @Then("I get a record with id $id and error code $error")
    public void haveRecordWithExpectedId(@Named("id") String id, @Named("error") String error) {
        final Long loadedId = Long.parseLong(id);
        assertEquals(loadedId, response.getId());
        assertEquals(response.getErrors(), error);
    }

    @When("I change status of record with id $id")
    public void changeStatus(@Named("id") int id) {
        entity = todoServiceController.changeStatus(id);
    }

    @Then("I get status of record with $statusCode and $doneStatus")
    public void getChangedStatus(@Named("statusCode") String statusCode,
                                 @Named("doneStatus") String doneStatus) {
        assertEquals(statusCode, entity.getStatusCode().toString());
        assertEquals(doneStatus, entity.getBody());
    }

    @When("I delete record with $id")
    public void deleteRecord(@Named("id") Long id) {
        entity = todoServiceController.deleteTodoPost(id);
    }

    @Then("I get status code $statusCode")
    public void getOKStatusWhenDeleted(@Named("statusCode") String statusCode) {
        assertEquals(statusCode, entity.getStatusCode().toString());
    }

    @Then("The record with id $id not present")
    public void noSuchRecord(@Named("id") int id) {
        loadedRecords = todoServiceController.getAllPosts();
        assertNull(loadedRecords.get(id));
    }
}
