package repositories.qualitative;

import entities.qualitative.AccountStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountStatusRepo extends MongoRepository<AccountStatus, Long> {
    List<AccountStatus> findAll();
    List<AccountStatus>findByBorrowerId(Integer borrowerId);

}
