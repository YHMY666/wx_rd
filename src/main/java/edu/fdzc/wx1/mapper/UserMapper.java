package edu.fdzc.wx1.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.fdzc.wx1.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}