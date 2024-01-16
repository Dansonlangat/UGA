package net.UGA.springboot.Service;

import net.UGA.springboot.model.Passenger;
import java.util.List;

import org.springframework.data.domain.Page;


public interface PassengerService {
	List<Passenger> getAllPassengers();
	void savePassenger(Passenger passenger);
	Passenger getPassengerById(long id);
	void deletePassengerById(long id);
	Page<Passenger> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
