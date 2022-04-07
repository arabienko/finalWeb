package by.arabienko.onlineSchool.service.xmlparser.StAX;

import by.arabienko.onlineSchool.entity.*;
import by.arabienko.onlineSchool.service.xmlparser.SAX.StudentCourseXmlTag;
import by.arabienko.onlineSchool.service.xmlparser.AbstractStudentCoursesBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * STAX XML document parser.
 */
public class StudentCourseStaxBuilder extends AbstractStudentCoursesBuilder {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCourseStaxBuilder.class);

    private final Set<StudentCourse> studentCourses
            = new HashSet<>();
    private final XMLInputFactory inputFactory;

    public StudentCourseStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    /**
     * Parser start from the first tag.
     *
     * @param fileName filename for the parser.
     */
    public void buildStudentCurses(String fileName) {
        LOGGER.debug("START STAX parser: generating "
                + "a collection of objects based "
                + "on parsing an XML document. " + fileName);
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(fileName);
            reader = inputFactory.
                    createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type==XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (StudentCourseXmlTag.valueOf(name.toUpperCase())
                            ==StudentCourseXmlTag.STUDENTCOURSE) {
                        StudentCourse studentCourse =
                                buildStudentCourse(reader);
                        studentCourses.add(studentCourse);
                    }
                }
            }
            LOGGER.debug("FINISH STAX parser: generating "
                    + "a collection of objects based "
                    + "on parsing an XML document.");
        } catch (XMLStreamException ex) {
            LOGGER.debug("StAX parsing error! "
                    + ex.getMessage());
        } catch (FileNotFoundException ex) {
            LOGGER.debug("File " + fileName
                    + " not found! " + ex);
        } finally {
            try {
                if (inputStream!=null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.debug("Impossible close file "
                        + fileName + " : " + e);
            }
        }
    }

    /**
     * Tag Parsing - Student Course Object
     *
     * @param reader
     * @return student course object
     * @throws XMLStreamException
     */
    private StudentCourse buildStudentCourse(
            XMLStreamReader reader) throws XMLStreamException {
        StudentCourse.StudentCourseBuilder builder =
                new StudentCourse.StudentCourseBuilder();
        StudentCourse studentCourse = builder.build();
        String name;
        studentCourse.setId(
                Integer.parseInt(
                        reader.getAttributeValue(
                                null, StudentCourseXmlTag.ID.getValue())));
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (StudentCourseXmlTag.valueOf(name.toUpperCase())) {
                        case COURSETEACHER:
                            TeacherCourse teacherCourse =
                                    buildTeacherCourse(reader);
                            builder.setTeacherCourse(teacherCourse);
                            break;
                        case USERINFO:
                            UserInfo userInfo =
                                    buildUserInfo(reader);
                            builder.setUserInfo(userInfo);
                            break;
                        case STATUS:
                            builder.setStatus(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (StudentCourseXmlTag.valueOf(name.toUpperCase())
                            ==StudentCourseXmlTag.STUDENTCOURSE) {
                        return studentCourse;
                    }
                    break;
            }
        }
        LOGGER.debug(
                "Unknown element in tag StudentCourse");
        throw new XMLStreamException(
                "Unknown element in tag StudentCourse");
    }

    /**
     * Tag Parsing - Teacher Course Object
     *
     * @param reader
     * @return teacher course object
     * @throws XMLStreamException
     */
    private TeacherCourse buildTeacherCourse(
            XMLStreamReader reader) throws XMLStreamException {
        TeacherCourse.TeacherCourseBuilder builder =
                new TeacherCourse.TeacherCourseBuilder();
        TeacherCourse teacherCourse = builder.build();
        String name;
        teacherCourse.setId(
                Integer.parseInt(
                        reader.getAttributeValue(
                                null, StudentCourseXmlTag.ID.getValue())));
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (StudentCourseXmlTag.valueOf(name.toUpperCase())) {
                        case TEACHERSUBJECT:
                            TeacherSubject teacherSubject =
                                    buildTeacherSubject(reader);
                            builder.setTeacherSubject(teacherSubject);
                            break;
                        case STARTDATE:
                            builder.setStartDate(getXMLText(reader));
                            break;
                        case ENDDATE:
                            builder.setEndDate(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (StudentCourseXmlTag.valueOf(name.toUpperCase())
                            ==StudentCourseXmlTag.COURSETEACHER) {
                        return teacherCourse;
                    }
                    break;
            }
        }
        LOGGER.debug(
                "Unknown element in tag TeacherCourse");
        throw new XMLStreamException(
                "Unknown element in tag TeacherCourse");

    }

    /**
     * Tag Parsing - Teacher Subject Object
     *
     * @param reader
     * @return teacher subject object
     * @throws XMLStreamException
     */
    private TeacherSubject buildTeacherSubject(
            XMLStreamReader reader) throws XMLStreamException {
        TeacherSubject.TeacherSubjectBuilder builder =
                new TeacherSubject.TeacherSubjectBuilder();
        TeacherSubject teacherSubject = builder.build();
        String name;
        teacherSubject.setId(
                Integer.parseInt(
                        reader.getAttributeValue(
                                null, StudentCourseXmlTag.ID.getValue())));
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (StudentCourseXmlTag.valueOf(name.toUpperCase())) {
                        case USERTEACHER:
                            UserInfo userInfo =
                                    buildUserInfo(reader);
                            builder.setUserInfo(userInfo);
                            break;
                        case SUBJECT:
                            Subject subject =
                                    buildSubject(reader);
                            builder.setSubject(subject);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (StudentCourseXmlTag.valueOf(name.toUpperCase())
                            ==StudentCourseXmlTag.TEACHERSUBJECT) {
                        return teacherSubject;
                    }
                    break;
            }
        }
        LOGGER.debug(
                "Unknown element in tag TeacherSubject");
        throw new XMLStreamException(
                "Unknown element in tag TeacherSubject");

    }

    /**
     * Tag Parsing - Subject Object
     *
     * @param reader
     * @return subject object
     * @throws XMLStreamException
     */
    private Subject buildSubject(
            XMLStreamReader reader)
            throws XMLStreamException {
        Subject subject = new Subject();
        int type;
        String name;
        subject.setId(
                Integer.parseInt(
                        reader.getAttributeValue(
                                null, StudentCourseXmlTag.ID.getValue())));
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if (StudentCourseXmlTag.valueOf(name.toUpperCase())
                            ==StudentCourseXmlTag.NAMESUBJECT) {
                        subject.setNameSubject(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (StudentCourseXmlTag.valueOf(name.toUpperCase())
                            ==StudentCourseXmlTag.SUBJECT) {
                        return subject;
                    }
                    break;
            }
        }
        LOGGER.debug(
                "Unknown element in tag Subject");
        throw new XMLStreamException(
                "Unknown element in tag Subject");
    }

    /**
     * Tag Parsing - User Object
     *
     * @param reader
     * @return User object
     * @throws XMLStreamException
     */
    private UserInfo buildUserInfo(
            XMLStreamReader reader) throws XMLStreamException {
        UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
        UserInfo userInfo = builder.build();
        userInfo.setId(
                Integer.parseInt(
                        reader.getAttributeValue(
                                null, StudentCourseXmlTag.ID.getValue())));
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (StudentCourseXmlTag.valueOf(name.toUpperCase())) {
                        case SURNAME:
                            builder.setSurname(getXMLText(reader));
                            break;
                        case NAME:
                            builder.setName(getXMLText(reader));
                            break;
                        case PHONE:
                            builder.setPhone(getXMLText(reader));
                            break;
                        case PATHPHOTO:
                            builder.setPathImage(getXMLText(reader)).build();
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    switch (StudentCourseXmlTag.valueOf(name.toUpperCase())) {
                        case USERTEACHER:
                        case USERINFO:
                            return userInfo;
                    }
                    break;
            }
        }
        LOGGER.debug(
                "Unknown element in tag UserInfo");
        throw new XMLStreamException(
                "Unknown element in tag UserInfo");
    }

    /**
     * Getting content between tags.
     *
     * @param reader
     * @return tag content.
     * @throws XMLStreamException
     */
    private String getXMLText(
            XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}