package aldora.spring.springrest.batisservice;

import aldora.spring.springrest.domain.Category;
import aldora.spring.springrest.mybatis.mapper.CategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryMyBatisServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryMyBatisService {
}
