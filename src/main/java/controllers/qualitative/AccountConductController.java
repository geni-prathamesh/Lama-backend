package controllers.qualitative;

import dtos.qualitative.AccountConductDto;
import entities.qualitative.AccountConduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.qualitative.AccountConductService;

import java.util.List;

@RestController
@RequestMapping("/api/account-conduct")
public class AccountConductController {

    @Autowired
    private AccountConductService accountConductService;

    AccountConductController(AccountConductService accountConductService){
        this.accountConductService=accountConductService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountConductDto>>getAllAccountConduct(){
        List<AccountConductDto>list=accountConductService.findAll();
        return ResponseEntity.ok(list);

    }
    @GetMapping("/by-id/{borrowerId}")
    public ResponseEntity<List<AccountConductDto>>getByBorrowerId(@PathVariable Integer borrowerId){
        List<AccountConductDto>list=accountConductService.findByBorrowerId(borrowerId);

        if(list.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
    @PostMapping("/create")
    public ResponseEntity<AccountConductDto>createAccountConduct(@RequestBody AccountConductDto accountConductDto){
        AccountConductDto saved=accountConductService.createAccountConduct(accountConductDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{borrowerId}")
    public  ResponseEntity<List<AccountConductDto>>updateByBorrowerId(@PathVariable Integer borrowerId,@RequestBody AccountConduct accountConduct){

        List<AccountConductDto>updated=accountConductService.updateByBorrowerId(borrowerId,accountConduct);

        if(updated.isEmpty()){
           return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{borrowerId}")
    public ResponseEntity<Void>deleteByBorrowerId(@PathVariable Integer borrowerId){
        accountConductService.deleteByBorrowerId(borrowerId);
        return ResponseEntity.noContent().build();
    }

}
