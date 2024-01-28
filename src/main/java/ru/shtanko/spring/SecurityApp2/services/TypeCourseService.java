package ru.shtanko.spring.SecurityApp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.shtanko.spring.SecurityApp2.models.Schedule;
import ru.shtanko.spring.SecurityApp2.models.TypeCourse;
import ru.shtanko.spring.SecurityApp2.repositories.TypeCourseRepository;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class TypeCourseService {

    private final TypeCourseRepository typeCourseRepository;

    @Autowired
    public TypeCourseService(TypeCourseRepository typeCourseRepository) {
        this.typeCourseRepository = typeCourseRepository;
    }

    public List<TypeCourse> findAll() {
        return typeCourseRepository.findAll();
    }

    public TypeCourse findOne(int id) {
        return typeCourseRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(TypeCourse typeCourse) {
        typeCourseRepository.save(typeCourse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, TypeCourse updatedTypeCourse) {
        updatedTypeCourse.setId(id);
        typeCourseRepository.save(updatedTypeCourse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        typeCourseRepository.deleteById(id);
    }
}
