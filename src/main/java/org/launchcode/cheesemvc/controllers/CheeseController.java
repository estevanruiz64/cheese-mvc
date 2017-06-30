package org.launchcode.cheesemvc.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value="")
public class CheeseController {

    static HashMap<String,String> cheeses = new HashMap<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDesc){
        cheeses.put(cheeseName,cheeseDesc);
        return "redirect:";
    }

    @RequestMapping(value="delete", method=RequestMethod.GET)
    public String displayDeleteCheeseForm(Model model){
        model.addAttribute("title", "Delete Cheese");
        model.addAttribute("cheeses", cheeses);
        return "cheese/delete";
    }

    @RequestMapping(value="delete", method=RequestMethod.POST)
    public String processDeleteCheeseForm(@RequestParam ArrayList<String> cheeseName){
        for (String i : cheeseName){
            if (cheeses.containsKey(i)){
                cheeses.remove(i);
        }
        }
        return "redirect:";
    }

}