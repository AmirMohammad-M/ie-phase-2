package ir.ac.sbu.ie.project2.Phase2.service;

import ir.ac.sbu.ie.project2.Phase2.model.Case;
import ir.ac.sbu.ie.project2.Phase2.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImpl implements CaseService{
    @Autowired
    CaseRepository caseRepository;

    @Override
    public void saveCase(Case userCase) {
        caseRepository.save(userCase);
    }
}
