package com.example.demo.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
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

import com.example.demo.dao.OrganizatonDao;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Organization;
import com.example.demo.service.IOrganizationService;
import com.example.demo.service.OrganizationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { OrganizationController.class, OrganizationServiceImpl.class })
@WebMvcTest
class OrganizationControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	IOrganizationService IOrganizationService;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testaddOrgniztion() throws Exception {

		Organization ad = new Organization();
		ad.setId(1);
		ad.setName("testing");

		String inputJson = mapToJson(ad);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/poc/organization/add").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testgetOrgniztion() throws Exception {

		OrganizatonDao ad = new OrganizatonDao();
		ad.setId(1);
		ad.setName("testing");

		String inputJson = mapToJson(ad);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/poc/organization").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testupdateOrgniztion() throws Exception {

		Organization ad = new Organization();
		ad.setId(1);
		ad.setName("testing");

		String inputJson = mapToJson(ad);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.put("/poc/organization").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testdeleteOrgniztion() throws Exception {

		String inputJson = mapToJson(1);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.delete("/poc/organization/{id}", 1).content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

}
