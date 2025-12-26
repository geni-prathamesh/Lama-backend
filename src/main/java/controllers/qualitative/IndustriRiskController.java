package controllers.qualitative;

import dtos.qualitative.IndustriRiskDto;
import entities.qualitative.IndustriRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.qualitative.IndustriRiskService;

import java.util.List;

@RestController
@RequestMapping("/api/industri-risk")
public class IndustriRiskController {

    @Autowired
    private IndustriRiskService industriRiskService;

    IndustriRiskController(IndustriRiskService industriRiskService){
        this.industriRiskService=industriRiskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<IndustriRiskDto>> getAll() {
        return ResponseEntity.ok(industriRiskService.findAll());
    }

    @GetMapping("/by-id/{borrowerId}")
    public ResponseEntity<List<IndustriRiskDto>> getByBorrowerId(
            @PathVariable Integer borrowerId) {

        List<IndustriRiskDto> data = industriRiskService.findByBorrowerId(borrowerId);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/create")
    public ResponseEntity<IndustriRiskDto> create(
            @RequestBody IndustriRiskDto industriRiskDto) {

        IndustriRiskDto created = industriRiskService.createIndustriRisk(industriRiskDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{borrowerId}")
    public ResponseEntity<List<IndustriRiskDto>> updateByBorrowerId(
            @PathVariable Integer borrowerId,
            @RequestBody IndustriRisk industriRisk) {

        List<IndustriRiskDto> updated =
                industriRiskService.updateByBorrowerId(borrowerId, industriRisk);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{borrowerId}")
    public ResponseEntity<Void> deleteByBorrowerId(
            @PathVariable Integer borrowerId) {

        industriRiskService.deleteByBorrowerId(borrowerId);
        return ResponseEntity.noContent().build();
    }

}
