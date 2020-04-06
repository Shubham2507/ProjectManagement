package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
		Optional<Designation> designation = designationRepo.findById(designationId);
		if (!designation.isPresent())
			return "No such id Exists!!";
		else {
			designationRepo.deleteById(designationId);

			return "Deleted Successfully";
		}
	}

	@Override
	public Object updateDesignation(Designation designation) {
		Optional<Designation> designation2 = designationRepo.findById(designation.getId());
		Designation designation4 = designation2.get();
		Designation designation3 = null;
		if (!designation2.isPresent())
			return "No Such Designation Exists!!";
		else {
			designation4.setCapital(designation.getCapital());
			designation3 = designationRepo.save(designation4);
		}
		return designation3;
	}

}
