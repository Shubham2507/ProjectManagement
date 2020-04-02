package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.dao.ProjectDao;
import com.example.demo.dto.ResProDto;
import com.example.demo.entity.Organization;
import com.example.demo.entity.Projects;
import com.example.demo.entity.Resources;
import com.example.demo.repo.OrganizationRepo;
import com.example.demo.repo.ProjectRepo;
import com.example.demo.repo.ResourcesRepo;

@EnableWebMvc
@Service("projectService")
public class ProjectServiceImpl implements IProjectService {
	@Autowired
	private ProjectRepo projectRepo;
	@Autowired
	private OrganizationRepo organizationRepo;
	@Autowired
	private IOrganizationService IOrganizationService;
	@Autowired
	private ResourcesRepo resourcesRepo;

	@Override
	public List<ProjectDao> getAllProjects() {
		List<ProjectDao> projectDaos = new ArrayList<ProjectDao>();
		List<Projects> projects = projectRepo.findAll();
		for (Projects pro : projects) {
			ProjectDao projectDao = new ProjectDao();
			projectDao.setProject_id(pro.getProject_id());
			projectDao.setName(pro.getName());
			projectDao.setAllocated_capital(pro.getAllocated_capital());
			projectDao.setUsed_capital(pro.getUsed_capital());
			List<ResProDto> resProDtos = new ArrayList<ResProDto>();
			Set<Resources> resources = pro.getResources();
			for (Resources resources2 : resources)
			{
				ResProDto proDto = new ResProDto();
				proDto.setId(resources2.getResource_id());
				proDto.setName(resources2.getName());
				resProDtos.add(proDto);

			}
			projectDao.setResources(resProDtos);
			projectDaos.add(projectDao);
		}
		return projectDaos;
	}

	@Override
	public String deleteOneProject(int projectId) {
		projectRepo.deleteById(projectId);
		return "Deletion Successfully";
	}

	@Override
	public Projects addItemToProjects(int id, Projects projectsObj) {
		Projects projects = new Projects();
		int usedCap = 0;

		projects.setAllocated_capital(projectsObj.getAllocated_capital());
		projects.setName(projectsObj.getName());
		projects.setResources(projectsObj.getResources());
		Set<Resources> resc = projectsObj.getResources();
		for (Resources res : resc) {
			Resources resources = resourcesRepo.getOne(res.getResource_id());
			usedCap = usedCap + (resources.getDesignation().getCapital());

		}
		projects.setUsed_capital(usedCap);
		Organization organization = organizationRepo.getOne(id);
		projects.setOrganization(organization);
		Projects projects2 = projectRepo.save(projects);
		int totCoos = organization.getTotal_cost();
		totCoos = totCoos + projects.getAllocated_capital();
		organization.setTotal_cost(totCoos);
		IOrganizationService.updateOrganization(organization);
		return projects2;

	}

	@Override
	public Projects updateProjects(Projects projects) {
		Projects projects2 = projectRepo.getOne(projects.getAllocated_capital());
		projects2.setUsed_capital(projects.getUsed_capital());
		Projects projects3 = projectRepo.save(projects2);
		return projects3;
	}

}
