package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.AdvancedAppointment;
import org.mushroom.repository.AdvancedAppointmentRepository;
import org.mushroom.service.AdvancedAppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvancedAppointmentServiceImpl implements AdvancedAppointmentService {
    private final AdvancedAppointmentRepository advancedAppointmentRepository;

    @Override
    public Optional<AdvancedAppointment> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public AdvancedAppointment findById(Long id) {
        AdvancedAppointment advancedAppointment = advancedAppointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, AdvancedAppointment.class));
        if (!advancedAppointment.isActual()) {
            throw new DeletedEntityException(id, AdvancedAppointment.class);
        }
        return advancedAppointment;
    }

    @Override
    public List<AdvancedAppointment> findAll() {
        List<AdvancedAppointment> advancedAppointments = advancedAppointmentRepository.findAll();
        advancedAppointments.removeIf(x -> !x.isActual());
        return advancedAppointments;
    }


    @Override
    public AdvancedAppointment create(AdvancedAppointment advancedAppointment) {
        advancedAppointment.setCreated(LocalDateTime.now());
        advancedAppointment.setChanged(LocalDateTime.now());
        return advancedAppointmentRepository.save(advancedAppointment);
    }

    @Override
    public AdvancedAppointment update(AdvancedAppointment advancedAppointment) {
        findById(advancedAppointment.getId());
        advancedAppointment.setChanged(LocalDateTime.now());
        return advancedAppointmentRepository.save(advancedAppointment);

    }

    @Override
    public void delete(Long id) {
        advancedAppointmentRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        AdvancedAppointment advancedAppointment = findById(id);
        if (advancedAppointment.isActual()) {
            advancedAppointment.setActual(false);
            advancedAppointmentRepository.save(advancedAppointment);
        }
    }

}
