package ir.ac.sbu.ie.project2.Phase2.repository;

import ir.ac.sbu.ie.project2.Phase2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
}
