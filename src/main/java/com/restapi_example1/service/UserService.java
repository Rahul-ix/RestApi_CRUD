package com.restapi_example1.service;

import com.restapi_example1.entity.User;
import com.restapi_example1.excpetion.ResourceNotFound;
import com.restapi_example1.payload.UserDto;
import com.restapi_example1.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto saveUser(UserDto userDto) {
        //convert Dto to entity
        User user = convertDtoToEntity(userDto);
        User user1 = userRepository.save(user);

        //convert Entity to Dto
        UserDto userDto1 = convertEntityToDto(user1);
        return userDto1;

    }

    private UserDto convertEntityToDto(User user1) {

        return modelMapper.map(user1, UserDto.class);
    }

    private User convertDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public List<User> readAllUser() {
        return userRepository.findAll();
    }
    public ResponseEntity<List<User>> readAllUserPage(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort= sortDir.equals("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<User> all= userRepository.findAll(page);
        List<User> pages=all.getContent();
        return new ResponseEntity<>(pages,HttpStatus.OK);
    }

    public User updateEmailById(String email, Long id) {
        Optional<User> op = userRepository.findById(id);
        if (op.isPresent()) {
            User user = op.get();
            user.setEmail(email);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public ResponseEntity<User> updatedEmailById(User user) throws ResourceNotFound {
        Optional<User> op = userRepository.findById(user.getId());
        if (op.isPresent()) {
            User user1 = op.get();
            user1.setEmail(user.getEmail());
            userRepository.save(user1);
            return new ResponseEntity<>(user1, HttpStatus.FOUND);
        } else {
            throw new ResourceNotFound("Id is not present");
        }
    }

    public ResponseEntity<User> findByUserId(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Id is not valid")
        );

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
