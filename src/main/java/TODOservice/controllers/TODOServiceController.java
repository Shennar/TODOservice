package TODOservice.controllers;

import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
import TODOservice.services.TODOPostResponse;
import TODOservice.web.dto.TODOPostDTO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.IllegalArgumentException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
public class TODOServiceController {

    @Autowired
    private TODOServiceDAO todoServiceDAO;

    @RequestMapping(value = "/todo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TODOPostDTO> getAllPosts() {
        List<TODOPost> allPosts = todoServiceDAO.findAll();
        List<TODOPostDTO> allPostsUI = new ArrayList<>();
        for (TODOPost p : allPosts) {
            String postUIStatus = "" + (p.isDoneStatus() ? "Done" : "TO DO");
            TODOPostDTO postUI = new TODOPostDTO(p.getId(), p.getDatum(), p.getWhatTODO(), postUIStatus);
            allPostsUI.add(postUI);
        }
        System.out.println("All posts listed");
        return allPostsUI;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TODOPostResponse addTODOPost(@Valid @RequestParam("datum") String datum, @Valid @RequestParam("whatTODO") String whatTODO,
                                        @Valid @RequestParam("doneStatus") String doneStatus) {
        String validationResult = validator(datum, whatTODO, doneStatus);
        TODOPostResponse postUI;
        if (validationResult.length() == 0) {
            TODOPost postToAdd = new TODOPost();
            postToAdd.setDatum(datum);
            postToAdd.setWhatTODO(whatTODO);
            if (doneStatus.equals("Done")) {
                postToAdd.setDoneStatus(true);
            } else {
                postToAdd.setDoneStatus(false);
            }
            postToAdd = todoServiceDAO.saveAndFlush(postToAdd);
            Long id = postToAdd.getId();
            postUI = new TODOPostResponse(new TODOPostDTO(id, datum, whatTODO, doneStatus),
                    "OK");
        } else {postUI = new TODOPostResponse( new TODOPostDTO(), validationResult);}
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
        List<TODOPost> allPosts = todoServiceDAO.findAll();
        String postStatusForUI = "";
        TODOPost post = new TODOPost();
        boolean postNotFound = true;
        for (TODOPost p : allPosts) {
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
        else if (!(doneStatus.equals("TO DO") || doneStatus.equals("Done")))
            errors += "Only TODO or Done is allowed for Status. \n";
        if (datum.length() == 0) errors += "Date-time" + fixedString;
        return errors;
    }
}
