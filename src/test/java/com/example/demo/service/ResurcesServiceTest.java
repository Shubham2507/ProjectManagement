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

import com.example.demo.dao.ResourcesDao;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Projects;
import com.example.demo.entity.Resources;
import com.example.demo.repo.DesignationRepo;
import com.example.demo.repo.ProjectRepo;
import com.example.demo.repo.ResourcesRepo;

@SpringBootTest
class ResurcesServiceTest {
	@Mock
	DesignationRepo designationRepo;
	@Mock
	ResourcesRepo resourcesRepo;
	@Mock
	ProjectRepo projectRepo;
	@InjectMocks
	ResourcesServiceImpl resourcesServiceImpl;

	@Test
	void testaddResources() {
		Designation designation = new Designation();
		designation.setId(1);
		Resources resources = new Resources();
		resources.setName("testing");
		resources.setDesignation(designation);
		when(designationRepo.getOne(1)).thenReturn(designation);
		when(resourcesServiceImpl.addItemToResources(1, resources)).thenReturn(resources);
		Resources result = resourcesServiceImpl.addItemToResources(1, resources);
		assertEquals(result, resources);
	}

	@Test
	void testgetResources() {
		Designation designation = new Designation();
		designation.setId(1);
		designation.setName("test");
		List<ResourcesDao> resourcesDaos = new ArrayList<ResourcesDao>();
		ResourcesDao resourcesDao = new ResourcesDao();
		resourcesDao.setName("testalpha");
		resourcesDao.setDesignation("test");
		resourcesDaos.add(resourcesDao);
		List<Resources> resourceslis = new ArrayList<Resources>();
		Resources resources = new Resources();
		resources.setName("testing");
		resources.setDesignation(designation);
		resourceslis.add(resources);
		List<Projects> projects = new ArrayList<Projects>();
		Projects pr = new Projects();
		pr.setName("hh");
		projects.add(pr);
		when(resourcesRepo.findAll()).thenReturn(resourceslis);
		when(projectRepo.findAllByResources(resources)).thenReturn(projects);
		List<ResourcesDao> result = resourcesServiceImpl.getAllResources();
		assertEquals(result, resourcesDaos);

	}

	@Test
	void testupdaateResources() {
		Resources resources = new Resources();
		resources.setName("testing");
		resources.setResource_id(1);
		Optional<Resources> resources2 = Optional.of(resources);
		when(resourcesRepo.findById(1)).thenReturn(resources2);
		when(resourcesRepo.save(resources)).thenReturn(resources);
		Object result = resourcesServiceImpl.updateResources(resources);
		assertEquals(result, resources);

	}

	@Test
	void testdeleteResources() {
		Resources resources = new Resources();
		resources.setName("testing");
		Optional<Resources> resources2 = Optional.of(resources);
		when(resourcesRepo.findById(1)).thenReturn(resources2);
		doNothing().when(resourcesRepo).deleteById(1);
		String ans = resourcesServiceImpl.deleteOneDesignation(1);
		assertEquals(ans, "Deleted Successfully");

	}
}
