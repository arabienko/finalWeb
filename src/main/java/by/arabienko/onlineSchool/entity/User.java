package by.arabienko.onlineSchool.entity;

public class User extends Entity {
    private String login;
    private String password;
    private Integer role;

    public User(Integer ID, String login, String password, Integer role) {
        super(ID);
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password, Integer role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + super.getId() +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
