package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.DaySchedule;
import org.mushroom.model.TerminalServices;
import org.mushroom.repository.TerminalServicesRepository;
import org.mushroom.service.CalendarOutDaysService;
import org.mushroom.service.ServiceService;
import org.mushroom.service.TerminalService;
import org.mushroom.service.TerminalServicesService;
import org.mushroom.util.TimeDispatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TerminalServicesServiceImpl implements TerminalServicesService {

    private final TerminalService terminalService;

    private final ServiceService serviceService;

    private final CalendarOutDaysService calendarOutDaysService;

    private final TerminalServicesRepository terminalServicesRepository;

    private final DayScheduleServiceImpl dayScheduleService;

    private final TimeDispatcher timeDispatcher;


    @Override
    public Optional<TerminalServices> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public TerminalServices findById(Long id) {
        TerminalServices terminalServices = terminalServicesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, TerminalServices.class));
        if (!terminalServices.isActual()) {
            throw new DeletedEntityException(id, TerminalServices.class);
        }
        return hideNotActualElement(terminalServices);
    }

    @Override
    public List<TerminalServices> findAll() {
        List<TerminalServices> terminalServices = terminalServicesRepository.findAll();
        terminalServices.removeIf(x -> !x.isActual());
        terminalServices.forEach(this::hideNotActualElement);
        return terminalServices;
    }

    @Override
    public TerminalServices create(TerminalServices terminalServices) {
        updateNestedFieldsByIds(terminalServices);
        return terminalServicesRepository.save(checkRestore(terminalServices));
    }

    @Override
    public TerminalServices update(TerminalServices terminalServices) {
        terminalServices.setCreated(findById(terminalServices.getId()).getCreated());
        updateNestedFieldsByIds(terminalServices);
        return hideNotActualElement(terminalServicesRepository.save(terminalServices));

    }

    @Override
    public TerminalServices addOutDates(Long id, Set<LocalDate> localDates) {
        TerminalServices tempTerminalServices = findById(id);
        tempTerminalServices.setOutDays(calendarOutDaysService.create(localDates, findById(id)));
        tempTerminalServices.setChanged(timeDispatcher.getTime());
        return terminalServicesRepository.save(findById(id));
    }

    @Override
    public TerminalServices deleteOutDaysByDates(Long id, Set<LocalDate> localDates) {
        TerminalServices tempTerminalServices = findById(id);
        tempTerminalServices.getOutDays().forEach(x -> {
            if (localDates.contains(x.getDate())) {
                x.setActual(false);
            }
        });
        tempTerminalServices.setChanged(timeDispatcher.getTime());
        return hideNotActualElement(terminalServicesRepository.save(tempTerminalServices));
    }

    @Override
    public void delete(Long id) {
        terminalServicesRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        TerminalServices terminalServices = findById(id);
        if (terminalServices.isActual()) {
            terminalServices.setActual(false);
            terminalServicesRepository.save(terminalServices);
        }
    }

    private TerminalServices checkRestore(TerminalServices terminalServices) {
        long terminalId = terminalServices.getTerminal().getId();
        long serviceId = terminalServices.getService().getId();
        if (terminalServicesRepository.existsByTerminalIdAndServiceId(terminalId, serviceId)) {
            TerminalServices bdTerminalServices = terminalServicesRepository.findByTerminalIdAndServiceId(terminalId, serviceId);
            if (!bdTerminalServices.isActual()) {
                bdTerminalServices.setActual(true);
                bdTerminalServices.setChanged(timeDispatcher.getTime());
                return bdTerminalServices;
            }
        }
        return terminalServices;
    }

    private TerminalServices hideNotActualElement(TerminalServices terminalServices) {
        terminalServices.getScheduleDays().removeIf(s -> !s.isActual());
        terminalServices.getOutDays().removeIf(o -> !o.isActual());
        return terminalServices;
    }

    private void updateNestedFieldsByIds(TerminalServices terminalServices) {
        terminalServices.setService(serviceService.findById(terminalServices.getService().getId()));
        terminalServices.setTerminal(terminalService.findById(terminalServices.getTerminal().getId()));
        terminalServices.setScheduleDays(dayScheduleService.findAllByIds(
                terminalServices.getScheduleDays().stream()
                        .map(DaySchedule::getId)
                        .collect(Collectors.toSet())));
    }
//    @Override
//    public TerminalServices findByTerminalIdAndServiceId(Long terminalId, Long serviceId) {
//
//        terminalServicesRepository.findByTerminalIdAndServiceId(terminalId,serviceId);
//
//        return terminalServicesRepository.findByTerminalIdAndServiceId(terminalId,serviceId).get(0);
//
//    }
}
