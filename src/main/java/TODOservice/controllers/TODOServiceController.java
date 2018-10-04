package TODOservice.controllers;

import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.lang.IllegalArgumentException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@Transactional
public class TODOServiceController {

    @Autowired
    private TODOServiceDAO todoServiceDAO;

    @RequestMapping(value = "/todo", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TODOPost> getAllPosts() {
          List<TODOPost> allPosts = todoServiceDAO.findAll();
        System.out.println("All posts listed");
      return allPosts;
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
        String deleteResponse="Booo";
        try {
               todoServiceDAO.deleteById(idToDelete);
        } catch (IllegalArgumentException e){
               deleteResponse+="Terrible failure!";
           }
            return deleteResponse;
    }


    @RequestMapping(value = "/todo", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TODOPost changeStatus(@RequestParam("idToChange") int idToChange) {
        List<TODOPost> allPosts = todoServiceDAO.findAll();
        TODOPost post = new TODOPost();
        for (TODOPost p : allPosts)
            if (p.getId()==idToChange) {
                post=p;
                break;
            }
        if (post.isDoneStatus()) post.setDoneStatus(false);
        else post.setDoneStatus(true);
        todoServiceDAO.saveAndFlush(post);
        return post;
    }
}
