package services.qualitative;

import dtos.qualitative.IndustriRiskDto;
import entities.qualitative.IndustriRisk;
import entities.qualitative.QualitativeInput;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.stream.Collectors;

public class IndustriRiskServiceImpl implements IndustriRiskService{

    private MongoTemplate mongoTemplate;

    IndustriRiskServiceImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    private IndustriRiskDto toDto(IndustriRisk industriRisk){
       IndustriRiskDto dto=new IndustriRiskDto();
       dto.setCompetitiveness(industriRisk.getCompetitiveness());
       dto.setCapitalSensitivity(industriRisk.getCapitalSensitivity());
       dto.setBusinessCyclicality(industriRisk.getBusinessCyclicality());
       dto.setIndustryStage(industriRisk.getIndustryStage());
       dto.setIndustryProfitability(industriRisk.getIndustryProfitability());
       dto.setEnvironmentalConcerns(industriRisk.getEnvironmentalConcerns());
       dto.setFiscalPolicyDependence(industriRisk.getFiscalPolicyDependence());
       dto.setFxSensitivity(industriRisk.getFxSensitivity());
       dto.setImportPenetration(industriRisk.getImportPenetration());
       dto.setIndustryFailureRate(industriRisk.getIndustryFailureRate());
       dto.setIndustrySalesTrend(industriRisk.getIndustrySalesTrend());
       dto.setInflationSensitivity(industriRisk.getInflationSensitivity());
       dto.setInterestRateSensitivity(industriRisk.getInterestRateSensitivity());
       dto.setSkilledLaborGap(industriRisk.getSkilledLaborGap());
       dto.setProductPositioning(industriRisk.getProductPositioning());
       dto.setTechnologyDependence(industriRisk.getTechnologyDependence());
       return dto;
    }

    private IndustriRisk toEntity(IndustriRiskDto industriRiskDto){
        IndustriRisk industriRisk=new IndustriRisk();
        industriRisk.setCompetitiveness(industriRiskDto.getCompetitiveness());
        industriRisk.setCapitalSensitivity(industriRiskDto.getCapitalSensitivity());
        industriRisk.setIndustryStage(industriRiskDto.getIndustryStage());
        industriRisk.setIndustryProfitability(industriRiskDto.getIndustryProfitability());
        industriRisk.setEnvironmentalConcerns(industriRiskDto.getEnvironmentalConcerns());
        industriRisk.setFiscalPolicyDependence(industriRiskDto.getFiscalPolicyDependence());
        industriRisk.setFxSensitivity(industriRiskDto.getFxSensitivity());
        industriRisk.setImportPenetration(industriRiskDto.getImportPenetration());
        industriRisk.setIndustryFailureRate(industriRiskDto.getIndustryFailureRate());
        industriRisk.setIndustrySalesTrend(industriRiskDto.getIndustrySalesTrend());
        industriRisk.setInflationSensitivity(industriRiskDto.getInflationSensitivity());
        industriRisk.setInterestRateSensitivity(industriRiskDto.getInterestRateSensitivity());
        industriRisk.setSkilledLaborGap(industriRiskDto.getSkilledLaborGap());
        industriRisk.setProductPositioning(industriRiskDto.getProductPositioning());
        industriRisk.setTechnologyDependence(industriRiskDto.getTechnologyDependence());
        return industriRisk;
    }
    @Override
    public List<IndustriRiskDto> findAll() {
        return mongoTemplate.findAll(IndustriRisk.class).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IndustriRiskDto> findByBorrowerId(Integer borrowerId) {
        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );
        return mongoTemplate.find(query,IndustriRisk.class).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<IndustriRiskDto> updateByBorrowerId(Integer borrowerId, IndustriRisk industriRisk) {
        Query query=new Query(
                Criteria.where("borrowerId").is(borrowerId)
        );
        Update update=new Update()
                .set("industriRisk.competitiveness",industriRisk.getCompetitiveness())
                .set("industriRisk.environmentalConcerns",industriRisk.getEnvironmentalConcerns())
                .set("industriRisk.fiscalPolicyDependence",industriRisk.getFiscalPolicyDependence())
                .set("industriRisk.businessCyclicality",industriRisk.getBusinessCyclicality())
                .set("industriRisk.inflationSensitivity",industriRisk.getInflationSensitivity())
                .set("industriRisk.fxSensitivity",industriRisk.getFxSensitivity())
                .set("industriRisk.interestRateSensitivity",industriRisk.getInterestRateSensitivity())
                .set("industriRisk.industrySalesTrend",industriRisk.getIndustrySalesTrend())
                .set("industriRisk.industryProfitability",industriRisk.getIndustryProfitability())
                .set("industriRisk.industryStage",industriRisk.getIndustryStage())
                .set("industriRisk.importPenetration",industriRisk.getImportPenetration())
                .set("industriRisk.industryFailureRate",industriRisk.getIndustryFailureRate())
                .set("industriRisk.skilledLaborGap",industriRisk.getSkilledLaborGap())
                .set("industriRisk.productPositioning",industriRisk.getProductPositioning())
                .set("industriRisk.capitalSensitivity",industriRisk.getCapitalSensitivity())
                .set("industriRisk.technologyDependence",industriRisk.getTechnologyDependence());

            mongoTemplate.updateMulti(query,update,QualitativeInput.class);

        return mongoTemplate.find(query,QualitativeInput.class).stream().map(q->toDto(q.getIndustriRisk())).collect(Collectors.toList());
    }

    @Override
    public IndustriRiskDto createIndustriRisk(IndustriRiskDto industriRiskDto) {
        QualitativeInput qualitativeInput=new QualitativeInput();
        qualitativeInput.setIndustriRisk(toEntity(industriRiskDto));
        mongoTemplate.insert(qualitativeInput);
        return industriRiskDto;
    }

    @Override
    public void deleteByBorrowerId(Integer borrowerId) {
    Query query=new Query(
            Criteria.where("borrowerId").is(borrowerId)
    );
    mongoTemplate.remove(query,QualitativeInput.class);
    }
}
