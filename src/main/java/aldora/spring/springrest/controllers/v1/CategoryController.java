package aldora.spring.springrest.controllers.v1;

import aldora.spring.springrest.api.v1.model.CategoryDTO;
import aldora.spring.springrest.api.v1.model.CategoryListDTO;
import aldora.spring.springrest.domain.Category;
import aldora.spring.springrest.mybatis.MyPager;
import aldora.spring.springrest.mybatis.Params;
import aldora.spring.springrest.mybatis.mapper.CategoryMapper;
import aldora.spring.springrest.mybatis.service.CategoryMybatisService;
import aldora.spring.springrest.services.CategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    private CategoryMybatisService categoryMybatisService;

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategoriesList() {
        return categoryMapper.selectList(Wrappers.<Category>query().orderByAsc("id"));
    }

    @GetMapping("/list/pager")
    public IPage<Category> pageCategoriesList() {
        Page<Category> page = new Page<>(1, 6);
        page.addOrder(OrderItem.asc("name"));
        return categoryMapper.selectPage(page, Wrappers.<Category>query().orderByAsc("id"));
    }

    @GetMapping("/list/myselect")
    public List<Category> myselectCategoriesList() {
        Params params = new Params();
        params.setName("Exotic");

        List<Category> result = categoryMapper.mySelect(params);

        return result;
    }

    @GetMapping("/list/mypager")
    public MyPager<Category> myPagerCategoriesList() {
        MyPager<Category> myPager = new MyPager<>(1, 1, "Fruits");
        Params params = new Params();
        params.setName("Exotic");

//        MyPager<Category> result = categoryMapper.myPagerSelect(myPager, params);
        MyPager<Category> result = categoryMapper.myPagerSelect2(myPager, params);

        return result;
    }

    @GetMapping("/list/map")
    public MyPager<Map<String, Category>> myPagerMapCategoriesList() {
        MyPager<Category> myPager = new MyPager<>(1, 1, "Fruits");
        Params params = new Params();
        params.setName("Exotic");

        MyPager<Map<String, Category>> result = categoryMapper.myPagerMapSelect(myPager, params);

        return result;
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
