package dtos.qualitative;

import entities.qualitative.*;
import lombok.Data;

@Data
public class QualitativeInputDto {

    private Integer borrowerId;
    private BorrowerDetails borrowerDetails;
    private OwnerAdditionalSupport ownerAdditionalSupport;
    private AccountStatus accountStatus;
    private AccountConduct accountConduct;
    private IndustriRisk industriRisk;

}
