package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.Terminal;
import org.mushroom.repository.TerminalRepository;
import org.mushroom.service.TerminalService;
import org.mushroom.util.TimeDispatcher;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TerminalServiceImpl implements TerminalService {
    private final TerminalRepository terminalRepository;

    private final TimeDispatcher timeDispatcher;

    @Override
    public Optional<Terminal> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Cacheable("terminals")
    @Override
    public Terminal findById(Long id) {
        Terminal terminal = terminalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Terminal.class));
        if (terminal.getDeleted() != null) {
            throw new DeletedEntityException(id, Terminal.class);
        }
        return terminal;
    }

    //    @Cacheable("terminals")
    @Override
    public List<Terminal> findAll() {
        List<Terminal> terminals = terminalRepository.findAll();
        terminals.removeIf(x -> x.getDeleted() != null);
        return terminals;
    }

    @CachePut(value = "terminals", key = "#terminal.id")
    @Override
    public Terminal create(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    @CachePut(value = "terminals", key = "#terminal.id")
    @Override
    public Terminal update(Terminal terminal) {
        Terminal tempTerminal = findById(terminal.getId());
        terminal.setCreated(tempTerminal.getCreated());
        return terminalRepository.save(terminal);
    }

    @CacheEvict(value = "terminals", key = "#id")
    @Override
    public void delete(Long id) {
        terminalRepository.deleteById(id);
    }

    @CacheEvict(value = "terminals", key = "#id")
    @Override
    public void softDelete(Long id) {
        Terminal terminal = findById(id);
        if (terminal.getDeleted() == null) {
            terminal.setDeleted(timeDispatcher.getTime());
            terminalRepository.save(terminal);
        }
    }

}
