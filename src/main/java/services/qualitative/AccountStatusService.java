package services.qualitative;

import dtos.qualitative.AccountStatusDto;
import entities.qualitative.AccountStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountStatusService {
    List<AccountStatusDto> findAll();
    List<AccountStatusDto>findByBorrowerId(Integer borrowerId);
    List<AccountStatusDto>updateByBorrowerId(Integer borrowerId, AccountStatus accountStatus);
    AccountStatusDto createStatus(AccountStatusDto accountStatusDto);
    void  deleteByBorrowerId (Integer borrowerId);

}
