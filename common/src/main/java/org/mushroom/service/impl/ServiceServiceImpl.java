package org.mushroom.service.impl;

import org.mushroom.model.Service;
import org.mushroom.service.ServiceService;

import java.util.List;
import java.util.Optional;

public class ServiceServiceImpl implements ServiceService {
    @Override
    public Optional<Service> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Service findById(Long aLong) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        return null;
    }

    @Override
    public Service create(Service object) {
        return null;
    }

    @Override
    public Service update(Service object) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void softDelete(Long aLong) {

    }
}
