package com.techgeeknext.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin()
public class EmployeeController {
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Object getEmployees(@AuthenticationPrincipal UserDetails customUser) {
        return customUser.getUsername();
    }
}