package com.oztasburak.furrypawcare.service;

import com.oztasburak.furrypawcare.config.BaseService;
import com.oztasburak.furrypawcare.config.ModelMapperService;
import com.oztasburak.furrypawcare.dto.request.VaccineRequest;
import com.oztasburak.furrypawcare.dto.response.VaccineResponse;
import com.oztasburak.furrypawcare.dto.response.VaccineResponse;
import com.oztasburak.furrypawcare.entity.Vaccine;
import com.oztasburak.furrypawcare.entity.Vaccine;
import com.oztasburak.furrypawcare.repository.VaccineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService implements BaseService<Vaccine, VaccineRequest, VaccineResponse> {
    private final VaccineRepository vaccineRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public Vaccine getById (Long id)
        {
            return vaccineRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Vaccine with ID" + id + " not found"));
        }

    @Override
    public VaccineResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), VaccineResponse.class);
        }

    @Override
    public List<Vaccine> getAll ()
        {
            return vaccineRepository.findAll ();
        }

    @Override
    public List<VaccineResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (vaccine -> modelMapperService
                            .forResponse ()
                            .map (vaccine, VaccineResponse.class))
                    .toList ();
        }

    @Override
    public VaccineResponse create (VaccineRequest vaccineRequest)
        {
            Vaccine vaccine = modelMapperService
                    .forRequest ()
                    .map (vaccineRequest, Vaccine.class);

            validateVaccine (vaccine);

            return modelMapperService
                    .forResponse ()
                    .map (vaccineRepository.save (vaccine), VaccineResponse.class);
        }

    @Override
    public VaccineResponse update (Long id , VaccineRequest vaccineRequest)
        {
            Vaccine doesVaccineExist = getById (id);
            Vaccine vaccine = modelMapperService
                    .forRequest ()
                    .map (vaccineRequest, Vaccine.class);

            modelMapperService
                    .forRequest ()
                    .map (vaccine, doesVaccineExist);
            doesVaccineExist.setId (id);

            return modelMapperService
                    .forResponse ()
                    .map (vaccineRepository.save (doesVaccineExist), VaccineResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            vaccineRepository.delete (getById (id));
        }

    private void validateVaccine(Vaccine vaccine) {
        Optional<Vaccine> optionalVaccine = vaccineRepository.validateVaccine (
                vaccine.getCode(),
                vaccine.getProtectionStartDate ()
        );

        if ( optionalVaccine.isPresent () ) {
            throw new RuntimeException ("Vaccine is still in effect!");
        }
    }

    public List<VaccineResponse> getAllVaccinesBetweenStartAndFinishDate(LocalDate startDate, LocalDate finishDate) {
        return vaccineRepository.findAllVaccinesBetweenStartAndFinishDate (startDate, finishDate)
                .stream ().map (vaccine -> modelMapperService
                        .forResponse ()
                        .map (vaccine, VaccineResponse.class))
                .toList ();
    }

    public  List<VaccineResponse> getByAnimalName(String vaccineName) {
        return vaccineRepository.findByAnimalName (vaccineName)
                .stream ().map (vaccine -> modelMapperService
                        .forResponse ()
                        .map (vaccine, VaccineResponse.class))
                .toList ();
    }
}

