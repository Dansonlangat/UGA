package net.UGA.springboot.Service;

import net.UGA.springboot.dto.BagDto;
import net.UGA.springboot.model.Bag;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


public interface BagService {
    List<Bag> getAllBags();
    Long  saveBag(Bag bag,Long passengerId);
    ResponseEntity<?> saveBag(List<Bag> bag);
    Bag getBagById(long id);
    ResponseEntity<?> getBagByCode(long code);
    void deleteBagById(long id);

    ResponseEntity<?> listOfBags(Boolean collected);
//     List<Bag> getAllBagsWithTrueBooleanField();
    Page<Bag> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    Page<Bag> findCollectedPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    ResponseEntity<?> updateLuggage(BagDto bagDto);
}
