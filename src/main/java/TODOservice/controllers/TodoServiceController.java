package TODOservice.controllers;

import TODOservice.dao.TodoServiceDao;
import TODOservice.domain.TodoPost;
import TODOservice.services.TODOPostResponse;
import TODOservice.web.dto.TodoPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoServiceController {

    @Autowired
    private TodoServiceDao todoServiceDAO;

    @RequestMapping(value = "/todo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TodoPostDto> getAllPosts() {
        List<TodoPost> allPosts = todoServiceDAO.findAll();
        List<TodoPostDto> allPostsUI = new ArrayList<>();
        for (TodoPost p : allPosts) {
            String postUIStatus = "" + (p.isDoneStatus() ? "Done" : "TO DO");
            TodoPostDto postUI = new TodoPostDto(p.getId(), p.getDatum(), p.getWhatTODO(), postUIStatus);
            allPostsUI.add(postUI);
        }
        System.out.println("All posts listed");
        return allPostsUI;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TODOPostResponse addTODOPost(@RequestParam("datum") String datum, @RequestParam("whatTODO") String whatTODO,
                                        @RequestParam("doneStatus") String doneStatus) {
        String validationResult = validator(datum, whatTODO, doneStatus);
        TODOPostResponse postUI;
        if (validationResult.isEmpty()) {
            TodoPost postToAdd = new TodoPost();
            postToAdd.setDatum(datum.replace('T', ' '));
            postToAdd.setWhatTODO(whatTODO);
            if ("Done".equals(doneStatus)) {
                postToAdd.setDoneStatus(true);
            } else {
                postToAdd.setDoneStatus(false);
            }
            postToAdd = todoServiceDAO.saveAndFlush(postToAdd);
            Long id = postToAdd.getId();
            postUI = new TODOPostResponse(new TodoPostDto(id, datum.replace('T', ' '),
                    whatTODO, doneStatus), "OK");
        } else {
            postUI = new TODOPostResponse(new TodoPostDto(), validationResult);
        }
        return postUI;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.DELETE)
    public String deleteTODOPost(@RequestParam("idToDelete") Long idToDelete) {
        String deleteResponse = "Successfully deleted task with ID: ";
        try {
            todoServiceDAO.deleteById(idToDelete);
        } catch (IllegalArgumentException e) {
            deleteResponse = "Impossible to delete task with ID: ";
        }
        return deleteResponse;
    }


    @RequestMapping(value = "/todo", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String changeStatus(@RequestParam("idToChange") int idToChange) {
        List<TodoPost> allPosts = todoServiceDAO.findAll();
        String postStatusForUI = "";
        TodoPost post = new TodoPost();
        boolean postNotFound = true;
        for (TodoPost p : allPosts) {
            if (p.getId() == idToChange) {
                post = p;
                postNotFound = false;
                break;
            }
        }
        if (postNotFound) return "No such record in the database.";

        if (post.isDoneStatus()) {
            post.setDoneStatus(false);
            postStatusForUI = "TO DO";
        } else {
            post.setDoneStatus(true);
            postStatusForUI = "Done";
        }
        todoServiceDAO.saveAndFlush(post);
        return postStatusForUI;
    }

    public String validator(String datum, String whatTODO, String doneStatus) {
        String errors = "";
        String fixedString = " field must be filled. \n ";
        if (whatTODO.length() == 0) errors += "Task" + fixedString;
        if (doneStatus.length() == 0) errors += "Status" + fixedString;
        else if (!("TO DO".equals(doneStatus) || "Done".equals(doneStatus)))
            errors += "Only TODO or Done is allowed for Status. \n";
        if (datum.length() == 0) errors += "Date-time" + fixedString;
        return errors;
    }
}
