package com.co.ias.birds.application.services;

import com.co.ias.birds.application.domain.Bird;
import com.co.ias.birds.application.domain.valueObjs.BirdId;
import com.co.ias.birds.application.ports.input.DeleteBirdUseCase;
import com.co.ias.birds.application.ports.output.BirdRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteBirdService implements DeleteBirdUseCase {
    private final BirdRepository birdRepository;

    public DeleteBirdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public Boolean execute(Long birdId) {
        Optional<Bird> birdValidExist = birdRepository.get(new BirdId(birdId));
        if(birdValidExist.isPresent()){
            return birdRepository.delete(new BirdId(birdId));
        }
        return false;
    }
}
