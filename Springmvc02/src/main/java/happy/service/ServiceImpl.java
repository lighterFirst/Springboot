package happy.service;

import happy.dao.Return01;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    Return01 return01;

    @Override
    public int selectAll() {
        return return01.test01();
    }
}
