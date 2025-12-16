package com.example.demo.service;

import com.example.demo.entity.Organization;
import com.example.demo.repository.Organizationdb;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeService {

    private Organizationdb organizationdb;

    public HomeService(Organizationdb organizationdb){
        this.organizationdb = organizationdb;
    }

    public Organization saveEmployeeDetails(Organization organization){
        return organizationdb.save(organization);
    }

    public Optional<Organization> findEmployeeById(Integer id){
        return organizationdb.findById(id);
    }

    public List<Organization> findAllEmployees(){
        return organizationdb.findAll();
    }

    public Organization updateById(Integer id, Organization organization){
        Organization updated = organizationdb.findById(id).orElseThrow(() -> new RuntimeException("No such Id found"));
        updated.setName(organization.getName());
        updated.setSalary(organization.getSalary());
        return organizationdb.save(updated);
    }

    public void deleteById(Integer id){
        organizationdb.deleteById(id);
    }
}
