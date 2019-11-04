package com.yukon.demoDB.demo.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yukon.demoDB.demo.person.Person;
import com.yukon.demoDB.demo.person.PersonDAO;
 
@Controller
public class MainController {
 
    @Autowired
    private PersonDAO personDAO;
 
    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<Person> all = personDAO.findAll();
 
        StringBuilder sb = new StringBuilder();
 
        all.forEach(p -> sb.append(p.getFullName() + "<br>"));
 
        return sb.toString();
    }
 
}
