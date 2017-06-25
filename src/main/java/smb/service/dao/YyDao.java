package smb.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import smb.service.entity.YyDO;

@Mapper
public interface YyDao {

    @Select("SELECT * FROM yy WHERE id = #{id}")
    YyDO findById(@Param("id") int id);
    
    @Insert("insert into yy(id,yymc) value( #{id}, #{yymc})")
    int insert(YyDO entity);

    @Update("delete from yy")
    void deleteall();
    
    @Select("SELECT * FROM yy")
    List<YyDO> findList();
    
    
}
