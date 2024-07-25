package com.example.ormi5projectteam4.service;

import com.example.ormi5projectteam4.domain.Animal;
import com.example.ormi5projectteam4.domain.AnimalDTO;
import com.example.ormi5projectteam4.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal createAnimal(AnimalDTO animalDTO) {
        Animal animal = convertAnimalDTOToAnimal(animalDTO);
        animal.setCreatedAt(LocalDateTime.now());
        animalRepository.save(animal);
        return animal;
    }

    private static Animal convertAnimalDTOToAnimal(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setName(animalDTO.getName());
        animal.setGender(animalDTO.getGender());
        animal.setNeuteringStatus(animalDTO.getNeuteringStatus());
        animal.setSpecies(animalDTO.getSpecies());
        animal.setBreed(animalDTO.getBreed());
        animal.setAge(animalDTO.getAge());
        animal.setWeight(animalDTO.getWeight());
        return animal;
    }
}
