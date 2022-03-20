package happy.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import happy.mapper.UserMapper;
import happy.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


}
