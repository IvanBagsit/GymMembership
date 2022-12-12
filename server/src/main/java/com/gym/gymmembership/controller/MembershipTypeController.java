package com.gym.gymmembership.controller;

import com.gym.gymmembership.domain.MembershipType;
import com.gym.gymmembership.dto.MembershipTypeDTO;
import com.gym.gymmembership.service.MembershipTypeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/membership")
@Slf4j
public class MembershipTypeController {

    private final MembershipTypeService membershipTypeService;

    @GetMapping("/details")
    @ApiOperation("Fetch all membership plans")
    public ResponseEntity<List<MembershipType>> getAllMembershipPlans() {
        log.info("Start to fetch all membership plans");
        ResponseEntity<List<MembershipType>> response;
        try {
            response = ResponseEntity.ok(membershipTypeService.fetchAllMembershipPlan());
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/create")
    @ApiOperation("Create a new Membership Plan")
    public ResponseEntity<MembershipTypeDTO> createMembershipPlan(@RequestBody MembershipTypeDTO membershipTypeDTO) {
        log.info("Start to create a new membership plan");
        ResponseEntity<MembershipTypeDTO> response;
        try {
            response = ResponseEntity.ok(membershipTypeService.addMembershipPlan(membershipTypeDTO));
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/update")
    @ApiOperation("Update Membership Plan")
    public ResponseEntity<MembershipTypeDTO> updateMembershipPlan(@RequestBody MembershipTypeDTO membershipTypeDTO) {
        log.info("Start to update membership plan");
        ResponseEntity<MembershipTypeDTO> response;
        try {
            response = ResponseEntity.ok(membershipTypeService.updateMembershipPlan(membershipTypeDTO));
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/delete")
    @ApiOperation("Delete a Membership Plan")
    public ResponseEntity<String> deleteMembershipPlan(@RequestBody MembershipTypeDTO membershipTypeDTO) {
        log.info("Start to delete a membership plan");
        ResponseEntity<String> response;
        try {
            response = ResponseEntity.ok(membershipTypeService.deleteMembershipPlan(membershipTypeDTO));
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
