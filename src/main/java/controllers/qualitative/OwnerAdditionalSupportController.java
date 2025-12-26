package controllers.qualitative;

import dtos.qualitative.OwnerAdditionalSupportDto;
import entities.qualitative.OwnerAdditionalSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.qualitative.OwnerAdditionalSupportService;

import java.util.List;

@RestController
@RequestMapping("/api/owneradsupport")
public class OwnerAdditionalSupportController {

    private OwnerAdditionalSupportService ownerAdditionalSupportService;

    OwnerAdditionalSupportController(OwnerAdditionalSupportService ownerAdditionalSupportService){
        this.ownerAdditionalSupportService=ownerAdditionalSupportService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OwnerAdditionalSupportDto>> getAll() {
        List<OwnerAdditionalSupportDto> result = ownerAdditionalSupportService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/byId/{borrowerId}")
    public ResponseEntity<List<OwnerAdditionalSupportDto>> getByBorrowerId(
            @PathVariable Integer borrowerId
    ) {
        List<OwnerAdditionalSupportDto> result = ownerAdditionalSupportService.findByBorrowerId(borrowerId);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<OwnerAdditionalSupportDto> create(
            @RequestBody OwnerAdditionalSupportDto dto
    ) {
        OwnerAdditionalSupportDto saved = ownerAdditionalSupportService.createOwnerAdditionSupport(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/update/{borrowerId}")
    public ResponseEntity<List<OwnerAdditionalSupportDto>> updateByBorrowerId(
            @PathVariable Integer borrowerId,
            @RequestBody OwnerAdditionalSupport ownerAdditionalSupport
    ) {
        List<OwnerAdditionalSupportDto> updated =
                ownerAdditionalSupportService.updateByBorrowerId(borrowerId, ownerAdditionalSupport);
        if (updated.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/delete/{borrowerId}")
    public ResponseEntity<Void> deleteByBorrowerId(
            @PathVariable Integer borrowerId
    ) {
        ownerAdditionalSupportService.deleteByBorrowerId(borrowerId);
        return ResponseEntity.noContent().build();
    }


}
