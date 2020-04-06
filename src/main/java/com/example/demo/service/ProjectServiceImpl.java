package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
			Integer alc = resources.size();
			for (Resources resources2 : resources) {
				ResProDto proDto = new ResProDto();
				proDto.setId(resources2.getResource_id());
				proDto.setName(resources2.getName());
				if (alc == 1) {
					proDto.setAllocation("100%");
				} else {
					proDto.setAllocation("50%");
				}
				resProDtos.add(proDto);

			}
			projectDao.setResources(resProDtos);
			projectDaos.add(projectDao);
		}
		return projectDaos;
	}

	@Override
	public String deleteOneProject(int projectId) {
		Optional<Projects> projects = projectRepo.findById(projectId);
		if (!projects.isPresent())
			return "No such id Exists!!";
		else {
			projectRepo.deleteById(projectId);

			return "Deleted Successfully";
		}
	}

	@Override
	public Object addItemToProjects(int id, Projects projectsObj) {
		Object obj = null;
		int flag = 0;
		Projects projects = new Projects();
		int usedCap = 0;

		projects.setAllocated_capital(projectsObj.getAllocated_capital());
		projects.setName(projectsObj.getName());
		projects.setResources(projectsObj.getResources());
		Set<Resources> resc = projectsObj.getResources();
		for (Resources res : resc) {
			List<Projects> projects2 = projectRepo.findAllByResources(res);
			Integer alc = projects2.size();
			if (alc == 2) {
				obj = "Resource with id " + res.getResource_id()
						+ " is already assigned to 2 projects.Choose another resource";
				flag = 1;
			} else {

				Resources resources = resourcesRepo.getOne(res.getResource_id());
				usedCap = usedCap + (resources.getDesignation().getCapital());
			}
		}
		if (flag == 0) {

			projects.setUsed_capital(usedCap);
			Organization organization = organizationRepo.getOne(id);
			projects.setOrganization(organization);
			obj = projectRepo.save(projects);
			int totCoos = organization.getTotal_cost();
			totCoos = totCoos + projects.getAllocated_capital();
			organization.setTotal_cost(totCoos);
			IOrganizationService.updateOrganization(organization);
		}
		return obj;

	}

	@Override
	public Object updateProjects(Projects projects) {
		Optional<Projects> projects2 = projectRepo.findById(projects.getAllocated_capital());
		Projects projects4 = projects2.get();
		Projects projects3 = null;
		if (!projects2.isPresent())
			return "No Such Project Exists!!";
		else {
			projects4.setUsed_capital(projects.getUsed_capital());
			projects3 = projectRepo.save(projects4);
		}
		return projects3;
	}

	@Override
	public List<Projects> getAll() {
		
		return projectRepo.findAll();
	}

}
