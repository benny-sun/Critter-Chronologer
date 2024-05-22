package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }
}
