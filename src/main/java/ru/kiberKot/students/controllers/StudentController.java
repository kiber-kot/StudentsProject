package ru.kiberKot.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kiberKot.students.dao.StudentDao;
import ru.kiberKot.students.models.Student;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentDao studentDao;

    @Autowired
    public StudentController(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("student", studentDao.index());
        return "students/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("student", studentDao.show(id));
        return "students/show";
    }

    @GetMapping("/new")
    public String newStudent(Model model ){
        model.addAttribute("student", new Student());
        return "students/new";
    }

    @PostMapping
    public String create(@ModelAttribute("student") @Valid Student student,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "students/new";
        }
        studentDao.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("student",studentDao.show(id));
        return "students/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") @Valid Student student,BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "students/edit";
        }
        studentDao.update(id, student);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id ){
        studentDao.delete(id);
        return "redirect:/students";
    }
}
