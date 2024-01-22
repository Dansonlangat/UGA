package net.UGA.springboot.Repository;

import net.UGA.springboot.model.Bag;
import net.UGA.springboot.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{
  
}