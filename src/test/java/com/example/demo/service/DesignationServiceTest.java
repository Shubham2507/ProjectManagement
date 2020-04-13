package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

}
