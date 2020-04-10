package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.entity.Designation;
import com.example.demo.service.DesignationServiceImpl;
import com.example.demo.service.IDesignatonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SpringBootTest

//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { DesignationController.class, DesignationServiceImpl.class })
//@SpringBootTest(classes = DesignationController.class)
//@EnableWebMvc
@WebMvcTest
class DesignationControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	IDesignatonService designatonService;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testaddDesignation() throws Exception {

		Designation ad = new Designation();
		ad.setCapital(0);
		ad.setId(1);
		ad.setName("testing");

		String inputJson = mapToJson(ad);
		//String expected = "{\"code\":\"200\",\"message\":\"Data Added Successfully\",\"response\":{\"id\":1,\"name\":\"testing\",\"capital\":0}}";

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/poc/designation/add").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);
		assertEquals(inputJson, resultDOW);
	}

}
