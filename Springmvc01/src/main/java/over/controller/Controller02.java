package over.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller02 {

    @RequestMapping(value = "/map/{id}/user/{name}",method = RequestMethod.GET)
    public Object test01(@PathVariable String id, @PathVariable String name, @PathVariable Map<String,String> map){
       Map<String,Object> map1 = new HashMap<>();

        map1.put("id",id);
        map1.put("name",name);
        map1.put("map",map);
        return map1;
    }

    //矩阵变量
    //url:"/cars/sell;name=zhangsan;id=1,2,3"
    @RequestMapping(value = "/cars/{path}",method = RequestMethod.GET)
    public Object test02(@MatrixVariable("name") String name,@MatrixVariable("id") List<Integer> list){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("list",list);
        return map;
    }

    //测试thymeleaf视图解析器
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public ModelAndView test02(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("no","胡大爷万岁");
        mv.addObject("no1","https://www.bilibili.com/");
        mv.setViewName("hello");
        return mv;
    }

}
