package net.UGA.springboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import net.UGA.springboot.Repository.BagRepository;
import net.UGA.springboot.Repository.PassengerRepository;
import net.UGA.springboot.model.Bag;
import net.UGA.springboot.model.Passenger;

import javax.transaction.Transactional;

@Service
@Transactional
public class BagServiceImpl implements BagService {

    @Autowired
    private BagRepository bagRepository;
    
    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public List<Bag> getAllBags() {
        return bagRepository.findAll();
    }

    @Override
    public Long saveBag(Bag bag,Long passengerId) {
    	System.out.println("Am here!!"+passengerId);
    	Optional<Passenger> passenger = passengerRepository.findById(passengerId) ;
    	if(!passenger.isPresent()) {
    		System.out.println("Passenger not found..!!");
    	//TODO  return error
    	}
    	
    	bag.setPassenger(passenger.get());
    	System.out.println("Passenger Id picked");
       return this.bagRepository.save(bag).getId();
    }

    @Override
    public ResponseEntity<?> saveBag(List<Bag> bags) {
        try {
            bagRepository.saveAll(bags);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Bag getBagById(long id) {
        Optional<Bag> optional = bagRepository.findById(id);
        Bag bag = null;
        if (optional.isPresent()) {
            bag = optional.get();
        } else {
            throw new RuntimeException(" Bag not found for id :: " + id);
        }
        return bag;
    }

    @Override
    public void deleteBagById(long id) {
        this.bagRepository.deleteById(id);
    }

    @Override
    public Page<Bag> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.bagRepository.findAll(pageable);
    }
}