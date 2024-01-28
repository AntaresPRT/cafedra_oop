package ru.shtanko.spring.SecurityApp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.shtanko.spring.SecurityApp2.models.Employee;
import ru.shtanko.spring.SecurityApp2.models.Schedule;
import ru.shtanko.spring.SecurityApp2.repositories.ScheduleRepository;

import java.util.List;

@Service
public class ScheduleService {

    String[] daysOfWeek = {"Воскресенье","Понедельник","Вторник","Среда","Четверг","Пятница","Суббота"};

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule findOne(int id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Schedule schedule) {
        schedule.setDay_of_week(daysOfWeek[schedule.getDate().getDayOfWeek().getValue()]);
        scheduleRepository.save(schedule);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Schedule updatedSchedule) {
        updatedSchedule.setId_schedule(id);
        updatedSchedule.setDay_of_week(daysOfWeek[updatedSchedule.getDate().getDayOfWeek().getValue()]);
        scheduleRepository.save(updatedSchedule);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        scheduleRepository.deleteById(id);
    }
}
