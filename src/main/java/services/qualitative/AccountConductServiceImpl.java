package services.qualitative;

import dtos.qualitative.AccountConductDto;
import entities.qualitative.AccountConduct;
import entities.qualitative.QualitativeInput;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.stream.Collectors;

public class AccountConductServiceImpl implements AccountConductService{

    private MongoTemplate mongoTemplate;

    AccountConductServiceImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    private AccountConductDto toDto(AccountConduct accountConduct){
        AccountConductDto dto =new AccountConductDto();
        dto.setBounceCheques(accountConduct.getBounceCheques());
        dto.setWriteOff(accountConduct.getWriteOff());
        dto.setFraudLitigation(accountConduct.getFraudLitigation());
        dto.setDelinquencyHistory(accountConduct.getDelinquencyHistory());
        dto.setDelayInInstallments(accountConduct.getDelayInInstallments());
        dto.setOngoingCreditRelationship(accountConduct.getOngoingCreditRelationship());
        return dto;
    }

    private AccountConduct toEntity(AccountConductDto accountConductDto){
        AccountConduct accountConduct=new AccountConduct();
                accountConduct.setBounceCheques(accountConductDto.getBounceCheques());
                accountConduct.setWriteOff(accountConductDto.getWriteOff());
                accountConduct.setFraudLitigation(accountConductDto.getFraudLitigation());
                accountConduct.setDelinquencyHistory(accountConductDto.getDelinquencyHistory());
                accountConduct.setOngoingCreditRelationship(accountConductDto.getOngoingCreditRelationship());
                accountConduct.setDelayInInstallments(accountConductDto.getDelayInInstallments());
                return accountConduct;
    }


    @Override
    public List<AccountConductDto> findAll() {

        return mongoTemplate.findAll(AccountConduct.class).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AccountConductDto> findByBorrowerId(Integer borrowerId) {
        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );
        return mongoTemplate.find(query,AccountConduct.class).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AccountConductDto> updateByBorrowerId(Integer borrowerId, AccountConduct accountConduct) {

        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );

        Update update=new Update()
                .set("accountConduct.bounceCheques",accountConduct.getBounceCheques())
                .set("accountConduct.ongoingCreditRelationship",accountConduct.getOngoingCreditRelationship())
                .set("accountConduct.delayInInstallments",accountConduct.getDelayInInstallments())
                .set("accountConduct.delinquencyHistory",accountConduct.getDelinquencyHistory())
                .set("accountConduct.writeOff",accountConduct.getWriteOff())
                .set("accountConduct.fraudLitigation",accountConduct.getFraudLitigation());

        mongoTemplate.updateMulti(query,update, QualitativeInput.class);

        return mongoTemplate.find(query,QualitativeInput.class).stream().map(q->toDto(q.getAccountConduct())).collect(Collectors.toList());
    }

    @Override
    public AccountConductDto createAccountConduct(AccountConductDto accountConductDto) {

        QualitativeInput qualitativeInput=new QualitativeInput();
        qualitativeInput.setAccountConduct(toEntity(accountConductDto));
        mongoTemplate.insert(qualitativeInput);
        return accountConductDto;
    }

    @Override
    public void deleteByBorrowerId(Integer borrowerId) {
        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );
        mongoTemplate.remove(query,QualitativeInput.class);

    }
}
