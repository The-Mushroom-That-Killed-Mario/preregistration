package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.DaySchedule;
import org.mushroom.repository.DayScheduleRepository;
import org.mushroom.service.DayScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class DayScheduleServiceImpl implements DayScheduleService {
    private final DayScheduleRepository dayScheduleRepository;

    @Override
    public Optional<DaySchedule> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public DaySchedule findById(Long id) {
        DaySchedule daySchedule =  dayScheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, DaySchedule.class));
        if (daySchedule.isActual()){
            throw new DeletedEntityException(id, DaySchedule.class);
        }
        return daySchedule;
    }

    @Override
    public List<DaySchedule> findAll() {
        List<DaySchedule> daySchedules = dayScheduleRepository.findAll();
        daySchedules.removeIf(DaySchedule::isActual);
        return daySchedules;
    }


    @Override
    public DaySchedule create(DaySchedule daySchedule) {
        return dayScheduleRepository.save(daySchedule);
    }

    @Override
    public DaySchedule update(DaySchedule daySchedule) {
        if (dayScheduleRepository.existsById(daySchedule.getId())) {
            daySchedule.setChanged(LocalDateTime.now());
            return dayScheduleRepository.save(daySchedule);
        } else {
            throw new EntityNotFoundException(daySchedule.getId(), DaySchedule.class);
        }
    }

    @Override
    public void delete(Long id) {
        dayScheduleRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        DaySchedule daySchedule = findById(id);
        if (daySchedule.isActual()) {
            daySchedule.setActual(false);
            dayScheduleRepository.save(daySchedule);
        }
    }

}
