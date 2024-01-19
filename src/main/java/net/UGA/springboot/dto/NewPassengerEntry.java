package net.UGA.springboot.dto;

import java.util.List;

public class NewPassengerEntry {
    private PassengerDto passengerDto;
    private List<BagDto> bagDtos;

    public PassengerDto getPassengerDto() {
        return passengerDto;
    }

    public void setPassengerDto(PassengerDto passengerDto) {
        this.passengerDto = passengerDto;
    }

    public List<BagDto> getBagDtos() {
        return bagDtos;
    }

    public void setBagDtos(List<BagDto> bagDtos) {
        this.bagDtos = bagDtos;
    }
}
