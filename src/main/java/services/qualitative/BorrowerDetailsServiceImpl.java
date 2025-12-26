package services.qualitative;

import dtos.qualitative.BorrowerDetailsDto;
import entities.qualitative.BorrowerDetails;
import entities.qualitative.QualitativeInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import repositories.qualitative.BorrowerDetailsRepo;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowerDetailsServiceImpl implements BorrowerDetailsService{

    private final MongoTemplate mongoTemplate;

    BorrowerDetailsServiceImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    private BorrowerDetailsDto toDto(BorrowerDetails borrowerDetails){
        BorrowerDetailsDto dto=new BorrowerDetailsDto();
//        dto.setBorrowerId(borrowerDetails.get);
        dto.setName(borrowerDetails.getName());
        dto.setIndustry(borrowerDetails.getIndustry());
        dto.setCurrency(borrowerDetails.getCurrency());
        dto.setClassification(borrowerDetails.getClassification());
        dto.setCurrencyType(borrowerDetails.getCurrencyType());
        dto.setAssessmentDate(borrowerDetails.getAssessmentDate());
        dto.setRelationshipManager(borrowerDetails.getRelationshipManager());
        return dto;
    }

    private BorrowerDetails toEntity(BorrowerDetailsDto dto){
        BorrowerDetails borrowerDetails=new BorrowerDetails();
        borrowerDetails.setName(dto.getName());
        borrowerDetails.setIndustry(dto.getIndustry());
        borrowerDetails.setCurrency(dto.getCurrency());
        borrowerDetails.setClassification(dto.getClassification());
        borrowerDetails.setAssessmentDate(dto.getAssessmentDate());
        borrowerDetails.setCurrencyType(dto.getCurrencyType());
        borrowerDetails.setRelationshipManager(dto.getRelationshipManager());
        return borrowerDetails;
    }


    @Override
    public List<BorrowerDetailsDto> findAll() {
        return mongoTemplate.findAll(BorrowerDetails.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<BorrowerDetailsDto> findByBorrowerId(Integer borrowerId) {

        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );
        return mongoTemplate.find(query, BorrowerDetails.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public BorrowerDetailsDto createBorrowerDetails(BorrowerDetailsDto borrowerDetailsDto) {

        QualitativeInput qualitativeInput=new QualitativeInput();
        qualitativeInput.setBorrowerId(qualitativeInput.getBorrowerId());
        qualitativeInput.setBorrowerDetails(toEntity(borrowerDetailsDto));
        mongoTemplate.insert(qualitativeInput);
        return borrowerDetailsDto;
    }



    @Override
    public List<BorrowerDetailsDto> updateByBorrowerId(Integer borrowerId, BorrowerDetails borrowerDetails) {

        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );

        Update update=new Update()
                .set("borrowerDetails.name",borrowerDetails.getName())
                .set("borrowerDetails.currencyType", borrowerDetails.getCurrencyType())
                .set("borrowerDetails.currency", borrowerDetails.getCurrency())
                .set("borrowerDetails.classification", borrowerDetails.getClassification())
                .set("borrowerDetails.assessmentDate", borrowerDetails.getAssessmentDate())
                .set("borrowerDetails.relationshipManager", borrowerDetails.getRelationshipManager())
                .set("borrowerDetails.industry", borrowerDetails.getIndustry());

        mongoTemplate.updateMulti(query,update,QualitativeInput.class);

        return mongoTemplate.find(query,QualitativeInput.class)
                .stream()
                .map(q -> toDto(q.getBorrowerDetails()))
                .toList();
    }

    @Override
    public void deleteByBorrowerId(Integer borrowerId) {
    Query query=new Query(
            Criteria.where("borrowerId").is(borrowerId)
    );

    mongoTemplate.remove(query, QualitativeInput.class);
    }
}
