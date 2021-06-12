package aldora.spring.springrest.mybatis.mapper;

import aldora.spring.springrest.api.v1.model.CustomerDTO;
import aldora.spring.springrest.domain.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerMapper extends BaseMapper<Customer> {
    @ResultMap("customerMap")
//    @Select("select c.*, p.description as p_description from customer c left join payment p on c.id = p.customer_id-->")
    List<CustomerDTO> getCustomers();
}
