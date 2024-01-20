package net.UGA.springboot.dto;

import org.springframework.beans.factory.annotation.Autowired;


public class BagDto {
    @Autowired

    private Long id;
    private String bagSize;
    private String bagWeight;
    private String bagColor;
    private String code;
    private Long passengerId;

    private Boolean collected;

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

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public Boolean getCollected() {
        return collected;
    }

    public void setCollected(Boolean collected) {
        this.collected = collected;
    }

    public BagDto() {
    }
}
