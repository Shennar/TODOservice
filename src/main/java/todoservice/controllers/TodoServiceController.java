package todoservice.controllers;

import org.springframework.stereotype.Component;
import todoservice.dao.TodoServiceDao;
import todoservice.domain.TodoPost;
import todoservice.services.TodoPostResponse;
import todoservice.web.dto.TodoPostDto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Component
@RequestMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoServiceController {

    private TodoServiceDao todoServiceDAO;

    public TodoServiceController(final TodoServiceDao todoServiceDao){
        this.todoServiceDAO = todoServiceDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TodoPostDto> getAllPosts() {
        final DozerBeanMapper mapper = new DozerBeanMapper();
        final List<TodoPostDto> allPostsUI = new ArrayList<>();
//        final List<TodoPost> allRecords = todoServiceDAO.findAll();
        for (final TodoPost post : todoServiceDAO.findAll()) {
            final TodoPostDto postUI = mapper.map(post, TodoPostDto.class);
            allPostsUI.add(postUI);
        }
        System.out.println("All posts listed");
        return allPostsUI;
    }

    @RequestMapping(method = RequestMethod.POST)
    public TodoPostResponse addTodoPost(@RequestParam("datum") String datum, @RequestParam("whatTODO") String whatTODO,
                                        @RequestParam("doneStatus") String doneStatus) {
        final String validationResult = validator(datum, whatTODO, doneStatus);
        final TodoPostResponse postUI;
        if (validationResult.isEmpty()) {
            final TodoPost postToAdd = new TodoPost();
            postToAdd.setDatum(datum.replace('T', ' '));
            postToAdd.setWhatTODO(whatTODO);
            postToAdd.setDoneStatus("Done".equals(doneStatus));
            final TodoPost postAdded = todoServiceDAO.saveAndFlush(postToAdd);
            final Long id = postAdded.getId();
            postUI = new TodoPostResponse(new TodoPostDto(id, datum.replace('T', ' '),
                    whatTODO, doneStatus), "OK");
        } else {
            postUI = new TodoPostResponse(new TodoPostDto(), validationResult);
        }
        return postUI;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTodoPost(@RequestParam("idToDelete") Long idToDelete) {
        try {
            todoServiceDAO.deleteById(idToDelete);
            return new ResponseEntity<>("Successfully deleted task with ID: ", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> changeStatus(@RequestParam("idToChange") int idToChange) {
        String postStatusForUI;
        TodoPost postToChange = new TodoPost();
        boolean postNotFound = true;
        for (final TodoPost post : todoServiceDAO.findAll()) {
            if (post.getId() == idToChange) {
                postToChange = post;
                postNotFound = false;
                break;
            }
        }
        if (postNotFound) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        if (postToChange.isDoneStatus()) {
            postToChange.setDoneStatus(false);
            postStatusForUI = "TO DO";
        } else {
            postToChange.setDoneStatus(true);
            postStatusForUI = "Done";
        }
        todoServiceDAO.saveAndFlush(postToChange);
        return new ResponseEntity<>(postStatusForUI, HttpStatus.OK);
    }

    private String validator(String datum, String whatTODO, String doneStatus) {
        String errors = "";
        final String fixedString = " field must be filled. \n ";
        if (whatTODO.length() == 0) errors += "Task" + fixedString;
        if (doneStatus.length() == 0) errors += "Status" + fixedString;
        else if (!("TO DO".equals(doneStatus) || "Done".equals(doneStatus)))
            errors += "Only TODO or Done is allowed for Status. \n";
        if (datum.length() == 0) errors += "Date-time" + fixedString;
        return errors;
    }
}