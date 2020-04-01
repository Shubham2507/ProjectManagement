package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.entity.Designation;
import com.example.demo.repo.DesignationRepo;

@EnableWebMvc
@Service("designationService")
public class DesignationServiceImpl implements IDesignatonService {
	@Autowired
	private DesignationRepo designationRepo;

	@Override
	public List<Designation> getAllDesignation() {
		// TODO Auto-generated method stub
		return designationRepo.findAll();
	}

	@Override
	public Designation addItemToDesignation(Designation designationObj) {

		Designation output = designationRepo.save(designationObj);

		return output;
	}

	@Override
	public String deleteOneDesignation(int designationId) {
		designationRepo.delete(designationRepo.getOne(designationId));

		return "Deleted Successfully";
	}

	@Override
	public Designation updateDesignation(Designation designation) {
		Designation designation2 = designationRepo.getOne(designation.getId());
		designation2.setCapital(designation.getCapital());
		Designation designation3 = designationRepo.save(designation2);
		return designation3;
	}

}
