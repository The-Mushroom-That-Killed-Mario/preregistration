package org.mushroom.service.impl;

import org.mushroom.model.Terminal;
import org.mushroom.service.TerminalService;

import java.util.List;
import java.util.Optional;

public class TerminalServiceImpl implements TerminalService {
    @Override
    public Optional<Terminal> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Terminal findById(Long aLong) {
        return null;
    }

    @Override
    public List<Terminal> findAll() {
        return null;
    }

    @Override
    public Terminal create(Terminal object) {
        return null;
    }

    @Override
    public Terminal update(Terminal object) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void softDelete(Long aLong) {

    }
}
