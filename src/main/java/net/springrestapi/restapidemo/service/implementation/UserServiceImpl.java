package net.springrestapi.restapidemo.service.implementation;

import lombok.AllArgsConstructor;
import net.springrestapi.restapidemo.dto.UserDto;
import net.springrestapi.restapidemo.entity.User;
import net.springrestapi.restapidemo.mapper.UserMapper;
import net.springrestapi.restapidemo.repository.UserRepository;
import net.springrestapi.restapidemo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
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
        User savedUser = userRepository.save(existingUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).get();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
