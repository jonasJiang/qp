package smb.service.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import smb.service.entity.ResultDO;

@Mapper
public interface ResultDao {


    @Select("SELECT * FROM result WHERE id = #{id}")
    ResultDO findById(@Param("id") int id);

    @Insert("insert into result(mobile,name,idcard,want_date,yy_id,keshi_id,ys_id,qhmm,ddid)"
            + " value( #{mobile}, #{name}, #{idcard}, #{wantDate}, #{yyId}, #{keshiIid}, #{ysId}, #{qhmm}, #{ddid})")
    int insert(ResultDO entity);

    @Select("SELECT mobile,name,idcard,want_date as wantDate,yy_id as yyId,keshi_id as keshiId,"
            + "ys_id as ysId,qhmm,ddid,create_time FROM result where want_date = #{wantDate}")
    List<ResultDO> findList(@Param("wantDate") Date wantDate);
    
}
