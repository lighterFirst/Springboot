package happy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import happy.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Map<String,String> selectById01();
}
