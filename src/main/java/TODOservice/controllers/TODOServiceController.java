package TODOservice.controllers;

import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
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
        for (TODOPost p : allPosts){
            String postUIStatus="";
            if (p.isDoneStatus()) {
                postUIStatus="Done";
            }
            else {
                postUIStatus=("TO DO");
            }
            TODOPostDTO postUI = new TODOPostDTO(p.getId(), p.getDatum(), p.getWhatTODO(), postUIStatus);
            allPostsUI.add(postUI);
        }
        System.out.println("All posts listed");
      return allPostsUI;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TODOPost addTODOPost(@Valid @RequestParam("datum") String datum, @Valid @RequestParam("whatTODO") String whatTODO,
                                @Valid @RequestParam("doneStatus") String doneStatus) {
        TODOPost postToAdd = new TODOPost();
        postToAdd.setWhatTODO(whatTODO);
        postToAdd.setDatum(datum);
        postToAdd.setDoneStatus(false);
        Long id = todoServiceDAO
                .saveAndFlush(postToAdd)
                .getId();
        postToAdd.setId(id);
        return postToAdd;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.DELETE)
    public String deleteTODOPost(@RequestParam("idToDelete") Long idToDelete) {
        String deleteResponse="Successfully deleted task with ID: ";
        try {
               todoServiceDAO.deleteById(idToDelete);
        } catch (IllegalArgumentException e){
               deleteResponse="Impossible to delete task with ID: ";
           }
            return deleteResponse;
    }


    @RequestMapping(value = "/todo", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String changeStatus(@RequestParam("idToChange") int idToChange) {
        List<TODOPost> allPosts = todoServiceDAO.findAll();
        String postForUI ="";
        TODOPost post = new TODOPost();
        for (TODOPost p : allPosts) {
            if (p.getId() == idToChange) {
                post = p;
                break;
            }
        }
        if (post.isDoneStatus()) {
            post.setDoneStatus(false);
            postForUI="TO DO";
        }
        else {
            post.setDoneStatus(true);
            postForUI="Done";
        }
        todoServiceDAO.saveAndFlush(post);
        return postForUI;
    }
}
