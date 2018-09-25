package TODOservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
  //      return new ModelAndView("todolist");
        return "todolist";
    }
}

