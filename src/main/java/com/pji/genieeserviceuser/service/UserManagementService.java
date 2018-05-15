package com.pji.genieeserviceuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pji.genieeserviceuser.dto.RoleDTO;
import com.pji.genieeserviceuser.dto.UserDTO;
import com.pji.genieeserviceuser.model.Role;
import com.pji.genieeserviceuser.model.User;
import com.pji.genieeserviceuser.repository.RoleRepository;
import com.pji.genieeserviceuser.repository.UserRepository;

import java.util.*;

@Service
@Transactional
public class UserManagementService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public UserDTO authenticateUser(String email, String password) {
		UserDTO userDTO = null;
		User user = userRepository.findByEmail(email);
		if (user != null) {
			if (user.getPasswordHash() == password.hashCode()) {
				userDTO = convertUserToUserDTO(user);
			}
		}
		return userDTO;
	}

	private Collection<RoleDTO> convertRolesToRoleDTOs(Collection<Role> roles) {
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		for (Role role : roles) {
			roleDTOs.add(convertRoleToRoleDTO(role));
		}
		return roleDTOs;
	}

	private Collection<Integer> convertRolesToRoleIds(Collection<Role> roles) {
		List<Integer> roleIds = new ArrayList<>();
		for (Role userRole : roles) {
			roleIds.add(userRole.getId());
		}
		return roleIds;
	}

	private RoleDTO convertRoleToRoleDTO(Role role) {
		return new RoleDTO(role.getId(), role.getName());
	}

	private List<UserDTO> convertUsersToUserDTOs(Collection<User> users) {
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(convertUserToUserDTO(user));
		}
		return userDTOs;
	}

	private UserDTO convertUserToUserDTO(User user) {
		Collection<Integer> roleIds = convertRolesToRoleIds(user.getRoles());
		return new UserDTO(user.getId(), user.getEmail(), roleIds);
	}

	public RoleDTO createRole(RoleDTO roleDTO) {
		Role role = new Role(roleDTO.name);
		role = roleRepository.save(role);
		return convertRoleToRoleDTO(role);
	}

	public UserDTO createUser(UserDTO userDTO) {
		Set<Role> roles = new HashSet<>(roleRepository.findAllById(userDTO.roleIds));
		User user = new User(userDTO.email, userDTO.password, roles);
		user = userRepository.save(user);
		userDTO = convertUserToUserDTO(user);
		return userDTO;
	}

	public void deleteRole(int id) {
		Optional<Role> role = roleRepository.findById(id);
		role.ifPresent(role1 -> roleRepository.delete(role1));
	}

	public void deleteUser(int id) {
		Optional<User> user = userRepository.findById(id);
		user.ifPresent(user1 -> userRepository.delete(user1));
	}

	public Collection<RoleDTO> findAllRoles() {
		List<Role> roles = roleRepository.findAll();
		Collection<RoleDTO> roleDTOs = convertRolesToRoleDTOs(roles);
		return roleDTOs;
	}

	public Collection<RoleDTO> findAllRolesForUser(int id) {
		Collection<Role> roles = new HashSet<>();
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			roles = user.get().getRoles();
		}
		Collection<RoleDTO> roleDTOs = convertRolesToRoleDTOs(roles);
		return roleDTOs;
	}

	public List<UserDTO> findAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTOs = convertUsersToUserDTOs(users);
		return userDTOs;
	}

	public RoleDTO findRoleById(int id) {
		return convertRoleToRoleDTO(roleRepository.findById(id).get());
	}

	public UserDTO findUserById(int id) {
		return convertUserToUserDTO(userRepository.findById(id).get());
	}

	public RoleDTO updateRole(int id, RoleDTO roleDTO) {
		Role role = roleRepository.findById(id).get();
		// TODO: make a converter for RoleDTO to Role
		role.setName(roleDTO.name);
		return convertRoleToRoleDTO(roleRepository.save(role));
	}

	public UserDTO updateUser(int id, UserDTO userDTO) {
		User user = userRepository.findById(id).get();
		// TODO: make a converter for UserDTO to User
		Set<Role> roles = new HashSet<>(roleRepository.findAllById(userDTO.roleIds));
		user.setEmail(userDTO.email);
		// Only set the password if it was passed in
		if (userDTO.password != null) {
			user.setPassword(userDTO.password);
		}
		user.setRoles(roles);
		user = userRepository.save(user);
		return convertUserToUserDTO(user);
	}
}
