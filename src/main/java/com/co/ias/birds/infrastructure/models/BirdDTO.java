package com.co.ias.birds.infrastructure.models;

import com.co.ias.birds.application.domain.Bird;
import com.co.ias.birds.application.domain.valueObjs.*;

public class BirdDTO {

    private Long id;
    private String commonName;
    private String scientificName;
    private String zoneName;
    private Integer confirmedQuantity;
    private String status;
    private String message;

    public BirdDTO(Long id, String commonName, String scientificName, String zoneName, Integer confirmedQuantity) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.zoneName = zoneName;
        this.confirmedQuantity = confirmedQuantity;
        this.status = status;
        this.message = message;
    }

    public BirdDTO() {
    }

    public Bird toDomain(){
        return new Bird(
                new BirdId(id),
                new BirdCommonName(commonName),
                new BirdScientificName(scientificName),
                new BirdZoneName(zoneName),
                new BirdConfirmedQuantity(confirmedQuantity)
        );
    }

    public static BirdDTO fromToDomain(Bird bird){
        BirdDTO birdDTO = new BirdDTO(
                bird.getId().getValue(),
                bird.getCommonName().getValue(),
                bird.getScientificName().getValue(),
                bird.getZoneName().getValue(),
                bird.getConfirmedQuantity().getValue()


        );
        return birdDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getConfirmedQuantity() {
        return confirmedQuantity;
    }

    public void setConfirmedQuantity(Integer confirmedQuantity) {
        this.confirmedQuantity = confirmedQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BirdDTO{" +
                "id=" + id +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", zoneName='" + zoneName + '\'' +
                ", confirmedQuantity=" + confirmedQuantity +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
