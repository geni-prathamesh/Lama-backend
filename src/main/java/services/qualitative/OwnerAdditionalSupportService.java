package services.qualitative;

import dtos.qualitative.OwnerAdditionalSupportDto;
import entities.qualitative.OwnerAdditionalSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerAdditionalSupportService {
    List<OwnerAdditionalSupportDto>findAll();
    List<OwnerAdditionalSupportDto>findByBorrowerId(Integer borrowerId);
    List<OwnerAdditionalSupportDto>updateByBorrowerId(Integer borrowerId, OwnerAdditionalSupport ownerAdditionalSupport);
    OwnerAdditionalSupportDto createOwnerAdditionSupport(OwnerAdditionalSupportDto ownerAdditionalSupportDto);
    void deleteByBorrowerId(Integer borrowerId);
}
