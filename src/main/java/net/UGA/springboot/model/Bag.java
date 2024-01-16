package net.UGA.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bags")
public class Bag {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "bag_color")
    private String bagColor;

    @Column(name = "bag_size")
    private String bagSize;

    @Column(name = "bag_weight")
    private String bagWeight;
    @Column(name = "bar_code")
    private String code;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;


    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBagColor() {
        return bagColor;
    }
    public void setBagColor(String bagColor) {
        this.bagColor = bagColor;
    }
    public String getBagSize() {
        return bagSize;
    }
    public void setBagSize(String bagSize) {
        this.bagSize = bagSize;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getBagWeight() {
        return bagWeight;
    }
    public void setBagWeight(String bagWeight) {
        this.bagWeight = bagWeight;
    }
}
