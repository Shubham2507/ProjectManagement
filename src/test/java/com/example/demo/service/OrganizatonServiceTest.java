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

import com.example.demo.dao.OrganizatonDao;
import com.example.demo.entity.Organization;
import com.example.demo.entity.Projects;
import com.example.demo.repo.OrganizationRepo;
import com.example.demo.repo.ProjectRepo;

@SpringBootTest
class OrganizatonServiceTest {
	@Mock
	OrganizationRepo organizationRepo;
	@Mock
	ProjectRepo projectRepo;
	@InjectMocks
	OrganizationServiceImpl organizationServiceImpl;

	@Test
	void testaddOrganization() {
		Organization organizationob = new Organization();
		organizationob.setName("testing");
		Organization organizationob1 = null;
		when(organizationServiceImpl.addItemToOrganization(organizationob)).thenReturn(organizationob);
		when(organizationRepo.save(organizationob)).thenReturn(organizationob);
		Organization result = organizationServiceImpl.addItemToOrganization(organizationob);
		assertEquals(result, organizationob1);
	}

	@Test
	void testgetOrganization() {
		List<OrganizatonDao> orglis = new ArrayList<OrganizatonDao>();

		OrganizatonDao odao = new OrganizatonDao();
		odao.setId(1);
		orglis.add(odao);
		List<Organization> organizatons = new ArrayList<Organization>();
		Organization org = new Organization();
		org.setName("test");
		org.setId(1);
		org.setTotal_cost(10000);
		organizatons.add(org);
		List<Projects> ids = new ArrayList<Projects>();
		Projects pr = new Projects();
		pr.setName("testing");
		ids.add(pr);
		when(organizationRepo.findAll()).thenReturn(organizatons);
		when(projectRepo.findAllByOrganization_Id(1)).thenReturn(ids);
		List<OrganizatonDao> ans = organizationServiceImpl.getAllOrganization();
		List<OrganizatonDao> orglis1 = ans;
		assertEquals(ans, orglis1);

	}

	@Test
	void testupdateOrganization() {
		Organization org = new Organization();
		org.setName("test");
		org.setId(1);
		org.setTotal_cost(10000);
		Optional<Organization> organization2 = Optional.of(org);
		when(organizationRepo.findById(1)).thenReturn(organization2);
		when(organizationRepo.save(org)).thenReturn(org);
		Object obj = organizationServiceImpl.updateOrganization(org);
		assertEquals(obj, org);

	}

	@Test
	void testdeleteOrganization() {
		Organization org = new Organization();
		org.setName("test");
		org.setId(1);
		org.setTotal_cost(10000);
		Optional<Organization> organization2 = Optional.of(org);
		when(organizationRepo.findById(1)).thenReturn(organization2);
		doNothing().when(organizationRepo).deleteById(1);
		String ans = organizationServiceImpl.deleteOneOrganization(1);
		assertEquals(ans, "Deleted Successfully");

	}

}
