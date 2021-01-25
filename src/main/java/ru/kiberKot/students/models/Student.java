package ru.kiberKot.students.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class Student {
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым !")
    @Size(min = 2, max = 30, message = "Имя должна быть от 2-ух до 30 символов")
    private String firstName;

    @NotEmpty(message = "Отчество не должно быть пустым !")
    @Size(min = 2, max = 30, message = "Имя должна быть от 2-ух до 30 символов")
    private String middleName;

    @NotEmpty(message = "Фамилия не должно быть пустым !")
    @Size(min = 2, max = 30, message = "Имя должна быть от 2-ух до 30 символов")
    private String lastName;

    @NotEmpty(message = "Дата рождения не должно быть пустым и формат даты должен быть 'dd.MM.yyyy'")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String dateOfBirth;

    @NotEmpty(message = "Группа не должна быть пустым !")
    @Min(value = 1, message = "Группа не должна быть меньше 1")
    @Max(value = 5, message = "Группа не должна быть больше 5")
    private String group;

    public Student(){

    }

    public Student(int id, String firstName, String middleName, String lastName, String dateOfBirth, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.group = group;
    }
}
