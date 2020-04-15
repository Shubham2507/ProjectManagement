package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.Organization;
import com.example.demo.entity.Projects;
import com.example.demo.entity.Resources;
import com.example.demo.repo.OrganizationRepo;
import com.example.demo.repo.ProjectRepo;
import com.example.demo.repo.ResourcesRepo;

@SpringBootTest
class ProjectServiceTest {
	@Mock
	ProjectRepo projectRepo;
	@Mock
	OrganizationRepo organizationRepo;
	@Mock
	ResourcesRepo resourcesRepo;
	@Mock
	IOrganizationService IOrganizationService;
	@InjectMocks
	ProjectServiceImpl projectServiceImpl;

	@Test
	void testaddProject() {
		Resources resources = new Resources();
		resources.setName("testing");
		Organization org = new Organization();
		org.setName("test");
		List<Projects> ids = new ArrayList<Projects>();
		Projects pr = new Projects();
		pr.setName("testing");
		ids.add(pr);
		Projects pr1 = null;
		when(projectRepo.findAllByResources(resources)).thenReturn(ids);
		when(organizationRepo.getOne(1)).thenReturn(org);
		when(IOrganizationService.updateOrganization(org)).thenReturn(org);
		Object obj = projectServiceImpl.addItemToProjects(1, pr);
		assertEquals(obj, pr1);

	}

	@Test
	void testgetProject() {
		List<ProjectDao> pdao = new ArrayList<ProjectDao>();
		Resources resources = new Resources();
		resources.setName("testing");
		Set<Resources> temp = new HashSet<Resources>();
		temp.add(resources);
		List<Projects> proj = new ArrayList<Projects>();
		Projects pro = new Projects();
		pro.setAllocated_capital(1065656);
		pro.setUsed_capital(6885);
		pro.setName("gdg");
		pro.setProject_id(1);
		pro.setResources(temp);
		proj.add(pro);
		when(projectRepo.findAll()).thenReturn(proj);
		List<ProjectDao> pdao1 = projectServiceImpl.getAllProjects();
		pdao = pdao1;
		assertEquals(pdao, pdao1);
	}

	@Test
	void testupdateProject() {
		Projects pro = new Projects();
		pro.setAllocated_capital(1065656);
		pro.setUsed_capital(6885);
		pro.setName("gdg");
		pro.setProject_id(1);
		Optional<Projects> projects2 = Optional.of(pro);
		when(projectRepo.findById(1065656)).thenReturn(projects2);
		when(projectRepo.save(pro)).thenReturn(pro);
		Object obj = projectServiceImpl.updateProjects(pro);
		assertEquals(obj, pro);

	}

	@Test
	void testdeleteProject() {
		Projects pro = new Projects();
		pro.setAllocated_capital(1065656);
		pro.setUsed_capital(6885);
		pro.setName("gdg");
		pro.setProject_id(1);
		Optional<Projects> projects2 = Optional.of(pro);
		when(projectRepo.findById(1)).thenReturn(projects2);
		doNothing().when(projectRepo).deleteById(1);
		String ans = projectServiceImpl.deleteOneProject(1);
		assertEquals(ans, "Deleted Successfully");

	}

}
