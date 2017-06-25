package smb.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import smb.service.entity.KeshiDO;

@Mapper
public interface KeshiDao {

    @Select("SELECT * FROM keshi WHERE id = #{id}")
    KeshiDO findById(@Param("id") int id);

    @Select("SELECT id,keshi_id as keshiId,keshi_mc as keshiMc,yy_id as yyId,yy_mc as yyMc FROM keshi")
    List<KeshiDO> findList();
    
    @Insert("insert into keshi(keshi_id,keshi_mc,yy_id,yy_mc) value( #{keshiId}, #{keshiMc}, #{yyId}, #{yyMc})")
    int insert(KeshiDO entity);

    @Update("delete from keshi where yy_id=#{yyId}")
    void deleteByYyid(@Param("yyId") String yyId);
    
    
}
