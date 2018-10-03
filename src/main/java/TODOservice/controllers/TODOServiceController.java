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
        System.out.println("All posts listed");
      return allPosts;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TODOPost addTODOPost(@RequestBody @Valid TODOPost post) {
        System.out.println("Inside add...");
        System.out.println(post);
        TODOPost postToAdd = new TODOPost();
        postToAdd.setWhatTODO(post.getWhatTODO());
        postToAdd.setDatum(post.getDatum());
        postToAdd.setDoneStatus(false);//(post.isDoneStatus());
        System.out.println("Data assigned "+post);
        Long id = todoServiceDAO
                .saveAndFlush(postToAdd)
                .getId();
        postToAdd.setId(id);
        System.out.println("Obtained id: "+id);

        return postToAdd;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
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
