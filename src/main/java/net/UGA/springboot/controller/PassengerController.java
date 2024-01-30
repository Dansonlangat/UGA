package net.UGA.springboot.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.UGA.springboot.Service.PassengerService;
import net.UGA.springboot.dto.BagDto;
import net.UGA.springboot.dto.NewPassengerEntry;
import net.UGA.springboot.dto.PassengerDto;
import net.UGA.springboot.model.Bag;
import net.UGA.springboot.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	// display list of passengers
	@GetMapping("/passenger")
	public String viewPassengerPage(Model model) {
		return findPaginated(1, "Id", "dsc", model);
	}

	@GetMapping("/showNewPassengerForm")
	public String showNewPassengerForm(Model model) {
		// create model attribute to bind form data
		Passenger passenger = new Passenger();
		model.addAttribute("passenger", passenger);
		return "new_passenger";
	}
	
	@PostMapping("/saveNewEntry")
	public ResponseEntity<?> savePassenger(@RequestBody NewPassengerEntry newPassengerEntry) {
		return passengerService.savePassenger(newPassengerEntry);
	}
	@PostMapping("/savePassenger")
	public String savePassenger(@ModelAttribute("passenger") PassengerDto passenger, RedirectAttributes redirectAttributes) {

		Passenger passenger1 = new Passenger();
		passenger1.setDestination(passenger.getDestination());
		passenger1.setFirstName(passenger.getFirstName());
		passenger1.setLastName(passenger.getLastName());
		passenger1.setNationality(passenger.getNationality());
		passenger1.setFlightNumber(passenger.getFlightNumber());
		if (passenger.getId() != -1L) {
			passenger1.setId(passenger.getId());
		}
	    Long id = passengerService.savePassenger(passenger1);
	    redirectAttributes.addAttribute("passengerId", id);
	    return "redirect:/passenger";
	}
	/*public String savePassenger(@RequestBody Passenger passenger) {
		// save Passenger to database
		passengerService.savePassenger(passenger);
		return "redirect:/";
	}*/
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Passenger from the service
		Passenger passenger = passengerService.getPassengerById(id);
		
		// set Passenger as a model attribute to pre-populate the form
		model.addAttribute("passenger", passenger);
		return "update_passenger";
	}
//	@PutMapping("/UpdatePassenger")
//	public ResponseEntity<?> updatePassenger(@RequestBody PassengerDto passengerDto){
//		return passengerDto.getClass(passengerDto);
//	}
	
	@GetMapping("/deletePassenger/{id}")
	public String deletepassenger(@PathVariable (value = "id") long id) {
		
		// call delete passenger method
		this.passengerService.deletePassengerById(id);
		return "redirect:/passenger";
	}
	
	
//	@GetMapping("/Passenger/page/{pageNo}")
//	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//			@RequestParam("sortField") String sortField,
//			@RequestParam("sortDir") String sortDir,
//			Model model) {
//		int pageSize = 10;
//
//		Page<Passenger> page = passengerService.findPaginated(pageNo, pageSize, sortField, sortDir);
//		List<Passenger> listPassengers = page.getContent();
//
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//
//		model.addAttribute("sortField", sortField);
//		model.addAttribute("sortDir", sortDir);
//		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//		ObjectMapper mapper = new ObjectMapper();
//        try {
//            System.out.println(mapper.writeValueAsString(listPassengers));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        model.addAttribute("listPassengers", listPassengers);
//		return "passenger";
//	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								Model model) {
		int pageSize = 10;

		Page<Passenger> page = passengerService.findPaginated(pageNo, pageSize, sortField, sortDir);
     	List<Passenger> listPassengers = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(listPassengers));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

		model.addAttribute("listPassengers", listPassengers);
		return "passenger";
	}
}