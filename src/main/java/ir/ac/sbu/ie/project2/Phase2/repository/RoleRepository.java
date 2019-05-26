package ir.ac.sbu.ie.project2.Phase2.repository;

import ir.ac.sbu.ie.project2.Phase2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
}
