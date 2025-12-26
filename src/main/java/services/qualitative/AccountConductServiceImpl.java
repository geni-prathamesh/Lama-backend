package services.qualitative;

import dtos.qualitative.AccountConductDto;
import entities.qualitative.AccountConduct;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class AccountConductServiceImpl implements AccountConductService{

    private MongoTemplate mongoTemplate;

    AccountConductServiceImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }



    @Override
    public List<AccountConductDto> findAll() {

        return List.of();
    }

    @Override
    public List<AccountConductDto> findByBorrowerId(Integer borrowerId) {
        return List.of();
    }

    @Override
    public List<AccountConductDto> updateByBorrowerId(Integer borrowerId, AccountConduct accountConduct) {
        return List.of();
    }

    @Override
    public AccountConductDto createAccountConduct(AccountConductDto accountConductDto) {
        return null;
    }

    @Override
    public void deleteByBorrowerId(Integer borrowerId) {

    }
}
