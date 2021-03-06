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

@AutoConfigureMockMvc
@ContextConfiguration(classes = { DesignationController.class, DesignationServiceImpl.class })
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

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/poc/designation/add").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testgetDesignation() throws Exception {

		Designation ad = new Designation();
		ad.setCapital(0);
		ad.setId(1);
		ad.setName("testing");

		String inputJson = mapToJson(ad);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/poc/designation").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testupdateDesignation() throws Exception {

		Designation ad = new Designation();
		ad.setCapital(0);
		ad.setId(1);
		ad.setName("testing");

		String inputJson = mapToJson(ad);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.put("/poc/designation").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testdeleteDesignation() throws Exception {

		String inputJson1 = mapToJson(1);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.delete("/poc/designation/{id}", 1).content(inputJson1)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

}
