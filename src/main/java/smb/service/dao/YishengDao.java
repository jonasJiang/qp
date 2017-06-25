package smb.service.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import smb.service.entity.YishengDO;

@Mapper
public interface YishengDao {

    @Select("SELECT * FROM keshi WHERE id = #{id}")
    YishengDO findById(@Param("id") int id);
    
    @Insert("insert into yisheng(keshi_id,keshi_mc,ys_id,ys_mc,ys_title,ys_image_url,feature,order_count,yy_id,yy_mc) "
            + "value( #{keshiId},#{keshiMc}, #{ysId},#{ysMc},#{ysTitle},#{ysImageUrl},#{feature},#{orderCount}, #{yyId}, #{yyMc})")
    int insert(YishengDO entity);

    @Update("delete from yisheng where yy_id=#{yyId} and keshi_id=#{keshiId}")
    void deleteByYyIdKeshiId(@Param("yyId") String yyId,@Param("keshiId") String keshiId);

}
