package happy.controller;

import com.alibaba.druid.pool.DruidDataSource;
import happy.exception.UseTooManyException;
import happy.pojo.User;
import happy.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class controller01 {

@RequestMapping(value = {"/","/login"},method = RequestMethod.GET)
    public ModelAndView test01(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, HttpSession session, Model model){
    if(StringUtils.hasLength(user.getUsername())&&StringUtils.hasLength(user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","账号信息错误");
            return "/login";
        }
    }

    //使用重定向解决表单重复提交问题和登陆拦截
    @GetMapping("/main.html")
    public ModelAndView mainPage(HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;
    }

    @GetMapping("/basic_table.html")
    public String basic01(){
    return "table/basic_table";
    }
    @GetMapping("/dynamic_table.html")
    public String dynamic01(Model model){
        List<User> list= Arrays.asList(new User("zhangsan1","123456"),
                new User("zhangsan2","123456"),
                new User("zhangsan3","123456"),
                new User("zhangsan4","123456")
        );
        if(list.size() >3){
            throw new UseTooManyException();
        }
        model.addAttribute("model",list);
        return "table/dynamic_table";
    }
    @GetMapping("/responsive_table.html")
    public String editable01(){
        return "table/editable_table";
    }

    @Autowired
    JdbcTemplate jt;
    @ResponseBody
    @GetMapping("/druid01")
    public String druid01(){
        long k =jt.queryForObject("select count(*)  from t_user",Long.class);
        return String.valueOf(k);
    }

    @Autowired()
    Service service;
    @GetMapping("/like")
    @ResponseBody
    public Object test02(){
       return service.selectAll();
    }
}
