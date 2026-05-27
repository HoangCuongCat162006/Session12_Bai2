package org.example.session12_bai2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.session12_bai2.entity.Course;
import org.example.session12_bai2.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    // API lấy tất cả khóa học
    @GetMapping
    public ResponseEntity<?> getAllCourses() {

        log.info("GET request tới /api/courses");

        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // API lấy khóa học theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {

        log.info("GET request tới /api/courses/{}", id);

        Course course = courseService.getCourseById(id);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(course);
    }

    // API thêm khóa học
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {

        log.info("POST request tới /api/courses");

        try {

            return ResponseEntity.ok(courseService.createCourse(course));

        } catch (RuntimeException e) {

            log.error("Lỗi khi tạo course: {}", e.getMessage());

            return ResponseEntity.internalServerError().body("Lỗi server");
        }
    }

    // API cập nhật khóa học
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id,
                                          @RequestBody Course course) {

        log.info("PUT request tới /api/courses/{}", id);

        try {

            Course updatedCourse = courseService.updateCourse(id, course);

            if (updatedCourse == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(updatedCourse);

        } catch (RuntimeException e) {

            log.error("Lỗi khi cập nhật course: {}", e.getMessage());

            return ResponseEntity.internalServerError().body("Lỗi server");
        }
    }

    // API xóa khóa học
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {

        log.info("DELETE request tới /api/courses/{}", id);

        try {

            return ResponseEntity.ok(courseService.deleteCourse(id));

        } catch (RuntimeException e) {

            log.error("Lỗi khi xóa course: {}", e.getMessage());

            return ResponseEntity.internalServerError().body("Lỗi server");
        }
    }
}