package happy.configure;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
@Qualifier("as")
public class DataSourceConfig {

    //注入德鲁伊连接池
    //容器中没有datasource,就是用源代码默认的
    //SpringIOC 容器管理一个或者多个bean，这些bean都需要在@Configuration注解下进行创建，
    // 在一个方法上使用@Bean注解就表明这个方法需要交给Spring进行管理。
    //当没有显式命名时，自动注册的名称为方法名
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //加入监控功能stat,防火墙wall
        dataSource.setFilters("stat,wall");

        return dataSource;
    }

    //druid的监控功能


    public ServletRegistrationBean test01(){

        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(statViewServlet,"/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
         return servletRegistrationBean;
    }

    //WebStatFilter用于采集web-jdbc关联监控的数据

    public FilterRegistrationBean webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(webStatFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        //放行静态资源和我们的sql请求
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,/druid01");
        return filterRegistrationBean;
    }
}
