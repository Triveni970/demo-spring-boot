package com.example.demo.service;

import com.example.demo.entity.Organization;
import com.example.demo.repository.Organizationdb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HomeServiceTest {
    @Mock
    Organizationdb organizationdb;
    @InjectMocks
    HomeService homeService;

    @Test
    void saveEmployeeDetailsShouldSaveEmployeeeDetails() {
        Organization org = new Organization();
        org.setId(1);
        org.setName("ABC");
        org.setSalary(1000.0);
        System.out.println("Checking unit Test");
        List<Organization> organizations = new ArrayList<>();
        organizations.add(org);
        Mockito.when(organizationdb.saveAll(organizations)).thenReturn(organizations);
        List<Organization> addEmployee = homeService.saveEmployeeDetails(organizations);
        Assertions.assertEquals(1, addEmployee.get(0).getId());
        Assertions.assertEquals("ABC", addEmployee.get(0).getName());
        Assertions.assertEquals(1000.0, addEmployee.get(0).getSalary());
        Mockito.verify(organizationdb).saveAll(organizations);
    }
    @Test
    void findEmployeeByIdShouldFindEmployeeById(){
      Organization organiz = new Organization();
      organiz.setId(1);
      Mockito.when(organizationdb.findById(1)).thenReturn(Optional.of(organiz));
        Optional<Organization> enterId = homeService.findEmployeeById(1);
        Assertions.assertTrue(enterId.isPresent());
        Assertions.assertEquals(1,enterId.get().getId());
        Mockito.verify(organizationdb).findById(1);

    }
    @Test
    void findAllEmployeesShouldFindAllEmployees(){
        Organization organiz = new Organization();
        organiz.setId(1);
        organiz.setName("Hello");
        organiz.setSalary(1000);
        List<Organization> org = new ArrayList<>();
        org.add(organiz);
        Mockito.when(organizationdb.findAll()).thenReturn(org);
        List<Organization> findEmployee = homeService.findAllEmployees();
        Assertions.assertEquals(1, org.get(0).getId());
        Assertions.assertEquals("Hello", org.get(0).getName());
        Assertions.assertEquals(1000.0, org.get(0).getSalary());
        Mockito.verify(organizationdb).findAll();
    }
}
