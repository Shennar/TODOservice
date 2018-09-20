package TODOservice.controllers;

import TODOservice.dao.TODOserviceDAO;
import TODOservice.domain.TODOPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TODOServiceController {
    @Autowired
    private TODOserviceDAO todoServiceDAO;

    @RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
    public String showAllPosts(Model model){
        List<TODOPost> allTODOPosts = new ArrayList<>();
        Iterable<TODOPost> allTODOsFound = todoServiceDAO.findAll();
        for (TODOPost post: allTODOsFound){
            allTODOPosts.add(post);
        }
        model.addAttribute("TODOlist", allTODOPosts);
        return "showAllPosts"; // think on return type and value
    }



}
