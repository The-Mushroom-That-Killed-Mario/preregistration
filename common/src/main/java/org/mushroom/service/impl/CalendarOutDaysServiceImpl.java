package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.CalendarOutDays;
import org.mushroom.model.TerminalServices;
import org.mushroom.repository.CalendarOutDaysRepository;
import org.mushroom.service.CalendarOutDaysService;
import org.mushroom.util.TimeDispatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class CalendarOutDaysServiceImpl implements CalendarOutDaysService {

    private final CalendarOutDaysRepository calendarOutDaysRepository;

    private final TimeDispatcher timeDispatcher;

    @Override
    public Optional<CalendarOutDays> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public CalendarOutDays findById(Long id) {
        CalendarOutDays calendarOutDays = calendarOutDaysRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, CalendarOutDays.class));
        if (!calendarOutDays.isActual()) {
            throw new DeletedEntityException(id, CalendarOutDays.class);
        }
        return calendarOutDays;
    }

    @Override
    public List<CalendarOutDays> findAll() {
        List<CalendarOutDays> calendarOutDays = calendarOutDaysRepository.findAll();
        calendarOutDays.removeIf(x -> !x.isActual());
        return calendarOutDays;
    }


    @Override
    public CalendarOutDays create(CalendarOutDays calendarOutDays) {
        return calendarOutDaysRepository.save(calendarOutDays);
    }

    @Override
    public CalendarOutDays update(CalendarOutDays calendarOutDays) {
        CalendarOutDays tempCalendarOutDays = findById(calendarOutDays.getId());
        calendarOutDays.setCreated(tempCalendarOutDays.getCreated());
        return calendarOutDaysRepository.save(calendarOutDays);
    }

    @Override
    public List<CalendarOutDays> create(Set<LocalDate> dates, TerminalServices terminalServices) {
        List<CalendarOutDays> outDays = calendarOutDaysRepository.findAllByDateAndTerminalServiceIdWithDeleted(dates, terminalServices.getId());

        outDays.forEach(x -> {
            x.setActual(true);
            x.setChanged(timeDispatcher.getTime());
            dates.remove(x.getDate());
        });

        dates.forEach(x -> outDays.add(
                CalendarOutDays.builder()
                        .id(terminalServices.getId())
                        .date(x)
                        .changed(timeDispatcher.getTime())
                        .build()));
        return calendarOutDaysRepository.saveAll(outDays);
    }


    public List<CalendarOutDays> delete(Set<LocalDate> dates, TerminalServices terminalServices) {
        List<CalendarOutDays> outDays = calendarOutDaysRepository.findAllByDateAndTerminalServiceId(dates, terminalServices.getId());
        outDays.forEach(x -> softDelete(x.getId()));
        calendarOutDaysRepository.saveAll(outDays);
        return calendarOutDaysRepository.findAllByDateAndTerminalServiceId(dates, terminalServices.getId());
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
