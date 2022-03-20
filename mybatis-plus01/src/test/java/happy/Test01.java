package happy;

import happy.Util.UUIDGenerator;
import happy.mapper.UserMapper;
import happy.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class Test01 {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test02(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void test03(){
        User user = new User();
        user.setId(new UUIDGenerator().generete());
        user.setUsername("李四");
        user.setPassword("123456");
        user.setEmail("191323523@qq.com");
       int k = userMapper.insert(user);
        System.out.println("k = " + k);
    }

    @Test
    public void testDelete(){
        int k = userMapper.deleteById(1);
        System.out.println("k"+k);
    }

    @Test
    public void testElse(){
        List list = Arrays.asList(1,2,3,4);
        int i = userMapper.deleteBatchIds(list);
        System.out.println("i = " + i);
    }



}















