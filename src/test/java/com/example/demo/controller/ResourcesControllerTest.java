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

import com.example.demo.dao.ResourcesDao;
import com.example.demo.entity.Resources;
import com.example.demo.service.IResourcesService;

import com.example.demo.service.ResourcesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { ResourcesController.class, ResourcesServiceImpl.class })
@WebMvcTest
class ResourcesControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	IResourcesService IResourcesService;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testaddResources() throws Exception {

		Resources ad = new Resources();
		ad.setName("testing");

		String inputJson = mapToJson(ad);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/poc/resources/add/{designationId}", 1).content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testgetResources() throws Exception {

		ResourcesDao ad = new ResourcesDao();
		ad.setName("testing");

		String inputJson = mapToJson(ad);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/poc/resources").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testupdateResources() throws Exception {

		Resources ad = new Resources();
		ad.setName("testing");

		String inputJson = mapToJson(ad);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.put("/poc/resources").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testdeleteResources() throws Exception {

		String inputJson = mapToJson(1);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.delete("/poc/resources/{id}", 1).content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

}
