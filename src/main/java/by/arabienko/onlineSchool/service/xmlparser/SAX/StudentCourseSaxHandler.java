package by.arabienko.onlineSchool.service.xmlparser.SAX;

import by.arabienko.onlineSchool.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * SAX XML document parser.
 */
public class StudentCourseSaxHandler extends DefaultHandler {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCourseSaxHandler.class);

    private StudentCourse studentCourseCurrent;
    private StudentCourse.StudentCourseBuilder studentCourseBuilder;
    private TeacherCourse teacherCourseCurrent;
    private TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder;
    private TeacherSubject teacherSubjectCurrent;
    private TeacherCourse.TeacherCourseBuilder teacherCourseBuilder;
    private UserInfo userInfoCurrent;
    private UserInfo.UserBuilder builder;
    private Subject subjectCurrent;
    private final Set<StudentCourse> studentCourses;
    private StudentCourseXmlTag currentXmlTag;
    private final EnumSet<StudentCourseXmlTag> withText;

    public StudentCourseSaxHandler() {
        this.studentCourses = new HashSet<>();
        this.withText = EnumSet.
                range(StudentCourseXmlTag.SURNAME,
                        StudentCourseXmlTag.STATUS);
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    /**
     * Start parse the document
     */
    @Override
    public void startDocument() {
        LOGGER.debug("START SAX parser: generating "
                + "a collection of objects based "
                + "on parsing an XML document ");
    }

    /**
     * Finish parse the document.
     */
    @Override
    public void endDocument() {
        LOGGER.debug("FINISH SAX parser: generating "
                + "a collection of objects based "
                + "on parsing an XML document ");
    }

    /**
     * Start parsing the XML
     * content of the document.
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     */
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        //Parse document by start tag
        switch (Objects.requireNonNull(
                StudentCourseXmlTag.findByKey(qName))) {
            case STUDENTCOURSE:
                studentCourseBuilder =
                        new StudentCourse.StudentCourseBuilder();
                studentCourseCurrent = studentCourseBuilder.build();
                int id = Integer.
                        parseInt(attributes.getValue(0));
                studentCourseCurrent.setId(id);
                break;
            case COURSETEACHER:
                teacherCourseBuilder = new TeacherCourse.TeacherCourseBuilder();
                teacherCourseCurrent = teacherCourseBuilder.build();
                teacherCourseCurrent.setId(
                        Integer.parseInt(attributes.getValue(0)));
                break;
            case TEACHERSUBJECT:
                teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                teacherSubjectCurrent = teacherSubjectBuilder.build();
                teacherSubjectCurrent.setId(
                        Integer.parseInt(attributes.getValue(0)));
                break;
            case USERTEACHER:
            case USERINFO:
                builder = new UserInfo.UserBuilder();
                userInfoCurrent = builder.build();
                userInfoCurrent.setId(
                        Integer.parseInt(attributes.getValue(0)));
                break;
            case SUBJECT:
                subjectCurrent = new Subject();
                subjectCurrent.setId(
                        Integer.parseInt(attributes.getValue(0)));
                break;
            default:
                StudentCourseXmlTag temp =
                        StudentCourseXmlTag.findByKey(qName);
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
        }
    }

    /**
     * Completion of the formation of Entities
     * according to the content of the tags
     * at the place where the corresponding
     * tag is closed.
     *
     * @param uri
     * @param localName
     * @param qName
     */
    @Override
    public void endElement(String uri,
                           String localName, String qName) {
        switch (Objects.requireNonNull(
                StudentCourseXmlTag.findByKey(qName))) {
            case TEACHERSUBJECT:
                teacherSubjectBuilder.
                        setSubject(subjectCurrent);
                teacherSubjectBuilder.
                        setUserInfo(userInfoCurrent);
                break;
            case COURSETEACHER:
                teacherCourseBuilder.
                        setTeacherSubject(teacherSubjectCurrent);
                break;
            case STUDENTCOURSE:
                studentCourseBuilder.
                        setTeacherCourse(teacherCourseCurrent);
                studentCourseBuilder.
                        setUserInfo(userInfoCurrent);
                studentCourses.
                        add(studentCourseCurrent);
                break;
        }
    }

    /**
     * Reading content in tags and writing
     * to the corresponding objects.
     *
     * @param ch
     * @param start
     * @param length
     */
    @Override
    public void characters(char[] ch,
                           int start, int length) {
        String data = new String(
                ch, start, length).trim();
        if (currentXmlTag!=null) {
            switch (currentXmlTag) {
                case SURNAME:
                    builder.setSurname(data);
                    break;
                case NAME:
                    builder.setName(data);
                    break;
                case PHONE:
                    builder.setPhone(data);
                    break;
                case PATHPHOTO:
                    builder.setPathImage(data);
                    break;
                case NAMESUBJECT:
                    subjectCurrent.setNameSubject(data);
                    break;
                case STARTDATE:
                    teacherCourseBuilder.setStartDate(data);
                case ENDDATE:
                    teacherCourseBuilder.setEndDate(data);
                    break;
                case STATUS:
                    studentCourseBuilder.setStatus(data);
                    break;
                default:
                    LOGGER.debug("Enum Constant Not Present Exception-> "
                            + currentXmlTag.name());
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(),
                            currentXmlTag.name());
            }
            currentXmlTag = null;
        }
    }
}

