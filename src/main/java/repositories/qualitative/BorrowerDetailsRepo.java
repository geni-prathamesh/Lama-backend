package repositories.qualitative;

import entities.qualitative.BorrowerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BorrowerDetailsRepo extends MongoRepository<BorrowerDetails, Long> {
    List<BorrowerDetails> findAll();
    List<BorrowerDetails>findByBorrowerId(Integer id);
}
