package swtech.pageDesignControl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

@EnableScheduling   // 开启定时任务
@SpringBootApplication
@MapperScan("swtech.pageDesignControl.mapper")
public class RunApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){

        return application.sources(RunApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(RunApplication.class, args);
	}

}
