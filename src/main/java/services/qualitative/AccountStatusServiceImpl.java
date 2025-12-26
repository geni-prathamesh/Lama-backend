package services.qualitative;

import dtos.qualitative.AccountStatusDto;
import entities.qualitative.AccountStatus;
import entities.qualitative.QualitativeInput;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.stream.Collectors;

public class AccountStatusServiceImpl implements AccountStatusService{

    private MongoTemplate mongoTemplate;

    AccountStatusServiceImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    private AccountStatusDto toDto(AccountStatus accountStatus){
        AccountStatusDto dto=new AccountStatusDto();
        dto.setAuditorOpinion(accountStatus.getAuditorOpinion());
        dto.setAuditorQuality(accountStatus.getAuditorQuality());
        dto.setLocationOfBusiness(accountStatus.getLocationOfBusiness());
        dto.setRelationshipAge(accountStatus.getRelationshipAge());
        dto.setYearInBusiness(accountStatus.getYearInBusiness());
        dto.setNationalizationScheme(accountStatus.getNationalizationScheme());
        return dto;
    }

    private AccountStatus toEntity(AccountStatusDto accountStatusDto){
        AccountStatus accountStatus=new AccountStatus();
        accountStatus.setAuditorOpinion(accountStatusDto.getAuditorOpinion());
        accountStatus.setAuditorQuality(accountStatusDto.getAuditorQuality());
        accountStatus.setRelationshipAge(accountStatusDto.getRelationshipAge());
        accountStatus.setYearInBusiness(accountStatusDto.getYearInBusiness());
        accountStatus.setNationalizationScheme(accountStatusDto.getNationalizationScheme());
        accountStatus.setLocationOfBusiness(accountStatusDto.getLocationOfBusiness());
        return accountStatus;
    }
    @Override
    public List<AccountStatusDto> findAll() {
        return mongoTemplate.findAll(AccountStatus.class).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AccountStatusDto> findByBorrowerId(Integer borrowerId) {

        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );
        return mongoTemplate.find(query,AccountStatus.class).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AccountStatusDto> updateByBorrowerId(Integer borrowerId, AccountStatus accountStatus) {
        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );

        Update update=new Update()
                .set("accountStatus.auditorOpinion",accountStatus.getAuditorOpinion())
                .set("accountStatus.auditorQuality",accountStatus.getAuditorQuality())
                .set("accountStatus.locationOfBusiness",accountStatus.getLocationOfBusiness())
                .set("accountStatus.relationshipAge", accountStatus.getRelationshipAge())
                .set("accountStatus.yearInBusiness", accountStatus.getYearInBusiness())
                .set("accountStatus.nationalizationScheme", accountStatus.getNationalizationScheme());
        mongoTemplate.updateMulti(query,update,QualitativeInput.class);

        return mongoTemplate.find(query, QualitativeInput.class).stream().map(q->toDto(q.getAccountStatus())).collect(Collectors.toList());
    }

    @Override
    public AccountStatusDto createStatus(AccountStatusDto accountStatusDto) {

        QualitativeInput qualitativeInput=new QualitativeInput();
        qualitativeInput.setAccountStatus(toEntity(accountStatusDto));
        mongoTemplate.insert(qualitativeInput);
        return accountStatusDto;
    }

    @Override
    public void deleteByBorrowerId(Integer borrowerId) {
    Query query=new Query(
            Criteria.where("borrowerId").is(borrowerId)
    );
    mongoTemplate.remove(query,QualitativeInput.class);
    }
}
