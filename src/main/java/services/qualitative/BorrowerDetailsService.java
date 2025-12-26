package services.qualitative;

import dtos.qualitative.BorrowerDetailsDto;
import entities.qualitative.BorrowerDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BorrowerDetailsService {

    List<BorrowerDetailsDto>findAll();
    List<BorrowerDetailsDto>findByBorrowerId(Integer borrowerId);
    BorrowerDetailsDto createBorrowerDetails(BorrowerDetailsDto borrowerDetailsDto);
    List<BorrowerDetailsDto>updateByBorrowerId(Integer borrowerId, BorrowerDetails borrowerDetails);
    void deleteByBorrowerId(Integer borrowerId);

}
