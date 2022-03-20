package apple.controller;

import apple.service.Service01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller01 {

@Autowired
Service01 service01;
@GetMapping("cola")
    public Object test01(){
    return service01.selectAll();
}

}
