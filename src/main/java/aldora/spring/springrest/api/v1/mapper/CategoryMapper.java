package aldora.spring.springrest.api.v1.mapper;

import aldora.spring.springrest.api.v1.model.CategoryDTO;
import aldora.spring.springrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    String INSTANCEs = "Mappers.getMapper(CategoryMapper.class)";

    CategoryDTO categoryToCategoryDTO(Category category);
}
