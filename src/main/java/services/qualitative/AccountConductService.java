package services.qualitative;

import dtos.qualitative.AccountConductDto;
import entities.qualitative.AccountConduct;
import entities.qualitative.QualitativeInput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountConductService {

    List<AccountConductDto> findAll();
    List<AccountConductDto>findByBorrowerId(Integer borrowerId);
    List<AccountConductDto>updateByBorrowerId(Integer borrowerId, AccountConduct accountConduct);
    AccountConductDto createAccountConduct(AccountConductDto accountConductDto);
    void deleteByBorrowerId(Integer borrowerId);
}
