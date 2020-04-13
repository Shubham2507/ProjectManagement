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

import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Projects;
import com.example.demo.service.IProjectService;
import com.example.demo.service.ProjectServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { ProjectController.class, ProjectServiceImpl.class })
@WebMvcTest
class PojectControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	IProjectService projectService;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testaddProject() throws Exception {

		Projects ad = new Projects();
		ad.setName("testing");

		String inputJson = mapToJson(ad);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/poc/project/add/{organizationId}", 1).content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testgetProject() throws Exception {

		ProjectDao ad = new ProjectDao();
		ad.setName("testing");

		String inputJson = mapToJson(ad);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/poc/project").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testupdateProject() throws Exception {

		Projects ad = new Projects();
		ad.setName("testing");

		String inputJson = mapToJson(ad);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.put("/poc/project").content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

	@Test
	public void testdeleteProject() throws Exception {
		String inputJson = mapToJson(1);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.delete("/poc/project/{id}", 1).content(inputJson)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

	}

}
