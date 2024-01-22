package net.UGA.springboot.Service;

import net.UGA.springboot.dto.BagDto;
import net.UGA.springboot.dto.NewPassengerEntry;
import net.UGA.springboot.dto.PassengerDto;
import net.UGA.springboot.model.Passenger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


public interface PassengerService {
	List<Passenger> getAllPassengers();
	Long savePassenger(Passenger passenger);
	ResponseEntity<?> savePassenger(NewPassengerEntry passenger);
	Passenger getPassengerById(long id);

	//ResponseEntity<?> updatePassenger(PassengerDto passengerDto);

	ResponseEntity<?> updatePassenger(PassengerDto passengerDto);
	void deletePassengerById(long id);
	Page<Passenger> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
