package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.Service;
import org.mushroom.repository.ServiceRepository;
import org.mushroom.service.ServiceService;
import org.mushroom.util.TimeDispatcher;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    private final TimeDispatcher timeDispatcher;

    @Override
    public Optional<Service> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Cacheable("services")
    @Override
    public Service findById(Long id) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Service.class));
        if (service.getDeleted() != null) {
            throw new DeletedEntityException(id, Service.class);
        }
        return service;
    }

    //    @Cacheable("services")
    @Override
    public List<Service> findAll() {
        List<Service> services = serviceRepository.findAll();
        services.removeIf(x -> x.getDeleted() != null);
        return services;
    }

    @CachePut(value = "services", key = "#service.id")
    @Override
    public Service create(Service service) {
        return serviceRepository.save(service);
    }

    @CachePut(value = "services", key = "#service.id")
    @Override
    public Service update(Service service) {
        Service tempService = findById(service.getId());
        service.setCreated(tempService.getCreated());
        service.setTerminalServices(tempService.getTerminalServices());
        service.setAdvancedAppointment(tempService.getAdvancedAppointment());
        return serviceRepository.save(service);
    }

    @CacheEvict(value = "services", key = "#id")
    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    @CacheEvict(value = "services", key = "#id")
    @Override
    public void softDelete(Long id) {
        Service service = findById(id);
        if (service.getDeleted() == null) {
            service.setDeleted(timeDispatcher.getTime());
            serviceRepository.save(service);
        }
    }
}
