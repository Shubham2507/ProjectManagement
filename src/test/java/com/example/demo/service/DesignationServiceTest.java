package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Designation;
import com.example.demo.repo.DesignationRepo;

@SpringBootTest
class DesignationServiceTest {
	@Mock
	DesignationRepo designationRepo;
	@InjectMocks
	DesignationServiceImpl designationServiceImpl;

	@Test
	public void testaddDesignaton() {
		Designation designation = new Designation();
		designation.setId(1);
		designation.setName("testing");
		when(designationServiceImpl.addItemToDesignation(designation)).thenReturn(designation);
		Designation result = designationServiceImpl.addItemToDesignation(designation);
		assertEquals(result, designation);

	}

	@Test
	public void testgetDesignaton() {
		Designation designation = new Designation();
		designation.setId(1);
		designation.setName("testing");
		List<Designation> designationlis = new ArrayList<Designation>();
		designationlis.add(designation);
		when(designationServiceImpl.getAllDesignation()).thenReturn(designationlis);
		List<Designation> result = designationServiceImpl.getAllDesignation();
		assertEquals(result, designationlis);
	}

	@Test
	public void testupdateDesignaton() {
		Designation designation = new Designation();
		designation.setId(1);
		designation.setName("testing");
		Optional<Designation> des = Optional.of(designation);
		when(designationRepo.findById(1)).thenReturn(des);
		when(designationRepo.save(designation)).thenReturn(designation);
		when(designationServiceImpl.updateDesignation(designation)).thenReturn(designation);
		Object obj = designationServiceImpl.updateDesignation(designation);
		assertEquals(obj, designation);
	}

	@Test
	public void testdeleteDesignaton() {
		Designation designation = new Designation();
		designation.setId(1);
		designation.setName("testing");
		Optional<Designation> des = Optional.of(designation);
		when(designationRepo.findById(1)).thenReturn(des);
		doNothing().when(designationRepo).deleteById(1);
		String ans = designationServiceImpl.deleteOneDesignation(1);
		assertEquals(ans, "Deleted Successfully");
	}

}
