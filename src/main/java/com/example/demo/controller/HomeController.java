package com.example.demo.controller;

import com.example.demo.entity.Organization;
import com.example.demo.service.HomeService;
import jakarta.annotation.PostConstruct;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    private HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity <List<Organization>> saveDetails(@RequestBody List<Organization> organization) {
       List<Organization> saved = homeService.saveEmployeeDetails(organization);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getDetailsById(@PathVariable Integer id){
        return homeService.findEmployeeById(id).map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getAllEmployees(){
        List<Organization> employees = homeService.findAllEmployees();
        if(employees .isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Organization> updateDetailsId(@PathVariable Integer id, @RequestBody Organization organization){
        Organization updated = homeService.updateById(id, organization);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteEmployee(@PathVariable Integer id){
        homeService.deleteById(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search-by-name")
    public ResponseEntity<List<Organization>> searchEmployeeByName(@RequestParam String name){
        List<Organization> nameResult = homeService.searchByName(name);
        if(nameResult.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(nameResult);
    }

    @GetMapping("/search-by-salary-range")
    public ResponseEntity <List<Organization>> searchBySalaryRange(@RequestParam Double salary){
        List<Organization> sorted = homeService.searchBySalaryRange(salary);
        if(sorted.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sorted);
    }
}