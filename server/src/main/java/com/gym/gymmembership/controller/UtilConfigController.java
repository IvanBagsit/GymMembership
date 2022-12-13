package com.gym.gymmembership.controller;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.service.UtilConfigService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
@Slf4j
public class UtilConfigController {

    private final UtilConfigService utilConfigService;

    @GetMapping("/retrieve/{name}")
    @ApiOperation(value = "retrieving config")
    public ResponseEntity<String> retrieveConfig(@PathVariable final String name){
        log.info("Retrieveing config for {}", name);
        ResponseEntity<String> response;
        try {
            response = ResponseEntity.ok(utilConfigService.retrieveConfig(name));
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
