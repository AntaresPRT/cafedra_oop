package ru.shtanko.spring.SecurityApp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.shtanko.spring.SecurityApp2.models.Employee;
import ru.shtanko.spring.SecurityApp2.models.Position;
import ru.shtanko.spring.SecurityApp2.repositories.PositionRepository;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position findOne(int id) {
        return positionRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Position position) {
        positionRepository.save(position);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Position updatedPosition) {
        updatedPosition.setId_position(id);
        positionRepository.save(updatedPosition);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        positionRepository.deleteById(id);
    }
}
