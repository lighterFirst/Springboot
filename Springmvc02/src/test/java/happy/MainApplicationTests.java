package happy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class MainApplicationTests {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    @Qualifier(value = "as")
    DataSource dataSource;

    @Test
    void contextLoads(){
         long k =jt.queryForObject("select count(*)  from t_user",Long.class);
        log.info("记录总数"+k);

        log.info("数据类型：{}"+dataSource.getClass());
    }


}
