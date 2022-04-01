package com.example.ecommerce.Web.rest.errors;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class IndexController implements ErrorController {
    private final static String PATH = "/error";


    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return "No Mapping Found";
    }
}
