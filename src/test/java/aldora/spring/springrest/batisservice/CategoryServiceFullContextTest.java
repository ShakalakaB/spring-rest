package aldora.spring.springrest.batisservice;

import aldora.spring.springrest.domain.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CategoryServiceFullContextTest {
    @Autowired
    CategoryMyBatisServiceImpl categoryMyBatisService;

    @Test
    void listTest() {
        List<Category> categoryList = categoryMyBatisService.list();
        assertTrue(categoryList.size() > 1);
    }
}