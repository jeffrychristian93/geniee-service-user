package com.pji.genieeserviceuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pji.genieeserviceuser.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
