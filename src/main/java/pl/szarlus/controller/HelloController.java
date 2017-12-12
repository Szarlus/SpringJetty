package pl.szarlus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by karol on 25.11.2017.
 */
@Controller
@SuppressWarnings("UnusedDeclaration")
public class HelloController {

    private final String message = "HELLO";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        System.out.println("Request for Hello resource");
        return message;
    }

}
