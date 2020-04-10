package com.example.demo.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entity.Designation;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=DesignationServiceImpl.class)
@EnableWebMvc
class DesignationServiceTest {
	
	@Autowired
	DesignationServiceImpl DesignationServiceImpl;

	@Test
	public void testaddItemToDesignation()throws Exception {
		Designation ad = new Designation();
		ad.setId(1);
		ad.setName("testing");
		//verify(DesignationServiceImpl,times(1)).addItemToDesignation(ad);
		when(DesignationServiceImpl.addItemToDesignation(ad)).thenReturn(ad);
		assertEquals(ad, ad);
		
	}

	

}



