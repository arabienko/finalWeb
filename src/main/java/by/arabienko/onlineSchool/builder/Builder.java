package by.arabienko.onlineSchool.builder;


import by.arabienko.onlineSchool.entity.Entity;

import java.sql.ResultSet;

/**
 * The interface Builder.
 *
 * @param <T> the type parameter
 */
public interface Builder <T extends Entity> {
    /**
     * Build t.
     *
     * @param resultSet the result set
     * @return the t
     */
    T build(ResultSet resultSet) ;
}
