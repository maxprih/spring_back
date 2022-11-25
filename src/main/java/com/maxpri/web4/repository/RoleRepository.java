package com.maxpri.web4.repository;

import java.util.Optional;

import com.maxpri.web4.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxpri.web4.models.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
