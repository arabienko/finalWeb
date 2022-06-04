package by.arabienko.onlineSchool.entity;

import java.util.Objects;

public class UserInfo extends Entity{
    private String surname;
    private String name;
    private String phone;
    private String pathImage;
    private User user;

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPathImage() {
        return pathImage;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return surname +" " + name +
                ", phone: " + phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof UserInfo)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(surname, userInfo.surname) &&
                Objects.equals(name, userInfo.name) &&
                Objects.equals(phone, userInfo.phone) &&
                Objects.equals(pathImage, userInfo.pathImage) &&
                Objects.equals(user, userInfo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, phone, pathImage, user);
    }

    public static class UserBuilder {
        private UserInfo userInfo;

            public UserBuilder() {
            userInfo = new UserInfo();
        }

        public UserInfo build() {
            return userInfo;
        }
        public UserBuilder setId(int id){
                userInfo.setId(id);
                return this;
        }
        public UserBuilder setSurname(String surname) {
            userInfo.surname=surname;
            return this;
        }
        public UserBuilder setName(String name) {
            userInfo.name=name;
            return this;
        }
        public UserBuilder setPhone(String phone) {
            userInfo.phone=phone;
            return this;
        }

        public UserBuilder setPathImage(String pathImage) {
            userInfo.pathImage=pathImage;
            return this;
        }

        public UserBuilder setUser(User user) {
            userInfo.user = user;
            return this;
        }
    }
}
