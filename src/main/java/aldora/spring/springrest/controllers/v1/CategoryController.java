package aldora.spring.springrest.controllers.v1;

import aldora.spring.springrest.api.v1.model.CategoryDTO;
import aldora.spring.springrest.api.v1.model.CategoryListDTO;
import aldora.spring.springrest.domain.Category;
import aldora.spring.springrest.mybatis.service.CategoryMybatisService;
import aldora.spring.springrest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    private CategoryMybatisService categoryMybatisService;

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
//    public List<CategoryDTO> getAllCategoriesList() {
    public List<Category> getAllCategoriesList() {
        return categoryMybatisService.getCategories();
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
