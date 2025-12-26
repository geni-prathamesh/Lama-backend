package repositories.qualitative;

import dtos.qualitative.IndustriRiskDto;
import entities.qualitative.IndustriRisk;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IndustriRiskRepo extends MongoRepository<IndustriRisk, Long> {

    List<IndustriRisk>findAll();
    List<IndustriRisk>findByBorrowerId(Integer borrowerId);

}
