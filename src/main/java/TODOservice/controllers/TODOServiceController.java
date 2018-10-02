package TODOservice.controllers;

import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
import TODOservice.web.dto.TODOpostDTO;
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
      return allPosts;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TODOPost addTODOPost(@RequestBody @Valid TODOpostDTO post) {
        TODOPost postToAdd = new TODOPost();
        postToAdd.setWhatTODO(post.getWhatTODO());
        postToAdd.setDatum(post.getDatum());
        postToAdd.setDoneStatus(false);//(post.isDoneStatus());
        Long id = todoServiceDAO
                .saveAndFlush(postToAdd)
                .getId();
        postToAdd.setId(id);
        return postToAdd;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
           // consumes = "application/json")
    @ResponseBody
    public String deleteTODOPost(@RequestParam("idToDelete") Long idToDelete) {
        String deleteResponse="Booo!";
           try {
               todoServiceDAO.deleteById(idToDelete);
           } catch (IllegalArgumentException e){
               deleteResponse+="Terrible failure!";
           }
            return deleteResponse;
    }


    @RequestMapping(value = "/todo", method = RequestMethod.PUT,
        //    consumes = "application/json",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TODOPost changeStatus(@RequestParam("idToChange") Long idToChange) {
        List<TODOPost> allPosts = todoServiceDAO.findAll();
        TODOPost post = allPosts.get(idToChange.intValue());
        if (post.isDoneStatus()) post.setDoneStatus(false);
        else post.setDoneStatus(true);
        todoServiceDAO.saveAndFlush(post);
        return post;
    }
}
