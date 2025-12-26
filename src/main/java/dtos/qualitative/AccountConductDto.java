package dtos.qualitative;

import lombok.Data;

@Data
public class AccountConductDto {
    private Integer bounceCheques;
    private Integer ongoingCreditRelationship;
    private Integer delayInInstallments;
    private Integer delinquencyHistory;
    private Integer writeOff;
    private Integer fraudLitigation;
}
