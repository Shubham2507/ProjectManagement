package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			List<Projects> ids = projectRepo.findAllByOrganization_Id(org.getId());
			List<OrgProDto> orgProDtos = new ArrayList<OrgProDto>();
			for (Projects ir : ids) {
				OrgProDto orgProDto = new OrgProDto();
				// Projects pro = projectRepo.getOne(ir);
				orgProDto.setProjectName(ir.getName());
				orgProDtos.add(orgProDto);
			}
			organizatonDao.setProjects(orgProDtos);
			daos.add(organizatonDao);

		}
		return daos;
	}

	@Override
	public String deleteOneOrganization(int organizationId) {
		Optional<Organization> organization1 = organizationRepo.findById(organizationId);
		if (!organization1.isPresent())
			return "No such id Exists!!";
		else {
			organizationRepo.deleteById(organizationId);

			return "Deleted Successfully";
		}
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
	public Object updateOrganization(Organization organization) {
		Optional<Organization> organization2 = organizationRepo.findById(organization.getId());
		Organization organization4 = organization2.get();
		Organization organization3 = null;
		if (!organization2.isPresent())
			return "NO Such Organization Exists!!";
		else {
			organization4.setTotal_cost(organization.getTotal_cost());
			organization3 = organizationRepo.save(organization4);
		}

		return organization3;
	}

}
