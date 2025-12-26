package services.qualitative;

import dtos.qualitative.OwnerAdditionalSupportDto;
import entities.qualitative.OwnerAdditionalSupport;
import entities.qualitative.QualitativeInput;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.stream.Collectors;

public class OwnerAdditionalSupportServiceImpl implements OwnerAdditionalSupportService{

    private MongoTemplate mongoTemplate;

    OwnerAdditionalSupportServiceImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    private OwnerAdditionalSupportDto toDto(OwnerAdditionalSupport ownerAdditionalSupport){

        OwnerAdditionalSupportDto dto=new OwnerAdditionalSupportDto();
        dto.setPersonalNetWorthScore(ownerAdditionalSupport.getPersonalNetWorthScore());
        return dto;
    }

    private OwnerAdditionalSupport toEntity(OwnerAdditionalSupportDto ownerAdditionalSupportDto){
        OwnerAdditionalSupport ownerAdditionalSupport=new OwnerAdditionalSupport();
        ownerAdditionalSupport.setPersonalNetWorthScore(ownerAdditionalSupportDto.getPersonalNetWorthScore());
        return ownerAdditionalSupport;
    }

    @Override
    public List<OwnerAdditionalSupportDto> findAll() {
        return mongoTemplate.findAll(OwnerAdditionalSupport.class).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OwnerAdditionalSupportDto> findByBorrowerId(Integer borrowerId) {

        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );
        return mongoTemplate.find(query, OwnerAdditionalSupport.class).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OwnerAdditionalSupportDto> updateByBorrowerId(Integer borrowerId, OwnerAdditionalSupport ownerAdditionalSupport) {

        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );

        Update update=new Update();
        update.set("ownerAdditionalSupport.personalNetWorthScore",ownerAdditionalSupport.getPersonalNetWorthScore());
        mongoTemplate.updateMulti(query,update, QualitativeInput.class);
        return mongoTemplate.find(query, QualitativeInput.class).stream().map(q -> toDto(q.getOwnerAdditionalSupport())).collect(Collectors.toList());
    }

    @Override
    public OwnerAdditionalSupportDto createOwnerAdditionSupport(OwnerAdditionalSupportDto ownerAdditionalSupportDto) {

        QualitativeInput qualitativeInput=new QualitativeInput();
        qualitativeInput.setOwnerAdditionalSupport(toEntity(ownerAdditionalSupportDto));
        mongoTemplate.insert(qualitativeInput);

        return ownerAdditionalSupportDto;
    }

    @Override
    public void deleteByBorrowerId(Integer borrowerId) {

        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );
        mongoTemplate.remove(query, QualitativeInput.class);

    }
}
