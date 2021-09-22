package aldora.spring.springrest.batisservice;

import aldora.spring.springrest.domain.Category;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@MybatisTest
//@Import(CategoryMyBatisServiceImpl.class)
class CategoryMyBatisServiceImplTest {
    @Autowired
    CategoryMyBatisServiceImpl categoryMyBatisService;

    @Test
    void listTest() {
        List<Category> categoryList = categoryMyBatisService.list();
        assertTrue(categoryList.size() > 1);
    }
}