package TODOservice.controllers;

import TODOservice.dao.TODOServiceDAO;
import TODOservice.domain.TODOPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TODOServiceController {

    @Autowired
    private TODOServiceDAO todoServiceDAO;

    /*
        @RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
        public ModelAndView allTasks() {
            ModelAndView mav = new ModelAndView("todolist");
            List<TODOPost> allPosts = new ArrayList<TODOPost>();
            allPosts.addAll(getAllPosts());
            mav.addObject("TODOlist", allPosts);
            return mav;
        }
    */
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TODOPost> getAllPosts() {
        return todoServiceDAO.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TODOPost addTODOpost(@RequestBody @Valid TODOPost postToAdd) {
        Long id = todoServiceDAO
                .saveAndFlush(postToAdd)
                .getId();
        postToAdd.setId(id);
        return postToAdd;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteTODOPost(@PathVariable Long idToDelete) {
            todoServiceDAO.deleteById(idToDelete);
    }


    @RequestMapping(value = "/changestatus/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
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
