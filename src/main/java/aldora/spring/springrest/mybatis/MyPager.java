package aldora.spring.springrest.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class MyPager<T> extends Page<T> {
    private String pageParam;

    public MyPager(long current, long size, String pageParam) {
        super(current, size);
        this.pageParam = pageParam;
    }
}
