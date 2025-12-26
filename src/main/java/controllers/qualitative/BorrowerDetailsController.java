package controllers.qualitative;

import dtos.qualitative.BorrowerDetailsDto;
import entities.qualitative.BorrowerDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.qualitative.BorrowerDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/borrower")
public class BorrowerDetailsController {

    private BorrowerDetailsService borrowerDetailsService;

    BorrowerDetailsController(BorrowerDetailsService borrowerDetailsService){
        this.borrowerDetailsService=borrowerDetailsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BorrowerDetailsDto>> getAllBorrowers() {
        List<BorrowerDetailsDto> borrowers = borrowerDetailsService.findAll();
        return ResponseEntity.ok(borrowers);
    }

    @GetMapping("/byId/{borrowerId}")
    public ResponseEntity<List<BorrowerDetailsDto>> getBorrowerById(
            @PathVariable Integer borrowerId) {

        List<BorrowerDetailsDto> borrower = borrowerDetailsService.findByBorrowerId(borrowerId);
        if (borrower.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(borrower);
    }

    @PostMapping("/create/{borrowerId}")
    public ResponseEntity<BorrowerDetailsDto> createBorrower(
            @PathVariable Integer borrowerId,
            @RequestBody BorrowerDetailsDto dto) {

        BorrowerDetailsDto created = borrowerDetailsService.createBorrowerDetails(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{borrowerId}")
    public ResponseEntity<List<BorrowerDetailsDto>> updateBorrower(
            @PathVariable Integer borrowerId,
            @RequestBody BorrowerDetails dto) {

        List<BorrowerDetailsDto> updated = borrowerDetailsService.updateByBorrowerId(borrowerId, dto);
        if (updated.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/delete/{borrowerId}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Integer borrowerId) {
        borrowerDetailsService.deleteByBorrowerId(borrowerId);
        return ResponseEntity.noContent().build();
    }
}
