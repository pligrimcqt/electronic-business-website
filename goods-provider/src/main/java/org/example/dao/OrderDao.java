package org.example.dao;


import com.itheima.b2b.commonmodule.model.Userorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface OrderDao {
    //查询所有订单
    List<Userorder> getAllorder(@Param(value = "uid")int uid);
}
