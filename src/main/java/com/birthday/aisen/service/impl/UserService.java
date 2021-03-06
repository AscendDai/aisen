package com.birthday.aisen.service.impl;

import com.birthday.aisen.entity.User;
import com.birthday.aisen.dto.UserDTO;
import com.birthday.aisen.mapper.UserMapper;
import com.birthday.aisen.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public UserDTO getUserById(long uid) {
        UserDTO dto = new UserDTO();
        User entity = userMapper.getUserById(uid);

        // test redis
        redisTemplate.opsForValue().set(String.valueOf(entity.getId()), entity.getUsername());
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public List<UserDTO> getUsersByName(String username) {
        List<UserDTO> dtos = new ArrayList<>();
        List<User> entities = userMapper.getUsersByName('%' + username + '%');
        for (User entity : entities) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
