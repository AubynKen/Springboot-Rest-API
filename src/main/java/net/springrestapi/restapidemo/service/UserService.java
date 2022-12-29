package net.springrestapi.restapidemo.service;

import net.springrestapi.restapidemo.dto.UserDto;
import net.springrestapi.restapidemo.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto updatedUser);

    void deleteUserById(Long id);
}
