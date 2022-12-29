package net.springrestapi.restapidemo.service.implementation;

import lombok.AllArgsConstructor;
import net.springrestapi.restapidemo.dto.UserDto;
import net.springrestapi.restapidemo.entity.User;
import net.springrestapi.restapidemo.mapper.UserMapper;
import net.springrestapi.restapidemo.repository.UserRepository;
import net.springrestapi.restapidemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(Long id, UserDto updatedUser) {
        User existingUser = userRepository.findById(id).get();
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        userRepository.save(existingUser);
        return UserMapper.mapToUserDto(existingUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }
}
