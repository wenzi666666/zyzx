package net.tfedu.zhl.sso.dao;

import java.util.List;

import net.tfedu.zhl.sso.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectAll();
    
    List<User> selectSelective(User record);
}