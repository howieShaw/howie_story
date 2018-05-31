package com.howie.story.biz.mapper;

import com.howie.story.biz.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午8:20 2018/5/24
 * @Modified by:xiaohaoyun
 */
public interface UserMapper {
    UserEntity getUserByUserId(@Param("userId")int userId);
}
