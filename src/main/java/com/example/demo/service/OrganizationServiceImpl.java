package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.dao.OrganizatonDao;
import com.example.demo.dto.OrgProDto;
import com.example.demo.entity.Organization;
import com.example.demo.entity.Projects;
import com.example.demo.repo.OrganizationRepo;
import com.example.demo.repo.ProjectRepo;

@EnableWebMvc
@Service("organizationService")
public class OrganizationServiceImpl implements IOrganizationService {
	@Autowired
	private OrganizationRepo organizationRepo;
	@Autowired
	private ProjectRepo projectRepo;

	@Override
	public List<OrganizatonDao> getAllOrganization() {
		List<OrganizatonDao> daos = new ArrayList<OrganizatonDao>();
		List<Organization> organizatons = organizationRepo.findAll();
		for (Organization org : organizatons) {
			OrganizatonDao organizatonDao = new OrganizatonDao();
			organizatonDao.setId(org.getId());
			organizatonDao.setName(org.getName());
			organizatonDao.setTotal_cost(org.getTotal_cost());
			List<Integer> ids = projectRepo.findAllByOrgnization_Id(org.getId());
			List<OrgProDto> orgProDtos = new ArrayList<OrgProDto>();
			for (Integer ir : ids) {
				OrgProDto orgProDto = new OrgProDto();
				Projects pro = projectRepo.getOne(ir);
				orgProDto.setProjectName(pro.getName());
				orgProDtos.add(orgProDto);
			}
			organizatonDao.setProjects(orgProDtos);

		}
		return daos;
	}

	@Override
	public String deleteOneOrganization(int organizationId) {
		organizationRepo.deleteById(organizationId);
		return "Deleted Successfully";
	}

	@Override
	public Organization addItemToOrganization(Organization organizationObj) {
		Organization organizationob = new Organization();
		organizationob.setName(organizationObj.getName());
		organizationob.setTotal_cost(0);
		Organization organization = organizationRepo.save(organizationob);
		return organization;
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		Organization organization2 = organizationRepo.getOne(organization.getId());
		organization2.setTotal_cost(organization.getTotal_cost());
		Organization organization3 = organizationRepo.save(organization2);

		return organization3;
	}

}
