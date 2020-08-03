package app.controller;

import app.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.model.User;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> add(@RequestBody User user){//
        boolean isOK = userService.saveUser(user);
        if(isOK){
            return new ResponseEntity<>(HttpStatus.CREATED,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/users/auth", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> getToken(@RequestBody User user){//
        User daoUser = userService.findUser(user);
        if(daoUser != null){
            return new ResponseEntity<>(HttpStatus.OK,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
