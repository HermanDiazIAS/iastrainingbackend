package com.co.ias.birds.application.services;

import com.co.ias.birds.application.domain.Bird;
import com.co.ias.birds.application.domain.valueObjs.*;
import com.co.ias.birds.application.ports.input.CreateBirdUseCase;
import com.co.ias.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateBirdService implements CreateBirdUseCase {
    private final BirdRepository birdRepository;

    public CreateBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    public BirdDTO execute(BirdDTO birdDTO){

        Bird bird = new Bird(null,
                new BirdCommonName(birdDTO.getCommonName()),
                new BirdScientificName(birdDTO.getScientificName()),
                new BirdZoneName(birdDTO.getZoneName()),
                new BirdConfirmedQuantity(birdDTO.getConfirmedQuantity()));

            birdRepository.store(bird);
            birdDTO.setStatus("Created");

        return birdDTO;
    }
}

