package over.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller01 {
    @RequestMapping("/test")
    public Object test01(){
        return "aaaa";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Object testGet(){
        return "get";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Object testPost(){
        return "post";
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public Object testPut(){
        return "put";
    }

    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public Object testDelete(){
        return "delete";
    }

}
