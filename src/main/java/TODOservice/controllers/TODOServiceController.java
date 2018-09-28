package TODOservice.controllers;

import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
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
            produces = "application/json")
    @ResponseBody
    public List<TODOPost> getAllPosts() {
          List<TODOPost> allPosts = todoServiceDAO.findAll();
      return allPosts;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public TODOPost addTODOPost(@RequestBody @Valid TODOPost postToAdd) {

        Long id = todoServiceDAO
                .saveAndFlush(postToAdd)
                .getId();
        postToAdd.setId(id);
        return postToAdd;
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE,
            consumes = "application/json")
    @ResponseBody
    public String deleteTODOPost(@PathVariable Long idToDelete) {
        String deleteResponse="Booo!";
           try {
               todoServiceDAO.deleteById(idToDelete);
           } catch (IllegalArgumentException e){
               deleteResponse+="Terrible failure!";
           }
            return deleteResponse;
    }


    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT,
            produces = "application/json")
    @ResponseBody
    public TODOPost changeStatus(@PathVariable Long idToChange) {
        List<TODOPost> allPosts = todoServiceDAO.findAll();
        TODOPost post = allPosts.get(idToChange.intValue());
        if (post.isDoneStatus()) post.setDoneStatus(false);
        else post.setDoneStatus(true);
        todoServiceDAO.saveAndFlush(post);
        return post;
    }
}
