package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.Service;
import org.mushroom.repository.ServiceRepository;
import org.mushroom.service.ServiceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public Optional<Service> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public Service findById(Long id) {
        Service service =  serviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Service.class));
        if (service.getDeleted()!=null){
            throw new DeletedEntityException(id, Service.class);
        }
        return service;
    }

    @Override
    public List<Service> findAll() {
        List<Service> services = serviceRepository.findAll();
        services.removeIf(x->x.getDeleted()!=null);
        return services;
    }


    @Override
    public Service create(Service service) {
        service.setCreated(LocalDateTime.now());
        service.setChanged(LocalDateTime.now());
        return serviceRepository.save(service);
    }

    @Override
    public Service update(Service service) {
        service.setCreated(findById(service.getId()).getCreated());
        service.setChanged(LocalDateTime.now());
        return serviceRepository.save(service);
    }

    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        Service service = findById(id);
        if (service.getDeleted() == null) {
            service.setDeleted(LocalDateTime.now());
            serviceRepository.save(service);
        }
    }
}
