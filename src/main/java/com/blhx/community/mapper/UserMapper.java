package com.blhx.community.mapper;

import com.blhx.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert ( User user );
    @Select("select * from user where token=#{token}")
    User findBYToken (@Param("token") String token );

    @Select("select * from user where id=#{id}")
    User findBYID ( @Param("id") Integer create);

}
