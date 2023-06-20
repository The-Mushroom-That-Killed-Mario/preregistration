package org.mushroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.mushroom.exception.DeletedEntityException;
import org.mushroom.exception.EntityNotFoundException;
import org.mushroom.model.Break;
import org.mushroom.repository.BreakRepository;
import org.mushroom.service.BreakService;
import org.mushroom.util.TimeDispatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BreakServiceImpl implements BreakService {

    private final BreakRepository timeBreakRepository;

    private final TimeDispatcher timeDispatcher;

    @Override
    public Optional<Break> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public Break findById(Long id) {
        Break timeBreak = timeBreakRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Break.class));
        if (!timeBreak.isActual()) {
            throw new DeletedEntityException(id, Break.class);
        }
        return timeBreak;
    }

    @Override
    public List<Break> findAll() {
        List<Break> timeBreaks = timeBreakRepository.findAll();
        timeBreaks.removeIf(x -> !x.isActual());
        return timeBreaks;
    }


    @Override
    public Break create(Break timeBreak) {
        timeBreak = checkRestore(timeBreak);
        return timeBreakRepository.save(timeBreak);
    }

    @Override
    public Break update(Break timeBreak) {
        Break tempTimeBreak = findById(timeBreak.getId());
        timeBreak.setCreated(tempTimeBreak.getCreated());
        timeBreak.setScheduleDays(tempTimeBreak.getScheduleDays());
        return timeBreakRepository.save(timeBreak);
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

    private Break checkRestore(Break timeBreak) {
        if (timeBreakRepository.existsByFromTimeAndToTime(timeBreak.getFromTime(), timeBreak.getToTime())) {
            timeBreak = timeBreakRepository.findByFromTimeAndToTime(timeBreak.getFromTime(), timeBreak.getToTime());
            timeBreak.setActual(true);
            timeBreak.setChanged(timeDispatcher.getTime());
            return timeBreak;
        }
        return timeBreak;
    }
}
