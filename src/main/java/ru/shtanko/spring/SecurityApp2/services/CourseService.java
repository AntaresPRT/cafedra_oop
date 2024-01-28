package ru.shtanko.spring.SecurityApp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.shtanko.spring.SecurityApp2.models.Course;
import ru.shtanko.spring.SecurityApp2.models.Employee;
import ru.shtanko.spring.SecurityApp2.repositories.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findOne(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Course course) {
        courseRepository.save(course);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Course updatedCourse) {
        updatedCourse.setId_course(id);
        courseRepository.save(updatedCourse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        courseRepository.deleteById(id);
    }
}
