package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DayOffException;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.exception.InvalidScheduleException;
import org.mushroom.model.AdvancedAppointment;
import org.mushroom.model.DaySchedule;
import org.mushroom.model.Terminal;
import org.mushroom.model.TerminalServices;
import org.mushroom.model.User;
import org.mushroom.repository.AdvancedAppointmentRepository;
import org.mushroom.service.AdvancedAppointmentService;
import org.mushroom.service.ServiceService;
import org.mushroom.service.TerminalService;
import org.mushroom.service.TerminalServicesService;
import org.mushroom.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvancedAppointmentServiceImpl implements AdvancedAppointmentService {

    private final AdvancedAppointmentRepository advancedAppointmentRepository;

    private final ServiceService serviceService;

    private final UserService userService;

    private final TerminalService terminalService;

    private final TerminalServicesService terminalServicesService;

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
        updateNestedFieldsByIds(advancedAppointment);
        checkPossibilityOfRegistration(advancedAppointment);
        return advancedAppointmentRepository.save(advancedAppointment);
    }

    @Override
    public AdvancedAppointment update(AdvancedAppointment advancedAppointment) {
        advancedAppointment.setCreated(findById(advancedAppointment.getId()).getCreated());
        updateNestedFieldsByIds(advancedAppointment);
        checkPossibilityOfRegistration(advancedAppointment);
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

    private void updateNestedFieldsByIds(AdvancedAppointment advancedAppointment) {
        Terminal terminal = terminalService.findById(advancedAppointment.getTerminal().getId());
        org.mushroom.model.Service service = serviceService.findById(advancedAppointment.getTerminal().getId());
        User user = userService.findById(advancedAppointment.getUser().getId());
        advancedAppointment.setTerminal(terminal);
        advancedAppointment.setService(service);
        advancedAppointment.setUser(user);
    }

    private void checkPossibilityOfRegistration(AdvancedAppointment appointment) {
        TerminalServices terminalServices = terminalServicesService.findByTerminalIdAndServiceId(appointment.getTerminal().getId(),
                appointment.getService().getId());
        checkOutDates(terminalServices, appointment.getDate());
        DaySchedule daySchedule = checkScheduleDates(terminalServices, appointment.getDate());
        checkScheduleTime(daySchedule, appointment.getTimeFrom());
        checkBreakTime(daySchedule, appointment.getTimeFrom());
    }

    private void checkOutDates(TerminalServices terminalServices, LocalDate date) {
        if (terminalServices.getOutDays().stream()
                .anyMatch(x -> x.getDate().equals(date) && x.isActual())) {
            throw new DayOffException(date);
        }
    }

    private DaySchedule checkScheduleDates(TerminalServices terminalServices, LocalDate date) {
        return terminalServices.getScheduleDays().stream()
                .filter(x -> x.getDayOfWeek().equals(date.getDayOfWeek()) && x.isActual())
                .findAny()
                .orElseThrow(() -> new InvalidScheduleException("not found day of week from schedule" + date.getDayOfWeek()));
    }

    private void checkScheduleTime(DaySchedule daySchedule, LocalTime time) {
        if (!daySchedule.getTimeBegin().isBefore(time) && !daySchedule.getTimeEnd().isAfter(time)) {
            throw new InvalidScheduleException("non-working time from schedule");
        }
    }

    private void checkBreakTime(DaySchedule daySchedule, LocalTime time) {
        daySchedule.getBreaks().forEach(x -> {
            if (x.getFromTime().isBefore(time) && x.getToTime().isAfter(time)) {
                throw new InvalidScheduleException("non-working time from schedule it break");
            }
        });
    }
}
