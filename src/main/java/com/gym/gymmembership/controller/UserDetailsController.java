package com.gym.gymmembership.controller;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.UserDetailsDTO;
import com.gym.gymmembership.service.UserDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @GetMapping("/details")
    @ApiOperation(value = "Get User Details")
    public ResponseEntity<List<UserDetails>> getAllUserDetails(){
        log.info("Start to retrieve User Details");
        ResponseEntity<List<UserDetails>> response;
        try{
            response = ResponseEntity.ok(userDetailsService.fetchAllUserDetails());
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/create")
    @ApiOperation(value = "User creation")
    public ResponseEntity<UserDetailsDTO> addUser(@RequestBody UserDetailsDTO userDetailsDTO) throws Exception {
        log.info("Start of user creation");
        ResponseEntity<UserDetailsDTO> response;
        try {
            response = ResponseEntity.ok(userDetailsService.addUser(userDetailsDTO));
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/update")
    @ApiOperation(value = "Updating of user details")
    public ResponseEntity<UserDetailsDTO> updateUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        log.info("Start of user details updating");
        ResponseEntity<UserDetailsDTO> response;
        try {
            response = ResponseEntity.ok(userDetailsService.updateUser(userDetailsDTO));
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
