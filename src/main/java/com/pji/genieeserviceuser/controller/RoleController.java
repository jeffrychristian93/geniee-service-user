package com.pji.genieeserviceuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pji.genieeserviceuser.dto.RoleDTO;
import com.pji.genieeserviceuser.service.UserManagementService;

import java.util.Collection;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(method = RequestMethod.POST)
	public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
		return userManagementService.createRole(roleDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteRole(@PathVariable int id) {
		userManagementService.deleteRole(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<RoleDTO> findAllRoles() {
		return userManagementService.findAllRoles();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RoleDTO findRoleById(@PathVariable int id) {
		return userManagementService.findRoleById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
		roleDTO = userManagementService.updateRole(id, roleDTO);
	}

}
