package com.oztasburak.furrypawcare.service;

import com.oztasburak.furrypawcare.config.BaseService;
import com.oztasburak.furrypawcare.config.ModelMapperService;
import com.oztasburak.furrypawcare.dto.request.AppointmentRequest;
import com.oztasburak.furrypawcare.dto.response.AppointmentResponse;
import com.oztasburak.furrypawcare.entity.Appointment;
import com.oztasburak.furrypawcare.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService implements BaseService<Appointment, AppointmentRequest, AppointmentResponse> {
    private final AppointmentRepository appointmentRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public Appointment getById (Long id)
        {
            return appointmentRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Appointment with ID" + id + " not found"));
        }

    @Override
    public AppointmentResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), AppointmentResponse.class);
        }

    @Override
    public List<Appointment> getAll ()
        {
            return appointmentRepository.findAll ();
        }

    @Override
    public List<AppointmentResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (appointment -> modelMapperService
                            .forResponse ()
                            .map (appointment, AppointmentResponse.class))
                    .toList ();
        }

    @Override
    public AppointmentResponse create (AppointmentRequest appointmentRequest)
        {
            Appointment appointment = modelMapperService
                    .forRequest ()
                    .map (appointmentRequest, Appointment.class);

            return modelMapperService
                    .forResponse ()
                    .map (appointmentRepository.save (appointment), AppointmentResponse.class);
        }

    @Override
    public AppointmentResponse update (Long id , AppointmentRequest appointmentRequest)
        {
            Appointment doesAppointmentExist = getById (id);

            modelMapperService.forRequest ().map (appointmentRequest, doesAppointmentExist);

            return modelMapperService
                    .forResponse ()
                    .map (appointmentRepository.save (doesAppointmentExist), AppointmentResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            appointmentRepository.delete (getById (id));
        }
}
