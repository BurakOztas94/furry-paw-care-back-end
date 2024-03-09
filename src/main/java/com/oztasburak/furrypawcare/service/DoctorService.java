package com.oztasburak.furrypawcare.service;

import com.oztasburak.furrypawcare.config.BaseService;
import com.oztasburak.furrypawcare.config.ModelMapperService;
import com.oztasburak.furrypawcare.dto.request.DoctorRequest;
import com.oztasburak.furrypawcare.dto.response.DoctorResponse;
import com.oztasburak.furrypawcare.entity.Doctor;
import com.oztasburak.furrypawcare.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService implements BaseService<Doctor, DoctorRequest, DoctorResponse> {
    private final DoctorRepository doctorRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public Doctor getById (Long id)
        {
            return doctorRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Doctor with ID" + id + " not found"));
        }

    @Override
    public DoctorResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), DoctorResponse.class);
        }

    @Override
    public List<Doctor> getAll ()
        {
            return doctorRepository.findAll ();
        }

    @Override
    public List<DoctorResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (doctor -> modelMapperService
                            .forResponse ()
                            .map (doctor, DoctorResponse.class))
                    .toList ();
        }

    @Override
    public DoctorResponse create (DoctorRequest doctorRequest)
        {
            Doctor doctor = modelMapperService
                    .forRequest ()
                    .map (doctorRequest, Doctor.class);

            return modelMapperService
                    .forResponse ()
                    .map (doctorRepository.save (doctor), DoctorResponse.class);
        }

    @Override
    public DoctorResponse update (Long id , DoctorRequest doctorRequest)
        {
            Doctor doesDoctorExist = getById (id);

            modelMapperService.forRequest ().map (doctorRequest, doesDoctorExist);

            return modelMapperService
                    .forResponse ()
                    .map (doctorRepository.save (doesDoctorExist), DoctorResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            doctorRepository.delete (getById (id));
        }
}
