package com.restapi_example1.controller;

import com.restapi_example1.entity.User;
import com.restapi_example1.excpetion.ResourceNotFound;
import com.restapi_example1.payload.UserDto;
import com.restapi_example1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        UserDto userDto1 = userService.saveUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<User>> readAllUser() {
        List<User> users = userService.readAllUser();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateEmailById(@RequestParam Long id, @RequestBody String email) {
        Long startTime = System.nanoTime();
        User user = userService.updateEmailById(email, id);
        Long endTime = System.nanoTime();
        System.out.println(endTime - startTime);
        return new ResponseEntity<>("Your " + email + " Updated", HttpStatus.FOUND);
    }


    @PutMapping("/updated")
    public ResponseEntity<String> updatedEmailById(@RequestBody User user) throws ResourceNotFound {
        Long startTime1 = System.nanoTime();
        User users = userService.updatedEmailById(user).getBody();
        Long endTime1 = System.nanoTime();
        System.out.println(endTime1 - startTime1);
        return new ResponseEntity<>("Your " + users.getEmail() + " Updated", HttpStatus.FOUND);

    }

    @GetMapping("/byid")
    public ResponseEntity<User> getById(@RequestParam Long id) throws Exception {
        User user = userService.findByUserId(id).getBody();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
