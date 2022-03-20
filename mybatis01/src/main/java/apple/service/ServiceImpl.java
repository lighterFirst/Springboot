package apple.service;

import apple.dao.Dao01;
import apple.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements  Service01 {

    @Autowired
    Dao01 dao01;
    @Override
    public List<User> selectAll() {
        return dao01.selectAll();
    }
}
