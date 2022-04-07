package by.arabienko.onlineSchool.entity;

import java.util.Objects;

public class Subject extends Entity {
    private String nameSubject;
    private String description;

    public Subject(int id, String nameSubject) {
        super(id);
        this.nameSubject = nameSubject;
    }

    public Subject() {
    }

    public Subject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Subject(int id, String nameSubject, String description) {
        super(id);
        this.nameSubject = nameSubject;
        this.description = description;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Subject{" + super.toString() +
                ", nameSubject='" + nameSubject + '\'' +
                ", description='" + description + '\'' + "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(nameSubject, subject.nameSubject) &&
                Objects.equals(description, subject.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameSubject, description);
    }
}
