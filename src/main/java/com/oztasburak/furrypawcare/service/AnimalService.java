package com.oztasburak.furrypawcare.service;

import com.oztasburak.furrypawcare.config.BaseService;
import com.oztasburak.furrypawcare.config.ModelMapperService;
import com.oztasburak.furrypawcare.dto.request.AnimalRequest;
import com.oztasburak.furrypawcare.dto.response.AnimalResponse;
import com.oztasburak.furrypawcare.entity.Animal;
import com.oztasburak.furrypawcare.repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService implements BaseService<Animal, AnimalRequest, AnimalResponse> {
    private final AnimalRepository animalRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public Animal getById (Long id)
        {
            return animalRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Animal with ID" + id + " not found"));
        }

    @Override
    public AnimalResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), AnimalResponse.class);
        }

    @Override
    public List<Animal> getAll ()
        {
            return animalRepository.findAll ();
        }

    @Override
    public List<AnimalResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (animal -> modelMapperService
                            .forResponse ()
                            .map (animal, AnimalResponse.class))
                    .toList ();
        }

    @Override
    public AnimalResponse create (AnimalRequest animalRequest)
        {
            Animal animal = modelMapperService
                    .forRequest ()
                    .map (animalRequest, Animal.class);

            return modelMapperService
                    .forResponse ()
                    .map (animalRepository.save (animal), AnimalResponse.class);
        }

    @Override
    public AnimalResponse update (Long id , AnimalRequest animalRequest)
        {
            Animal doesAnimalExist = getById (id);
            Animal animal = modelMapperService
                    .forRequest ()
                    .map (animalRequest, Animal.class);

            modelMapperService
                    .forRequest ()
                    .map (animal, doesAnimalExist);
            doesAnimalExist.setId (id);

            return modelMapperService
                    .forResponse ()
                    .map (animalRepository.save (doesAnimalExist), AnimalResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            animalRepository.delete (getById (id));
        }

    public List<AnimalResponse> filterByName(String name) {
        return animalRepository.findByNameIgnoringCaseContaining (name)
                .stream ().map (animal -> modelMapperService
                        .forResponse ()
                        .map (animal, AnimalResponse.class))
                .toList ();
    }

    public List<AnimalResponse> filterByCustomerName(String customerName) {
        return animalRepository.findByCustomerName (customerName)
                .stream ().map (animal -> modelMapperService
                        .forResponse ()
                        .map (animal, AnimalResponse.class))
                .toList ();
    }
}
