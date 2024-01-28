package ru.shtanko.spring.SecurityApp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.shtanko.spring.SecurityApp2.models.Employee;
import ru.shtanko.spring.SecurityApp2.models.Group;
import ru.shtanko.spring.SecurityApp2.repositories.GroupRepository;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findOne(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Group group) {
        groupRepository.save(group);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Group updatedGroup) {
        updatedGroup.setId_group(id);
        groupRepository.save(updatedGroup);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        groupRepository.deleteById(id);
    }
}
