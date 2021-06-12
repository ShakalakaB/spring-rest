package aldora.spring.springrest.mybatis.mapper;

import aldora.spring.springrest.domain.Category;
import aldora.spring.springrest.mybatis.MyPager;
import aldora.spring.springrest.mybatis.Params;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> mySelect(Params params);

    MyPager<Category> myPagerSelect(MyPager<Category> myPager, @Param("params") Params params);

    //without @Param example
    MyPager<Category> myPagerSelect2(MyPager<Category> myPager, Params params);

    MyPager<Map<String, Category>> myPagerMapSelect(MyPager<Category> myPager, @Param("params") Params params);

    MyPager<Category> myPagerSelectIf(MyPager<Category> myPager, @Param("params") Params params);
}
