package smb.service.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import smb.service.entity.RequestDO;

@Mapper
public interface RequestDao {

    @Select("SELECT * FROM request WHERE id = #{id}")
    RequestDO findById(@Param("id") int id);

    @Select("SELECT mobile,name,idcard,want_date as wantDate,yy_id as yyId,keshi_id as keshiId,"
            + "ys_id as ysId,qp_date as qpDate ,status FROM request where want_date = #{wantDate} and  status = #{status} ")
    RequestDO findByUser(@Param("idcard") String idcard,@Param("wantDate") Date wantDate,@Param("yyId")String yyId,
                         @Param("ysId")String ysId,@Param("keshi_id")String keshiId);
    
    @Insert("insert into request(mobile,name,idcard,want_date,yy_id,keshi_id,ys_id,qp_date)"
            + " value( #{mobile}, #{name}, #{idcard}, #{wantDate}, #{yyId}, #{keshiIid}, #{ysId}, #{qpDate})")
    int insert(RequestDO entity);

    @Update("update request set status = #{status} where WHERE id = #{id}")
    int updateStatus(@Param("id") int id,@Param("status")RequestEnum status);
    
    @Select("SELECT mobile,name,idcard,want_date as wantDate,yy_id as yyId,keshi_id as keshiId,"
            + "ys_id as ysId,qp_date as qpDate ,status FROM request where qp_date = #{qpDate} and  status = #{status} ")
    List<RequestDO> findNeedList(@Param("qpDate") Date qpDate,@Param("status")RequestEnum status);
    
}
