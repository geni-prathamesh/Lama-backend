package repositories.qualitative;

import entities.qualitative.QualitativeInput;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QualitativeInputRepo extends MongoRepository {
    List<QualitativeInput>findAll();
    List<QualitativeInput>findByBorrowerId(Integer borrowerId);
}
