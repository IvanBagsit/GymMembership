package com.gym.gymmembership.controller;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.UserDetailsDTO;
import com.gym.gymmembership.service.UserDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @GetMapping("/details")
    @ApiOperation(value = "Get User Details")
    public ResponseEntity<List<UserDetails>> getAllUserDetails(){
        ResponseEntity<List<UserDetails>> response;
        try{
            response = ResponseEntity.ok(userDetailsService.fetchAllUserDetails());
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/adduser")
    @ApiOperation(value = "Adding of user")
    public ResponseEntity<UserDetails> addUser(UserDetailsDTO userDetailsDTO) throws Exception {
        ResponseEntity<UserDetails> response;
        try {
            response = ResponseEntity.ok(userDetailsService.addUser(userDetailsDTO));
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
