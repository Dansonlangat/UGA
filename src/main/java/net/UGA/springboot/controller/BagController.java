package net.UGA.springboot.controller;

import java.util.List;

import net.UGA.springboot.Service.BagService;
import net.UGA.springboot.dto.BagDto;
import net.UGA.springboot.model.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BagController {

    @Autowired
    private BagService bagService;

    // display list of bags
    @GetMapping("/luggage")

    //System.out.println("Am here..!!")
    public String viewLuggagePage(Model model) {
        return findPaginated(1, "passengerId", "dsc", model);
    }

    @GetMapping("/collected")

    //System.out.println("Am here..!!")
    public String viewCollectedPage(Model model) {
        return findCollectedPaginated(1, "bagColor", "dsc", model);
    }

    @GetMapping("/showNewBagForm")
    public String showNewBagForm(Model model) {
        // create model attribute to bind form data
        Bag bag = new Bag();
        model.addAttribute("bag", bag);
        return "new_bag";
    }

    @PostMapping("/saveBag")
    public String saveBag(@RequestParam Long passengerId,@ModelAttribute("bag") BagDto bag) {
    	System.out.println("Save Bag"+ bag.toString());

		Bag bag1 = new Bag();
		bag1.setBagColor(bag.getBagColor());
		bag1.setBagSize(bag.getBagSize());
		bag1.setBagWeight(bag.getBagWeight());
		bag1.setCode(bag.getCode());

		bagService.saveBag(bag1,passengerId);
	    return "redirect:/";
	}

    @GetMapping("/showBagFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get Passenger from the service
        Bag bag = bagService.getBagById(id);

        // set Bag as a model attribute to pre-populate the form
        model.addAttribute("bag", bag);
        return "update_bag";
    }

    @GetMapping("/deleteBag/{id}")
    public String deletebag(@PathVariable (value = "id") long id) {

        // call delete passenger method
        this.bagService.deleteBagById(id);
        return "redirect:/";
    }

    @GetMapping("/bags")
    public ResponseEntity<?> getCollectedLuggage(@RequestParam(name = "collected") boolean collected){
       return bagService.listOfBags(collected);

    }
    @GetMapping("/luggageByBarCode")
    public ResponseEntity<?> getLuggageByBarCode(@RequestParam(name = "barcode") Long barcode){
        return bagService.getBagByCode(barcode);

    }

    @PutMapping("/luggage")
    public ResponseEntity<?> updateLuggage(@RequestBody BagDto bagDto){
        return bagService.updateLuggage(bagDto);
    }



    @GetMapping("/Bag/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 10;

        Page<Bag> page = bagService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Bag> listBags = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listBags", listBags);
        return "luggage";
    }

    @GetMapping("/Collected/page/{pageNo}")
    public String findCollectedPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 10;

        Page<Bag> page = bagService.findCollectedPaginated(pageNo, pageSize, sortField, sortDir);
        List<Bag> listBags = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listBags", listBags);
        return "collected";
    }
}