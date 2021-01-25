package ru.kiberKot.students.dao;

import org.springframework.stereotype.Component;
import ru.kiberKot.students.models.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDao {

    private static int STUDENT_COUNT;
    private List<Student> people;

    {
        people = new ArrayList<>();

        people.add(new Student(++STUDENT_COUNT, "Tom","MiddleTom","LastTom","01.01.1995","2"));
        people.add(new Student(++STUDENT_COUNT, "Bob","MiddleBob","LastBob","11.09.2000","1"));
        people.add(new Student(++STUDENT_COUNT, "Mike","MiddleMike","LastMike","22.05.1998","2"));
        people.add(new Student(++STUDENT_COUNT, "Katy","MiddleKaty","LastKaty","18.07.1996","3"));
    }

    public List<Student> index() {
        return people;
    }

    public Student show(int id) {
        return people.stream().filter(student -> student.getId() == id).findAny().orElse(null);
    }

    public void save(Student student){
        student.setId(++STUDENT_COUNT);
        people.add(student);
    }

    public void update(int id, Student updateStudent){
        Student studentToBeUpdated = show(id);
        studentToBeUpdated.setFirstName(updateStudent.getFirstName());
        studentToBeUpdated.setMiddleName(updateStudent.getMiddleName());
        studentToBeUpdated.setLastName(updateStudent.getLastName());
        studentToBeUpdated.setGroup(updateStudent.getGroup());
        studentToBeUpdated.setDateOfBirth(updateStudent.getDateOfBirth());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
