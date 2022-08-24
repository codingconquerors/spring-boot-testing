package com.testing.springboot.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.testing.springboot.model.Course;
import com.testing.springboot.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final List<Student> students = new ArrayList<>();
    private final SecureRandom random = new SecureRandom();

    static {
        //Initialize Data
        Course courseOne = new Course("Course1", "Spring", "10 Steps", Arrays
                .asList("Learn Maven", "Import Project", "First Example", "Second Example"));

        Course courseTwo = new Course("Course2", "Spring MVC", "10 Examples",
                Arrays.asList("Learn Maven", "Import Project", "First Example", "Second Example"));

        Course courseThree = new Course("Course3", "Spring Boot", "6K Students",
                Arrays.asList("Learn Maven", "Learn Spring", "Learn Spring MVC", "First Example", "Second Example"));

        Course courseFour = new Course("Course4", "Maven",
                "Most popular maven course on internet!", Arrays.asList("Pom.xml", "Build Life Cycle", "Parent POM",
                "Importing into Eclipse"));

        Student ranga = new Student("Student1", "Ranga Karanam",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(courseOne, courseTwo, courseThree, courseFour)));

        Student satish = new Student("Student2", "Satish T",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(courseOne, courseTwo, courseThree, courseFour)));

        students.add(ranga);
        students.add(satish);
    }

    public List<Student> retrieveAllStudents() {
        return students;
    }

    public Student retrieveStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public List<Course> retrieveCourses(String studentId) {
        Student student = retrieveStudent(studentId);

        return student == null ? null : student.getCourses();
    }

    public Course retrieveCourse(String studentId, String courseId) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        for (Course course : student.getCourses()) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }

        return null;
    }


    public Course addCourse(String studentId, Course course) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        course.setId(randomId);

        student.getCourses().add(course);

        return course;
    }
}