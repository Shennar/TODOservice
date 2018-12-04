package TODOservice.controllers;

import TODOservice.dao.TodoServiceDao;
import TODOservice.domain.TodoPost;
import TODOservice.services.TodoPostResponse;
import TODOservice.web.dto.TodoPostDto;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoServiceController {

    @Autowired
    private TodoServiceDao todoServiceDAO;

    @RequestMapping(method = RequestMethod.GET)
    public List<TodoPostDto> getAllPosts() {
        final DozerBeanMapper mapper = new DozerBeanMapper();
        final List<TodoPostDto> allPostsUI = new ArrayList<>();
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
            if ("Done".equals(doneStatus)) {
                postToAdd.setDoneStatus(true);
            } else {
                postToAdd.setDoneStatus(false);
            }
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
    public String deleteTodoPost(@RequestParam("idToDelete") Long idToDelete) {
        try {
            todoServiceDAO.deleteById(idToDelete);
            return "Successfully deleted task with ID: ";
        } catch (IllegalArgumentException e) {
            return "Impossible to delete task with ID: ";
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
            return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
        }
        if (postToChange.isDoneStatus()) {
            postToChange.setDoneStatus(false);
            postStatusForUI = "TO DO";
        } else {
            postToChange.setDoneStatus(true);
            postStatusForUI = "Done";
        }
        todoServiceDAO.saveAndFlush(postToChange);
        return new ResponseEntity<String>(postStatusForUI, HttpStatus.OK);
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