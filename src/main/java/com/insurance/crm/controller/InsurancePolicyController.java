package com.insurance.crm.controller;

import com.insurance.crm.constant.ErrorMessage;
import com.insurance.crm.constant.HttpStatuses;
import com.insurance.crm.dto.policy.InsurancePolicyCreationDto;
import com.insurance.crm.dto.policy.InsurancePolicyUpdateDto;
import com.insurance.crm.entity.InsurancePolicy;
import com.insurance.crm.exception.NotFoundException;
import com.insurance.crm.service.impl.InsurancePolicyServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/policy")
@AllArgsConstructor
public class InsurancePolicyController {
    private InsurancePolicyServiceImpl insurancePolicyService;
    private ModelMapper modelMapper;

    @ApiOperation(value = "Create InsurancePolicy")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody InsurancePolicyCreationDto dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(insurancePolicyService.create(dto));
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @ApiOperation(value = "Update InsurancePolicy")
    @PutMapping("/{policyId}")
    public ResponseEntity<InsurancePolicyUpdateDto> update(@Valid @RequestBody InsurancePolicyUpdateDto dto,
                                                           @PathVariable Long policyId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(insurancePolicyService.update(dto,policyId),InsurancePolicyUpdateDto.class));
    }
    @ApiOperation(value = "Get all Policies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public List<InsurancePolicy> getAllPolicies(){
        return insurancePolicyService.getInsurancePolicies();
    }
    @GetMapping("/{id}")
    public InsurancePolicy getbyId(@PathVariable Long id){
        return insurancePolicyService.findById(id)
                .orElseThrow(()-> new NotFoundException(ErrorMessage.INSURANCE_POLICY_NOT_FOUND_BY_ID + id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        insurancePolicyService.delete(id);
        return ResponseEntity.ok().build();
    }

}