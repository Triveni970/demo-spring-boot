package com.example.demo.repository;

import com.example.demo.entity.Organization;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Organizationdb extends JpaRepository<Organization, Integer> {

    List<Organization> findByName(String name);
    List<Organization> findBySalary(Double salary, Sort sort);
}
