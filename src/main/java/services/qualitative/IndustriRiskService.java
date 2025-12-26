package services.qualitative;

import dtos.qualitative.IndustriRiskDto;
import entities.qualitative.IndustriRisk;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IndustriRiskService {
    List<IndustriRiskDto>findAll();
    List<IndustriRiskDto>findByBorrowerId(Integer borrowerId);
    List<IndustriRiskDto>updateByBorrowerId(Integer borrowerId, IndustriRisk industriRisk);
    IndustriRiskDto createIndustriRisk(IndustriRiskDto industriRiskDto);
    void deleteByBorrowerId(Integer borrowerId);
}
