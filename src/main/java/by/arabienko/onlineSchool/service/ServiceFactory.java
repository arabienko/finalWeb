package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.exception.PersistentException;

public interface ServiceFactory {
    <Type extends Service> Type getService(Type type) throws PersistentException;
}
