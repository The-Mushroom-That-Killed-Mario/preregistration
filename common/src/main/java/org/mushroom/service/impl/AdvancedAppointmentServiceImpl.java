package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.AdvancedAppointment;
import org.mushroom.model.Terminal;
import org.mushroom.model.TerminalServices;
import org.mushroom.model.User;
import org.mushroom.repository.AdvancedAppointmentRepository;
import org.mushroom.service.AdvancedAppointmentService;
import org.mushroom.service.ServiceService;
import org.mushroom.service.TerminalService;
import org.mushroom.service.TerminalServicesService;
import org.mushroom.service.UserService;
import org.mushroom.util.TimeDispatcher;
import org.springframework.stereotype.Service;

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

    private final TimeDispatcher timeDispatcher;

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

//        Terminal terminal = terminalService.findById(advancedAppointment.getTerminal().getId());
//        org.mushroom.model.Service service = serviceService.findById(advancedAppointment.getTerminal().getId());
//        User user = userService.findById(advancedAppointment.getUser().getId());
//
//        advancedAppointment.setTerminal(terminal);
//        advancedAppointment.setService(service);
//        advancedAppointment.setUser(user);

        findNestedField(advancedAppointment);


//        TerminalServices terminalServices = terminalServicesService.findByTerminalIdAndServiceId(
//                advancedAppointment.getTerminal().getId(),
//                advancedAppointment.getService().getId());
//
//        checkWriteCapability(terminalServices,
//                advancedAppointment.getTimeFrom(),
//                advancedAppointment.getDate());

        return advancedAppointmentRepository.save(advancedAppointment);
    }

    @Override
    public AdvancedAppointment update(AdvancedAppointment advancedAppointment) {
        advancedAppointment.setCreated(findById(advancedAppointment.getId()).getCreated());
        findNestedField(advancedAppointment);
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

    private void findNestedField(AdvancedAppointment advancedAppointment){
        Terminal terminal = terminalService.findById(advancedAppointment.getTerminal().getId());
        org.mushroom.model.Service service = serviceService.findById(advancedAppointment.getTerminal().getId());
        User user = userService.findById(advancedAppointment.getUser().getId());
        advancedAppointment.setTerminal(terminal);
        advancedAppointment.setService(service);
        advancedAppointment.setUser(user);
    }

//    private boolean checkWriteCapability(TerminalServices terminalServices,
//                                         LocalTime time,
//                                         LocalDate date) {
//        checkOutDates(terminalServices, date);
//        checkScheduleDates(terminalServices, date);
//        checkScheduleTime(terminalServices, date, time);
//        return true;
//    }

//    private boolean checkOutDates(TerminalServices terminalServices,
//                                  LocalDate date) {
//        terminalServices.getService().getTerminalServices().getOutDays().stream()
//                .filter(x -> x.isActual())
//                .map(x -> x.getDate())
//                .filter(x -> x.equals(date))
//                .findFirst()
//                .orElseThrow(() -> new DayOffException(date));
//        return true;
//    }

//    private boolean checkScheduleDates(TerminalServices terminalServices,
//                                       LocalDate date) {
//        terminalServices.getService().getTerminalService().getScheduleDays().stream()
//                .filter(x -> x.isActual())
//                .filter(x -> x.getDayOfWeek().equals(date.getDayOfWeek()))
//                .findFirst()
//                .orElseThrow(() -> new InvalidScheduleException("not found day of week from schedule" + date.getDayOfWeek()));
//        return true;
//    }

//    private boolean checkScheduleTime(TerminalServices terminalServices,
//                                      LocalDate date,
//                                      LocalTime time) {
//
//        terminalServices.getService().getTerminalService().getScheduleDays().stream()
//                .filter(x -> x.isActual())
//                .filter(x -> x.getDayOfWeek().equals(date.getDayOfWeek()))
//                .filter(x -> (x.getTimeBegin().isBefore(time) && x.getTimeEnd().isAfter(time)))
//                .findFirst().orElseThrow(() -> new InvalidScheduleException("non-working time from schedule"));
//
//        return true;
//    }
    //что-то в духе
}
