package happy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.Service.UserService;
import happy.mapper.UserMapper;
import happy.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("happy.mapper")
public class Test02 {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){

       long k = userService.count();
        System.out.println("k = " + k);
    }

    @Test
    public void test03(){
        //动态查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //链式结构，返回还是其本身对象
        queryWrapper.like("username","zhang").between("id","2","5").
                isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
     }

     @Test
    public void test04(){
        //升序查询
         QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
         List<User> list = userMapper.selectList(queryWrapper);
         list.forEach(System.out::println);
    }
    @Test
    public void test05(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("id","3").like("username","zhang")
                .or().eq("username","jack");
        User user = new User();
        user.setUsername("lisi");
        userMapper.update(user,queryWrapper);
        userMapper.selectList(queryWrapper);
    }

    @Test
    public void test06(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        String username ="a";
        queryWrapper.like(StringUtils.isNotBlank("a"),User::getUsername,username);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testPage(){
        Page<User> page = new Page<>(1,2);
        userMapper.selectPage(page,null);
        System.out.println(page);
    }

}
















