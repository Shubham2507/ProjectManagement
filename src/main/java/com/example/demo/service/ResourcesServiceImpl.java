package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			for (Projects projects2 : projects) {
				ResProDto resProDto = new ResProDto();
				resProDto.setId(projects2.getProject_id());
				resProDto.setName(projects2.getName());
				if (alc == 1) {
					resProDto.setAllocation("100%");
				} else {
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
	public String deleteOneDesignation(int resourceId) {
		Optional<Resources> resources = resourcesRepo.findById(resourceId);
		if (!resources.isPresent())
			return "No such id Exists!!";
		else {
			resourcesRepo.deleteById(resourceId);

			return "Deleted Successfully";
		}
	}

	@Override
	public Object updateResources(Resources resources) {
		Optional<Resources> resources2 = resourcesRepo.findById(resources.getResource_id());
		Resources resources4 = resources2.get();
		Resources resources3 = null;
		if (resources2 == null)
			return "No Such Resource Exists!!";
		else {
			resources4.setDesignation(resources.getDesignation());
			resources3 = resourcesRepo.save(resources4);
		}

		return resources3;
	}

}
