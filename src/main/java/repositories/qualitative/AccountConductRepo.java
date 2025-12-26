package repositories.qualitative;

import entities.qualitative.AccountConduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountConductRepo extends MongoRepository<AccountConduct,Long> {

    List<AccountConduct> findAll();
    List<AccountConduct>findByBorrowerId(Integer borrowerId);
}
