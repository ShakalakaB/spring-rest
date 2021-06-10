package aldora.spring.springrest.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("aldora.spring.springrest.mybatis.mapper")
public class MybatisPlusConfig {
}
