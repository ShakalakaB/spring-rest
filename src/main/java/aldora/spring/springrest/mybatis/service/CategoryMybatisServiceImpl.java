package aldora.spring.springrest.mybatis.service;

import aldora.spring.springrest.domain.Category;
import aldora.spring.springrest.mybatis.mapper.CategoryMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMybatisServiceImpl implements CategoryMybatisService {
    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategories() {
        return categoryMapper.selectList(Wrappers.<Category>query().orderByAsc("id"));
    }
}
