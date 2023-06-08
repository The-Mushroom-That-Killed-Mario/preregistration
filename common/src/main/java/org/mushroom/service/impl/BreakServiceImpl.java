package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.Break;
import org.mushroom.repository.BreakRepository;
import org.mushroom.service.BreakService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BreakServiceImpl implements BreakService {
    private final BreakRepository timeBreakRepository;

    @Override
    public Optional<Break> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public Break findById(Long id) {
        Break timeBreak =  timeBreakRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Break.class));
        if (timeBreak.isActual()){
            throw new DeletedEntityException(id, Break.class);
        }
        return timeBreak;
    }

    @Override
    public List<Break> findAll() {
        List<Break> timeBreaks = timeBreakRepository.findAll();
        timeBreaks.removeIf(Break::isActual);
        return timeBreaks;
    }


    @Override
    public Break create(Break timeBreak) {
        return timeBreakRepository.save(timeBreak);
    }

    @Override
    public Break update(Break timeBreak) {
        if (timeBreakRepository.existsById(timeBreak.getId())) {
            timeBreak.setChanged(LocalDateTime.now());
            return timeBreakRepository.save(timeBreak);
        } else {
            throw new EntityNotFoundException(timeBreak.getId(), Break.class);
        }
    }

    @Override
    public void delete(Long id) {
        timeBreakRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        Break timeBreak = findById(id);
        if (timeBreak.isActual()) {
            timeBreak.setActual(false);
            timeBreakRepository.save(timeBreak);
        }
    }
}
