package net.UGA.springboot.Service;

import net.UGA.springboot.dto.BagDto;
import net.UGA.springboot.dto.NewPassengerEntry;
import net.UGA.springboot.dto.PassengerDto;
import net.UGA.springboot.model.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import net.UGA.springboot.Repository.PassengerRepository;

import net.UGA.springboot.model.Passenger;

import javax.transaction.Transactional;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BagService bagService;

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Long savePassenger(Passenger passenger) {
        return this.passengerRepository.save(passenger).getId();

    }

    @Override
    public ResponseEntity<?> savePassenger(NewPassengerEntry newPassengerEntry) {
        try {
            PassengerDto passenger = newPassengerEntry.getPassengerDto();
            Passenger passenger1 = new Passenger();
            passenger1.setDestination(passenger.getDestination());
            passenger1.setFirstName(passenger.getFirstName());
            passenger1.setLastName(passenger.getLastName());
            passenger1.setNationality(passenger.getNationality());
            passenger1.setFlightNumber(passenger.getFlightNumber());

            Long id = this.savePassenger(passenger1);

            List<BagDto> list = newPassengerEntry.getBagDtos();
            List<Bag> bagList = new ArrayList<>();
            for (BagDto bagDto : list) {
                Bag bag = new Bag();
                bag.setBagColor(bagDto.getBagColor());
                bag.setCode(bagDto.getCode());
                bag.setBagSize(bagDto.getBagSize());
                bag.setBagWeight(bagDto.getBagWeight());
                bag.setPassenger(getPassengerById(id));
                bagList.add(bag);
            }
            return bagService.saveBag(bagList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Passenger getPassengerById(long id) {
        Optional<Passenger> optional = passengerRepository.findById(id);
        Passenger passenger = null;
        if (optional.isPresent()) {
            passenger = optional.get();
        } else {
            throw new RuntimeException(" Passenger not found for id :: " + id);
        }
        return passenger;
    }

    @Override
    public ResponseEntity<?> updatePassenger(PassengerDto passengerDto) {

        Optional<Passenger> optional =   passengerRepository.findById(passengerDto.getId());
        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger Not found");
        }
        Passenger currentPassenger = optional.get();
        Passenger passenger1 = new Passenger();
        passenger1.setFirstName(passengerDto.getFirstName());
        passenger1.setLastName(passengerDto.getLastName());
        passenger1.setNationality(passengerDto.getNationality());
        passenger1.setDestination(passengerDto.getDestination());
        passenger1.setFlightNumber(passengerDto.getFlightNumber());
        passenger1.setId(passengerDto.getId());
        return ResponseEntity.ok(passengerRepository.save(passenger1).getId());
    }



    @Override
    public void deletePassengerById(long id) {
        this.passengerRepository.deleteById(id);
    }

    @Override
    public Page<Passenger> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.passengerRepository.findAll(pageable);
    }
}

