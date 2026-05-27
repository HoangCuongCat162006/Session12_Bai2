package org.example.session12_bai2.service;

import lombok.extern.slf4j.Slf4j;
import org.example.session12_bai2.entity.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    // Hàm lấy danh sách khóa học
    public List<Course> getAllCourses() {
        return courses;
    }

    // Hàm lấy khóa học theo id
    public Course getCourseById(Long id) {

        Course course = courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (course == null) {
            log.warn("Không tìm thấy course với id = {}", id);
        }

        return course;
    }

    // Hàm thêm khóa học
    public Course createCourse(Course course) {

        courses.add(course);

        log.info("Tạo mới course thành công: {}", course.getCourseName());

        return course;
    }

    // Hàm cập nhật khóa học
    public Course updateCourse(Long id, Course newCourse) {

        Course oldCourse = getCourseById(id);

        if (oldCourse == null) {
            return null;
        }

        oldCourse.setCourseName(newCourse.getCourseName());
        oldCourse.setInstructor(newCourse.getInstructor());
        oldCourse.setDurationHours(newCourse.getDurationHours());
        oldCourse.setFee(newCourse.getFee());

        log.info("Cập nhật course thành công với id = {}", id);

        return oldCourse;
    }

    // Hàm xóa khóa học
    public String deleteCourse(Long id) {

        Course course = getCourseById(id);

        if (course == null) {
            return "Không tìm thấy course";
        }

        courses.remove(course);

        return "Xóa thành công";
    }
}