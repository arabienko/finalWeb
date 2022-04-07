package by.arabienko.onlineSchool.entity;

public abstract class Entity {
    private long id;
    public Entity() {
    }
    public Entity(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;}
}
