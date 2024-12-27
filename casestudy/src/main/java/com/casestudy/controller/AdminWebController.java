package com.casestudy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.casestudy.dto.CourseDTO;
import com.casestudy.dto.LearnerDTO;
import com.casestudy.dto.LessonDTO;
import com.casestudy.services.CourseService;
import com.casestudy.services.LearnerService;
import com.casestudy.services.LessonService;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private LearnerService learnerService;
    
    @Autowired
    private LessonService lessonService;

    // Admin Page
    @GetMapping("")
    public String showAdminPage() {
        return "admin"; // Thymeleaf template for the admin page
    }

    // Courses Section
    @GetMapping("/courses")
    public String viewCourses(Model model) {
        List<CourseDTO> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "viewCourses"; // Thymeleaf template for viewing all courses
    }

    @PostMapping("/course/add")
    public String addCourse(@RequestParam String name, @RequestParam String description) {
        CourseDTO course = new CourseDTO(null, name, description, null, null, null, null);
        courseService.createCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/course/{courseId}")
    public String viewCourse(@PathVariable Long courseId, Model model) {
        CourseDTO course = courseService.getCoursebyId(courseId);
        model.addAttribute("course", course);
        return "viewCourse"; // Thymeleaf template for viewing a specific course
    }

    @PutMapping("/course/{courseId}/update")
    public String updateCourse(@PathVariable Long courseId, @RequestParam String name, @RequestParam String description) {
        CourseDTO updatedCourse = courseService.editCourse(courseId, new CourseDTO(courseId, name, description, null, null, null, null));
        return "redirect:/courses";
    }

    @DeleteMapping("/course/{courseId}/delete")
    public String deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return "redirect:/courses";
    }

    // Learners Section
    @GetMapping("/learners")
    public String viewLearners(Model model) {
        List<LearnerDTO> learners = learnerService.getallLearner();
        model.addAttribute("learners", learners);
        return "viewLearners"; // Thymeleaf template for viewing all learners
    }

    @PostMapping("/learner/add")
    public String addLearner(@RequestParam String name, @RequestParam String email) {
        LearnerDTO learner = new LearnerDTO(null, name, email, null, null);
        learnerService.createLearner(learner);
        return "redirect:/learners";
    }

    @GetMapping("/learner/{learnerId}")
    public String viewLearner(@PathVariable Long learnerId, Model model) {
        LearnerDTO learner = learnerService.getLearnerById(learnerId);
        model.addAttribute("learner", learner);
        return "viewLearner"; // Thymeleaf template for viewing a specific learner
    }

    @PutMapping("/learner/{learnerId}/update")
    public String updateLearner(@PathVariable Long learnerId, @RequestParam String name, @RequestParam String email) {
        LearnerDTO updatedLearner = learnerService.updateLearner(learnerId, new LearnerDTO(learnerId, name, email, null, null));
        return "redirect:/learners";
    }

    @DeleteMapping("/learner/{learnerId}/delete")
    public String deleteLearner(@PathVariable Long learnerId) {
        learnerService.deleteleaner(learnerId);
        return "redirect:/learners";
    }

    // Assign/Unassign Courses to Learners
    @PutMapping("/learner/{learnerId}/assign/{courseId}")
    public String assignCourseToLearner(@PathVariable Long learnerId, @PathVariable Long courseId) {
        learnerService.assignCourseToLearner(learnerId, courseId);
        return "redirect:/learner/" + learnerId;
    }

    @PutMapping("/learner/{learnerId}/unassign/{courseId}")
    public String unassignCourseToLearner(@PathVariable Long learnerId, @PathVariable Long courseId) {
        learnerService.unAssignCourseToLearner(learnerId, courseId);
        return "redirect:/learner/" + learnerId;
    }

    // Lessons Section
    @GetMapping("/course/{courseId}/lessons")
    public String viewLessons(@PathVariable Long courseId, Model model) {
        List<LessonDTO> lessons = lessonService.getAll(courseId);
        model.addAttribute("lessons", lessons);
        return "viewLessons"; // Thymeleaf template for viewing lessons of a course
    }

    @PostMapping("/course/{courseId}/lesson/add")
    public String addLesson(@PathVariable Long courseId, @RequestParam String name, @RequestParam String content) {
        LessonDTO lesson = new LessonDTO(null, name, content, null, null, null, null, null);
        lessonService.createLesson(courseId, lesson);
        return "redirect:/course/" + courseId + "/lessons";
    }
}
