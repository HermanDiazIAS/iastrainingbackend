package com.co.ias.birds.application.services;

import com.co.ias.birds.application.domain.Bird;
import com.co.ias.birds.application.domain.valueObjs.BirdId;
import com.co.ias.birds.application.ports.input.QueryBirdByIdUseCase;
import com.co.ias.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.models.BirdDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueryBirdByIdService implements QueryBirdByIdUseCase {

    private final BirdRepository birdRepository;

    public QueryBirdByIdService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @Override
    public Optional<BirdDTO> execute(Long birdId) {
        BirdId birdIdInput = new BirdId(birdId);
        Optional<Bird> birdOptional = birdRepository.get(birdIdInput);
        if(birdOptional.isPresent()){
            return birdOptional.map(bird -> {
                BirdDTO birdDTO = BirdDTO.fromToDomain(birdOptional.get());
                birdDTO.setStatus("Ok");
                birdDTO.setMessage("The bird is found");
                return birdDTO;
            });
        }
        return Optional.empty();

    }
}
