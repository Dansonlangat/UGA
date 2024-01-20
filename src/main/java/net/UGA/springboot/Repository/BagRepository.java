package net.UGA.springboot.Repository;

import net.UGA.springboot.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long>{

    @Query(value="SELECT * FROM bags  WHERE Collected = ?1",nativeQuery = true)
    Optional<List<Bag>> findCollectedBags(Boolean collected);
}