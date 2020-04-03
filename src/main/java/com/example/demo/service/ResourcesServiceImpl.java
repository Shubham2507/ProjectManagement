package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.dao.ResourcesDao;
import com.example.demo.dto.ResProDto;
import com.example.demo.entity.Designation;
import com.example.demo.entity.Projects;
import com.example.demo.entity.Resources;
import com.example.demo.repo.DesignationRepo;
import com.example.demo.repo.ProjectRepo;
import com.example.demo.repo.ResourcesRepo;

@EnableWebMvc
@Service("resourcesService")
public class ResourcesServiceImpl implements IResourcesService {
	@Autowired
	private DesignationRepo designationRepo;
	@Autowired
	private ResourcesRepo resourcesRepo;
	@Autowired
	private ProjectRepo projectRepo;

	@Override
	public Resources addItemToResources(int id, Resources resources) {
		Designation des = designationRepo.getOne(id);
		resources.setDesignation(des);
		Resources resources2 = resourcesRepo.save(resources);

		return resources2;
	}

	@Override
	public List<ResourcesDao> getAllResources() {
		List<ResourcesDao> resourcesDaos = new ArrayList<ResourcesDao>();
		List<Resources> resources = resourcesRepo.findAll();

		for (Resources res : resources) {
			List<ResProDto> resProDtos = new ArrayList<ResProDto>();
			ResourcesDao resourcesDao = new ResourcesDao();
			resourcesDao.setResource_id(res.getResource_id());
			resourcesDao.setName(res.getName());
			resourcesDao.setDesignation(res.getDesignation().getName());
			
			List<Projects> projects = projectRepo.findAllByResources(res);
			Integer alc = projects.size();
			for (Projects projects2 : projects)
			{
				ResProDto resProDto = new ResProDto();
				resProDto.setId(projects2.getProject_id());
				resProDto.setName(projects2.getName());
				if(alc==1) {
				resProDto.setAllocation("100%");
				}
				else {
				resProDto.setAllocation("50%");
				}
				
				resProDtos.add(resProDto);

			}
			resourcesDao.setProject(resProDtos);
			resourcesDaos.add(resourcesDao);

		}
		return resourcesDaos;
	}

	@Override
	public String deleteOneDesignation(int designationId) {
		resourcesRepo.delete(resourcesRepo.getOne(designationId));
		return "Deleted Successfully";
	}

	@Override
	public Resources updateResources(Resources resources) {
		Resources resources2 = resourcesRepo.getOne(resources.getResource_id());
		resources2.setDesignation(resources.getDesignation());
		Resources resources3 = resourcesRepo.save(resources2);

		return resources3;
	}

}
