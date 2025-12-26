package controllers.qualitative;

import dtos.qualitative.AccountStatusDto;
import entities.qualitative.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.qualitative.AccountStatusService;

import java.util.List;

@RestController
@RequestMapping("/api/account-status")
public class AccountStatusController {

@Autowired
    private AccountStatusService accountStatusService;

    AccountStatusController(AccountStatusService accountStatusService){
        this.accountStatusService=accountStatusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountStatusDto>> getAllAccountStatus() {
        List<AccountStatusDto> list = accountStatusService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/by-id/{borrowerId}")
    public ResponseEntity<List<AccountStatusDto>> getByBorrowerId(
            @PathVariable Integer borrowerId) {

        List<AccountStatusDto> list = accountStatusService.findByBorrowerId(borrowerId);

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<AccountStatusDto> createAccountStatus(
            @RequestBody AccountStatusDto accountStatusDto) {

        AccountStatusDto saved = accountStatusService.createStatus(accountStatusDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/borrower/{borrowerId}")
    public ResponseEntity<List<AccountStatusDto>> updateByBorrowerId(
            @PathVariable Integer borrowerId,
            @RequestBody AccountStatus accountStatus) {

        List<AccountStatusDto> updated =
                accountStatusService.updateByBorrowerId(borrowerId, accountStatus);

        if (updated.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/borrower/{borrowerId}")
    public ResponseEntity<Void> deleteByBorrowerId(
            @PathVariable Integer borrowerId) {

        accountStatusService.deleteByBorrowerId(borrowerId);
        return ResponseEntity.noContent().build();
    }



}
