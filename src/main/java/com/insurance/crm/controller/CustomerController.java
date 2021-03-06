package com.insurance.crm.controller;

import com.insurance.crm.constant.HttpStatuses;
import com.insurance.crm.entity.Customer;
import com.insurance.crm.forms.CustomerForm;
import com.insurance.crm.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @ApiOperation(value = "Save customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
    })
   @PostMapping("/add")
    public ResponseEntity save(@Valid @RequestBody CustomerForm form){
        Customer customer = new Customer();
        customer.setSurname(form.getSurname());
        customer.setFirstname(form.getFirstname());
        customer.setPatronymic(form.getPatronymic());
        customer.setPhoneNum(form.getPhoneNum());
        customer.setHomeAddress(form.getHomeAddress());
        customer.setAge(form.getAge());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
    }
    @ApiOperation(value = "Get customer by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping("/{customerId}")
    public Customer getById(@PathVariable Long customerId){
        return customerService.getById(customerId);

    }
    @ApiOperation("Get all customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @GetMapping
    public List<Customer> getAll(){
        return customerService.getCustomers();
    }
    @ApiOperation(value = "Update customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@Valid @RequestBody CustomerForm form, @PathVariable("id") Long id){
        //Customer customer = new Customer();
        Customer customer = customerService.getById(id);
        customer.setSurname(form.getSurname());
        customer.setFirstname(form.getFirstname());
        customer.setPatronymic(form.getPatronymic());
        customer.setPhoneNum(form.getPhoneNum());
        customer.setHomeAddress(form.getHomeAddress());
        customer.setAge(form.getAge());
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.update(customer));
    }
    @ApiOperation(value = "Delete customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 303, message = HttpStatuses.SEE_OTHER),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/customerId")
    public ResponseEntity delete (@PathVariable Long customerId){
        customerService.delete(customerId);
        return ResponseEntity.ok().build();
    }



}
