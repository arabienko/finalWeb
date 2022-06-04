package by.arabienko.onlineSchool.valid;

import by.arabienko.onlineSchool.controller.ServletSQLRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Data validator.
 */
public class DataValidator {
    private static final Logger LOGGER =
            LogManager.getLogger(DataValidator.class);
    private static final Pattern LOGIN_PATTERN = Pattern.compile("^(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    private static final Pattern NAME_SURNAME_PATRONYMIC_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{3,20}");
    //Minimum eight characters, at least one letter and one number:
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$");
    private static final Pattern SUBJECT_DESCRIPTION_PATTERN = Pattern.compile("[A-Za-zА-Яа-яёЁ0-9][A-Za-zА-Яа-яёЁ,.()\\s0-9]{4,399}");
    private static final Pattern SUBJECT_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]*$");
    private static final Pattern INPUT_IDENTIFIABLE_ID_PATTERN = Pattern.compile("[\\d]{1,}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static final Pattern DATE_OF_WEEK_TIME_PATTERN = Pattern.compile("^[A-z]*,\\s\\d{2}-\\d{2}");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\+\\d{12}");


    public static boolean isDateFormat(String date){
        Matcher matcher = DATE_PATTERN.matcher(date);
        return matcher.matches();
    }
    /**
     * Is login valid boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public static boolean isLoginValid(String login) {
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        return matcher.matches();
    }

    /**
     * Is password valid boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isPasswordValid(String password) {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }

    /**
     * Is name valid boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isNameValid(String name) {
        Matcher matcher = NAME_SURNAME_PATRONYMIC_PATTERN.matcher(name);
        return matcher.matches();
    }

    /**
     * Is subject name valid boolean.
     *
     * @param subjectName the exercise name
     * @return the boolean
     */
    public static boolean isSubjectNameValid(String subjectName) {
        Matcher matcher = SUBJECT_NAME_PATTERN.matcher(subjectName);
        return matcher.matches();
    }

    /**
     * Is surname valid boolean.
     *
     * @param surname the surname
     * @return the boolean
     */
    public static boolean isSurnameValid(String surname) {
        Matcher matcher = NAME_SURNAME_PATRONYMIC_PATTERN.matcher(surname);
        return matcher.matches();
    }

    /**
     * Is patronymic valid boolean.
     *
     * @param patronymic the patronymic
     * @return the boolean
     */
    public static boolean isPatronymicValid(String patronymic) {
        Matcher matcher = NAME_SURNAME_PATRONYMIC_PATTERN.matcher(patronymic);
        return matcher.matches();
    }

    /**
     * Is identifiable id valid boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    public static boolean isIdentifiableIdValid(String userId) {
        Matcher matcher = INPUT_IDENTIFIABLE_ID_PATTERN.matcher(userId);
        return matcher.matches();
    }

    /**
     * Is description valid boolean.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isDescriptionValid(String description) {
        Matcher matcher = SUBJECT_DESCRIPTION_PATTERN.matcher(description);
        return matcher.matches();
    }

    /**
     * Is day of week and date valid boolean.
     *
     * @param pattern the description
     * @return the boolean
     */
    public static boolean isDateOfWeekValid(String pattern) {
        Matcher matcher = DATE_OF_WEEK_TIME_PATTERN.matcher(pattern);
        return matcher.matches();
    }

}
