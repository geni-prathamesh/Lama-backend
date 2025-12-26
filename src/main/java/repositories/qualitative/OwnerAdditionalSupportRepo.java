package repositories.qualitative;

import entities.qualitative.OwnerAdditionalSupport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OwnerAdditionalSupportRepo extends MongoRepository<OwnerAdditionalSupport, Long> {
List<OwnerAdditionalSupport> findAll();
List<OwnerAdditionalSupport>findByBorrowerId(Integer borrowerId);


}
