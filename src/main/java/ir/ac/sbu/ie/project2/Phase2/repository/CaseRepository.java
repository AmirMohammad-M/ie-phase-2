package ir.ac.sbu.ie.project2.Phase2.repository;

import ir.ac.sbu.ie.project2.Phase2.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {
}
