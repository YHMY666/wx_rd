package edu.fdzc.wx1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.fdzc.wx1.mapper")// 扫描Mapper接口，生成代理实现类
public class Wx1Application {

    public static void main(String[] args) {
        SpringApplication.run(Wx1Application.class, args);
    }

}
