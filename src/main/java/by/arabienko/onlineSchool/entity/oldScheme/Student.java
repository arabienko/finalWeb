package by.arabienko.onlineSchool.entity.oldScheme;

import by.arabienko.onlineSchool.entity.Entity;

public class Student extends Entity {
    private String surname;
    private String name;
    private String phoneStudent;
    private String level;
    private String status;

    public Student( String surname, String name, String phoneStudent, String level, String status) {
        this.surname = surname;
        this.name = name;
        this.phoneStudent = phoneStudent;
        this.level = level;
        this.status = status;
    }

    public Student() {

    }

    public Student(int id, String surname, String name, String phoneStudent, String level, String status) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.phoneStudent = phoneStudent;
        this.level = level;
        this.status = status;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneStudent() {
        return phoneStudent;
    }

    public void setPhoneStudent(String phoneStudent) {
        this.phoneStudent = phoneStudent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Student{" +
                "ID='" + this.getId() + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", phoneStudent='" + phoneStudent + '\'' +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
