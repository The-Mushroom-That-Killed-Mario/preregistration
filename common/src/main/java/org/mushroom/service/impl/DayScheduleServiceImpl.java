package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.Break;
import org.mushroom.model.DaySchedule;
import org.mushroom.repository.DayScheduleRepository;
import org.mushroom.service.BreakService;
import org.mushroom.service.DayScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class DayScheduleServiceImpl implements DayScheduleService {

    private final DayScheduleRepository dayScheduleRepository;

    private final BreakService breakService;

    @Override
    public Optional<DaySchedule> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public DaySchedule findById(Long id) {
        DaySchedule daySchedule = dayScheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, DaySchedule.class));
        if (!daySchedule.isActual()) {
            throw new DeletedEntityException(id, DaySchedule.class);
        }
        return daySchedule;
    }

    @Override
    public List<DaySchedule> findAll() {
        List<DaySchedule> daySchedules = dayScheduleRepository.findAll();
        daySchedules.removeIf(x -> !x.isActual());
        return daySchedules;
    }

    @Override
    public Set<DaySchedule> findAllByIds(Set<Long> ids) {
        Set<DaySchedule> daySchedules = dayScheduleRepository.findAllByIds(ids);
        daySchedules.forEach(x -> {
            if (!x.isActual()) {
                throw new DeletedEntityException(x.getId(), x.getClass());
            }
        });
        return daySchedules;
    }


    @Override
    public DaySchedule create(DaySchedule daySchedule) {
        return dayScheduleRepository.save(daySchedule);
    }

    @Override
    public DaySchedule create(DaySchedule daySchedule, Set<Long> breaksIds) {
        daySchedule.setBreaks(findBreaksFromDay(breaksIds));
        return create(daySchedule);

    }

    @Override
    public DaySchedule update(DaySchedule daySchedule) {
        daySchedule.setCreated(findById(daySchedule.getId()).getCreated());
        return dayScheduleRepository.save(daySchedule);
    }

    @Override
    public DaySchedule update(DaySchedule daySchedule, Set<Long> breaksIds) {
        daySchedule.setCreated(findById(daySchedule.getId()).getCreated());
        daySchedule.setBreaks(findBreaksFromDay(breaksIds));
        return create(daySchedule);
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

    public Set<Break> findBreaksFromDay(Set<Long> breaksIds) {
        Set<Break> breakSet = new HashSet<>();
        breaksIds.forEach(x -> {
                    breakSet.add(breakService.findById(x));
                }
        );
        return breakSet;
    }
}
