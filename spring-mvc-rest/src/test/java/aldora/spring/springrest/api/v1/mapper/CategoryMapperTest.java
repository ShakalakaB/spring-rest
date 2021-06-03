package test.java.aldora.spring.springrest.api.v1.mapper;

import aldora.spring.springrest.api.v1.model.CategoryDTO;
import aldora.spring.springrest.domain.Category;
import aldora.spring.springrest.api.v1.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setId(1L);
        category.setName("ddd");

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(1L, categoryDTO.getId());
        assertEquals("ddd", categoryDTO.getName());
    }
}