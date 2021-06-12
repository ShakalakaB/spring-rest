package aldora.spring.springrest.mybatis.mapper;

import aldora.spring.springrest.domain.Category;
import aldora.spring.springrest.mybatis.MyPager;
import aldora.spring.springrest.mybatis.ParamObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> mySelect(ParamObject paramObject);
}
