package net.UGA.springboot.Service;

import net.UGA.springboot.model.Bag;
import java.util.List;

import org.springframework.data.domain.Page;


public interface BagService {
    List<Bag> getAllBags();
    Long  saveBag(Bag bag,Long passengerId);
    Bag getBagById(long id);
    void deleteBagById(long id);
    Page<Bag> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
