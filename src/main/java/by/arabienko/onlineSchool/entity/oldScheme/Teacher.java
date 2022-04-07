package by.arabienko.onlineSchool.entity.oldScheme;

import by.arabienko.onlineSchool.entity.Entity;

public class Teacher extends Entity {
    private String surname;
    private String name;
    private int idSubjectT;
    private String phoneTeacher;
    private String degree;
    private String pathImage;

    public Teacher(int idTeacher, String surname, String name,
                   int idSubjectT, String phoneTeacher,
                   String degree, String pathImage) {
        super(idTeacher);
        this.surname = surname;
        this.name = name;
        this.idSubjectT = idSubjectT;
        this.phoneTeacher = phoneTeacher;
        this.degree = degree;
        this.pathImage = pathImage;
    }

    public Teacher() {

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

    public int getIdSubject() {
        return idSubjectT;
    }

    public void setIdSubject(int idSubjectT) {
        this.idSubjectT = idSubjectT;
    }

    public String getPhoneTeacher() {
        return phoneTeacher;
    }

    public void setPhoneTeacher(String phoneTeacher) {
        this.phoneTeacher = phoneTeacher;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public String toString() {
        super.toString();
        return "Teacher{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", idSubjectT=" + idSubjectT +
                ", phoneTeacher='" + phoneTeacher + '\'' +
                ", degree='" + degree + '\'' +
                ", pathImage='" + pathImage +"}";
    }
}
