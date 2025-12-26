package services.qualitative;

import dtos.qualitative.QualitativeInputDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QualitativeInputService {
    List<QualitativeInputDto> findAll();
    List<QualitativeInputDto>findByBorrowerId(Integer borrowerId);
    List<QualitativeInputDto>updateByBorrowerId(Integer borrowerId);

    QualitativeInputDto createQualitativeInput(QualitativeInputDto qualitativeInputDto);

}
