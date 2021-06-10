package aldora.spring.springrest.mybatis.service;

import aldora.spring.springrest.domain.Category;

import java.util.List;

public interface CategoryMybatisService {
    List<Category> getCategories();
}
