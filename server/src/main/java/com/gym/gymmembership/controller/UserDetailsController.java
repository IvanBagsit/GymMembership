package com.gym.gymmembership.controller;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.SearchDTO;
import com.gym.gymmembership.dto.UserDetailsDTO;
import com.gym.gymmembership.service.UserDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
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

    @GetMapping("/details/{id}")
    @ApiOperation(value = "Retrieve specific user details")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable Long id){
        log.info("Retrieveing user details with id of {}", id);
        ResponseEntity<UserDetails> response;
        try{
            response = ResponseEntity.ok(userDetailsService.fetchUserDetail(id));
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/create")
    @ApiOperation(value = "User creation")
    public ResponseEntity<Object> addUser(@RequestBody UserDetailsDTO userDetailsDTO) throws Exception {
        log.info("Start of user creation");
        ResponseEntity<Object> response;
        try {
            response = ResponseEntity.ok(userDetailsService.addUser(userDetailsDTO));
            return response;
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
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

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a specific user")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        log.info("Start of user deletion");
        ResponseEntity<String> response;
        try {
            return ResponseEntity.ok(userDetailsService.deleteUser(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/details/search")
    @ApiOperation(value = "filter user in dashboard")
    public ResponseEntity<List<UserDetails>> filterUserDetails(@RequestBody SearchDTO searchDTO){
        log.info("Start filtering users");
        ResponseEntity<List<UserDetails>> response;
        try {
            response = ResponseEntity.ok(userDetailsService.filterUserDetails(searchDTO));
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
