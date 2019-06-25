package ir.ac.sbu.ie.project2.Phase2.repository;

import ir.ac.sbu.ie.project2.Phase2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);

    @Modifying
    @Query("update User u set u.name = :name,  u.lastName = :lastName, u.password = :password where u.email = :email")
    @Transactional
    void updateUserByEmail(@Param("email") String email, @Param("name") String name, @Param("lastName") String lastName, @Param("password") String password);
}
