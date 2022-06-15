package com.co.ias.birds.application.ports.input;

import com.co.ias.birds.commons.UseCase;
import com.co.ias.birds.infrastructure.models.BirdDTO;

import java.util.Optional;

public interface QueryBirdByIdUseCase extends UseCase<Long, Optional<BirdDTO>> {

}
