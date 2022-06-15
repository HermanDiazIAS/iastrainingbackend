package com.co.ias.birds.application.services;

import com.co.ias.birds.application.domain.Bird;
import com.co.ias.birds.application.ports.input.UpdateBirdUseCase;
import com.co.ias.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBirdService implements UpdateBirdUseCase {

    private final BirdRepository birdRepository;

    public UpdateBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public BirdDTO execute(BirdDTO birdDTO) {

        Bird bird = birdDTO.toDomain();

        Optional<Bird> birdbd = birdRepository.get(bird.getId());

        if(birdbd.isPresent()) {
            birdRepository.update(bird);
            birdDTO.setStatus("The bird is updated");
        } else {
            birdDTO.setStatus("The bird isn´t updated");
        }
        return birdDTO;
    }
}
