package ru.shtanko.spring.SecurityApp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.shtanko.spring.SecurityApp2.models.Audience;
import ru.shtanko.spring.SecurityApp2.repositories.AudienceRepository;

import java.util.List;

@Service
public class AudienceService {

    private final AudienceRepository audienceRepository;

    @Autowired
    public AudienceService(AudienceRepository audienceRepository) {
        this.audienceRepository = audienceRepository;
    }

    public List<Audience> findAll() {
        return audienceRepository.findAll();
    }

    public Audience findOne(int id) {
        return audienceRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Audience audience) {
        audienceRepository.save(audience);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Audience updatedAudience) {
        updatedAudience.setId_audience(id);
        audienceRepository.save(updatedAudience);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        audienceRepository.deleteById(id);
    }
}
