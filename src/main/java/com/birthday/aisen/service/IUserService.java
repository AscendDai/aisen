package com.birthday.aisen.service;

import com.birthday.aisen.dto.UserDTO;

import java.util.List;

public interface IUserService {
    public UserDTO getUserById(long uid);

    public List<UserDTO> getUsersByName(String username);
}
