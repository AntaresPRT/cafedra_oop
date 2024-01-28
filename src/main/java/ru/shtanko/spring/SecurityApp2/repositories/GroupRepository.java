package ru.shtanko.spring.SecurityApp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shtanko.spring.SecurityApp2.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
}
