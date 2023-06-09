package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.CalendarOutDays;
import org.mushroom.repository.CalendarOutDaysRepository;
import org.mushroom.service.CalendarOutDaysService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CalendarOutDaysServiceImpl implements CalendarOutDaysService {
    private final CalendarOutDaysRepository calendarOutDaysRepository;

    @Override
    public Optional<CalendarOutDays> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public CalendarOutDays findById(Long id) {
        CalendarOutDays calendarOutDays =  calendarOutDaysRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, CalendarOutDays.class));
        if (!calendarOutDays.isActual()){
            throw new DeletedEntityException(id, CalendarOutDays.class);
        }
        return calendarOutDays;
    }

    @Override
    public List<CalendarOutDays> findAll() {
        List<CalendarOutDays> calendarOutDays = calendarOutDaysRepository.findAll();
        calendarOutDays.removeIf(x->!x.isActual());
        return calendarOutDays;
    }


    @Override
    public CalendarOutDays create(CalendarOutDays calendarOutDays) {
        calendarOutDays.setCreated(LocalDateTime.now());
        calendarOutDays.setChanged(LocalDateTime.now());
        return calendarOutDaysRepository.save(calendarOutDays);
    }

    @Override
    public CalendarOutDays update(CalendarOutDays calendarOutDays) {
            findById(calendarOutDays.getId());
            calendarOutDays.setChanged(LocalDateTime.now());
            return calendarOutDaysRepository.save(calendarOutDays);
    }

    @Override
    public void delete(Long id) {
        calendarOutDaysRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        CalendarOutDays calendarOutDays = findById(id);
        if (calendarOutDays.isActual()) {
            calendarOutDays.setActual(false);
            calendarOutDaysRepository.save(calendarOutDays);
        }
    }
}
