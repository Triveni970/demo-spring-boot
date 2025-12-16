package com.example.demo.controller;

import com.example.demo.entity.Organization;
import com.example.demo.service.HomeService;
import jakarta.annotation.PostConstruct;
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
    public Organization saveDetails(@RequestBody Organization organization) {
        return homeService.saveEmployeeDetails(organization);
    }

    @GetMapping("/{id}")
    public Optional getDetailsById(@PathVariable Integer id){
        return homeService.findEmployeeById(id);
    }

    @GetMapping
    public List<Organization> getAllEmployees(){
        return homeService.findAllEmployees();
    }

    @PutMapping("/update/{id}")
    public Organization updateDetailsId(@PathVariable Integer id, @RequestBody Organization organization){
            return homeService.updateById(id, organization);
    }


}