package entities.qualitative;

import enums.Classification;
import enums.CurrencyType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowerDetails {
    private String name;
    private CurrencyType currencyType;
    private String currency;
    private Classification classification;
    private LocalDate assessmentDate;
    private String relationshipManager;
    private String industry;
}
