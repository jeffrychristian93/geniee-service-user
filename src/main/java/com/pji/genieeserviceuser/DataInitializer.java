package com.pji.genieeserviceuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.pji.genieeserviceuser.dto.RoleDTO;
import com.pji.genieeserviceuser.dto.UserDTO;
import com.pji.genieeserviceuser.service.UserManagementService;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private UserManagementService service;

	private Collection<Integer> roleDTOsToIdCollection(RoleDTO... roles) {
		Collection<Integer> idCollection = new ArrayList<>();
		for (RoleDTO role : roles) {
			idCollection.add(role.id);
		}
		return idCollection;
	}

	@Override
	public void run(String... arguments) throws Exception {
		// Create Roles
		RoleDTO advertiserRole = service.createRole(new RoleDTO("advertiser"));
		RoleDTO agencyARole = service.createRole(new RoleDTO("agency_a"));
		RoleDTO agencyBRole = service.createRole(new RoleDTO("agency_b"));
		RoleDTO agencyCRole = service.createRole(new RoleDTO("agency_d"));
		RoleDTO adminWebsiteRole = service.createRole(new RoleDTO("admin_website"));

		// Create Users
		UserDTO userA = service.createUser(new UserDTO("jeffry@gmail.com", "jeffry", roleDTOsToIdCollection(advertiserRole)));
		UserDTO userB = service.createUser(new UserDTO("agency1@gmail.com", "agency1", roleDTOsToIdCollection(agencyARole)));
		UserDTO userC = service.createUser(new UserDTO("agency2@gmail.com", "agency2", roleDTOsToIdCollection(agencyBRole)));
		UserDTO userD = service.createUser(new UserDTO("agency3@gmail.com", "agency3", roleDTOsToIdCollection(agencyCRole)));
		UserDTO userE = service.createUser(new UserDTO("admin@geniee.com", "admin", roleDTOsToIdCollection(adminWebsiteRole)));

		service.findAllRoles().forEach(System.out::println);
		service.findAllUsers().forEach(System.out::println);
	}
}
