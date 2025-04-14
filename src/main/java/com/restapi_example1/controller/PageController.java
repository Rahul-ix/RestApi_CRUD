package com.restapi_example1.controller;

import com.restapi_example1.entity.User;
import com.restapi_example1.payload.UserDto;
import com.restapi_example1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/pages")
public class PageController {

    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/read")
    public ResponseEntity<ResponseEntity<List<User>>> readUser(
            @RequestParam (name = "pageNo",required = false , defaultValue = "0")int pageNo,
            @RequestParam(name = "pageSize",required = false,defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy",required = false,defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir",required = false,defaultValue = "asc")String sortDir
    ){
      ResponseEntity<List<User>> users= userService.readAllUserPage(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
