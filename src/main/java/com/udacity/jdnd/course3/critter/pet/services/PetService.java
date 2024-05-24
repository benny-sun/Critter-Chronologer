package com.udacity.jdnd.course3.critter.pet.services;

import com.udacity.jdnd.course3.critter.pet.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.user.entities.Customer;
import com.udacity.jdnd.course3.critter.user.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PetService {

    private final PetRepository petRepository;
    private final UserService userService;

    public PetService(PetRepository petRepository, UserService userService) {
        this.petRepository = petRepository;
        this.userService = userService;
    }

    public Pet save(Pet pet, long ownerId) {
        Customer owner = userService.findCustomer(ownerId);
        pet.setOwner(owner);
        return petRepository.save(pet);
    }

    public Pet findByPetId(long petId) {
        return petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException(petId));
    }

    public List<Pet> findByOwnerId(long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> findByIdIn(List<Long> petIds) {
        return petRepository.findByIdIn(petIds);
    }
}
