package UsedStore.mapper;

import UsedStore.model.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {
    @Select("SELECT * FROM user")
    List<Test> findAll();
}