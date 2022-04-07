package by.arabienko.onlineSchool.service.xmlparser.DOM;

import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.entity.*;
import by.arabienko.onlineSchool.service.xmlparser.AbstractStudentCoursesBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudentCourseDomBuilder extends AbstractStudentCoursesBuilder {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCourseDomBuilder.class);
    private final Set<StudentCourse> studentCourses;
    private DocumentBuilder documentBuilder = null;
    private Schema schema;

    public StudentCourseDomBuilder() {
        studentCourses = new HashSet<>();
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        try {
            //factory.setValidating(true);
            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory xsdFactory =
                    SchemaFactory.newInstance(constant);
            schema = xsdFactory.newSchema(
                    new File("D:\\JavaProg\\task07xml\\src" +
                            "\\main\\resources\\date\\curses.xsd"));
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error("Parsing error. " + e);
        }
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    /**
     * Build Entity from XML File
     *
     * @param fileName
     */
    public void buildStudentCurses(String fileName) {
        try {
            if (fileName.length()==0) {
                LOGGER.debug(
                        "Document is not exist. " + fileName);
                throw new ExceptionService(
                        "Document is not exist. " + fileName);
            }
            LOGGER.debug("DOM parser: generating " +
                    "a collection of objects based " +
                    "on parsing an XML document. ");
            Validator validator = schema.newValidator();

            FileInputStream inputStream =
                    new FileInputStream(new File(fileName));
            Document document;
            document = documentBuilder.parse(inputStream);
            DOMSource source = new DOMSource(document);
            validator.validate(source);
            LOGGER.debug(
                    fileName + " is valid.");
            Element root = document.getDocumentElement();
            // getting a list of <studentCourse> child elements
            NodeList studentCourseList =
                    root.getElementsByTagName("studentCourse");
            for (int i = 0; i < studentCourseList.getLength(); i++) {
                Element studentCourseElement =
                        (Element) studentCourseList.item(i);
                StudentCourse studentCourse =
                        buildStudentCourse(studentCourseElement);
                studentCourses.add(studentCourse);
            }
        } catch (SAXException e) {
            LOGGER.debug("ошибка DOM парсера:{}. ",
                    fileName + e.getMessage());
        } catch (IOException e) {
            LOGGER.debug("ошибка I/О потока (DOM): "
                    + e.getMessage());
        } catch (ExceptionService exceptionService) {
            LOGGER.debug(
                    "Document is not exist (DOM). " + fileName);
        }
    }

    private StudentCourse buildStudentCourse(
            Element studentCourseElement) {
        StudentCourse.StudentCourseBuilder builder =
                new StudentCourse.StudentCourseBuilder();
        StudentCourse studentCourse =
                builder.build();
        try {
            if (studentCourseElement==null) {
                throw new ExceptionService("Element is empty. ");
            }
            int id = Integer.parseInt(
                    studentCourseElement.getAttribute("id"));
            studentCourse.setId(id);
            NodeList teacherCourseList =
                    studentCourseElement.
                            getElementsByTagName("courseTeacher");
            Element teacherCourseNode =
                    (Element) teacherCourseList.item(0);
            TeacherCourse teacherCourse =
                    buildTeacherCourse(teacherCourseNode);
            builder.setTeacherCourse(teacherCourse);
            builder.setStatus(getElementTextContent(
                    studentCourseElement, "status"));
            NodeList userInfoList =
                    studentCourseElement.
                            getElementsByTagName("userInfo");
            Element userInfoNode =
                    (Element) userInfoList.item(0);
            UserInfo userInfo =
                    buildUserInfo(userInfoNode);
            builder.
                    setUserInfo(userInfo);

        } catch (ExceptionService exceptionService) {
            LOGGER.error("Element studentCourseElement is empty. ");
        }
        return studentCourse;
    }

    private TeacherCourse buildTeacherCourse(
            Element curseTeacherElement) {
        TeacherCourse.TeacherCourseBuilder
                builder = new TeacherCourse.TeacherCourseBuilder();
        TeacherCourse teacherCourse = builder.build();
        try {
            if (curseTeacherElement==null) {
                throw new ExceptionService("Element is empty. ");
            }
            int id = Integer.parseInt(
                    curseTeacherElement.getAttribute("id"));
            teacherCourse.setId(id);
            builder.setStartDate(
                    getElementTextContent(
                            curseTeacherElement, "startDate"));
            builder.
                    setEndDate(
                            getElementTextContent(
                                    curseTeacherElement, "endDate"));
            NodeList teacherSubjectList =
                    curseTeacherElement.
                            getElementsByTagName("teacherSubject");
            Element teacherSubjectNode =
                    (Element) teacherSubjectList.item(0);
            TeacherSubject teacherSubject =
                    buildTeacherSubject(teacherSubjectNode);
            builder.
                    setTeacherSubject(teacherSubject);
        } catch (ExceptionService e) {
            LOGGER.error("Element curseTeacherElement is empty. ");
        }
        return teacherCourse;
    }

    private TeacherSubject buildTeacherSubject(
            Element teacherSubjectNode) {
        TeacherSubject.TeacherSubjectBuilder builder =
                new TeacherSubject.TeacherSubjectBuilder();
        TeacherSubject teacherSubject = builder.build();
        try {
            if (teacherSubjectNode==null) {
                throw new ExceptionService("Element is empty. ");
            }
            int id = Integer.parseInt(
                    teacherSubjectNode.getAttribute("id"));
            teacherSubject.setId(id);
            NodeList userInfoList =
                    teacherSubjectNode.
                            getElementsByTagName("userTeacher");
            Element userInfoNode =
                    (Element) userInfoList.item(0);
            UserInfo userInfo =
                    buildUserInfo(userInfoNode);
            builder.setUserInfo(userInfo);
            NodeList subjectList =
                    teacherSubjectNode.
                            getElementsByTagName("subject");
            Element subjectNode =
                    (Element) subjectList.item(0);
            Subject subject =
                    buildSubject(subjectNode);
            builder.setSubject(subject);
        } catch (ExceptionService exceptionService) {
            LOGGER.error("Element teacherSubjectNode is empty. ");
        }
        return teacherSubject;
    }

    private Subject buildSubject(Element subjectNode) {
        Subject subject = new Subject();
        try {
            if (subjectNode==null) {
                throw new ExceptionService("Element is empty. ");
            }
            int id = Integer.parseInt(
                    subjectNode.getAttribute("id"));
            subject.setId(id);
            subject.setNameSubject(
                    getElementTextContent(
                            subjectNode, "nameSubject"));
            String description =
                    subjectNode.getAttribute("description");
            subject.setDescription(description);
        } catch (ExceptionService e) {
            LOGGER.error("Element subjectNode is empty. ");
        }
        return subject;
    }

    private UserInfo buildUserInfo(Element userInfoNode) {
        UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
        UserInfo userInfo = builder.build();
        try {
            if (userInfoNode==null) {
                throw new ExceptionService("Element is empty. ");
            }
            int id = Integer.parseInt(
                    userInfoNode.getAttribute("id"));
            userInfo.setId(id);
            builder.setSurname(
                    getElementTextContent(
                            userInfoNode, "surname"));
            builder.setName(
                    getElementTextContent(
                            userInfoNode, "name"));
            builder.setPhone(
                    getElementTextContent(
                            userInfoNode, "phone"));
            builder.setPathImage(
                    getElementTextContent(
                            userInfoNode, "pathPhoto"));
        } catch (ExceptionService e) {
            LOGGER.error("Element userInfoNode is empty. ");
        }
        return userInfo;
    }

    // get the text content of the tag
    private static String getElementTextContent(
            Element element, String elementName) {
        NodeList nList = null;
        try {
            if (element==null ||
                    elementName.length()==0) {
                throw new ExceptionService(
                        "Element or element name is empty. ");
            }
            nList = element.
                    getElementsByTagName(elementName);
        } catch (ExceptionService e) {
            LOGGER.debug("Element or element name is empty. ");
        }
        if (nList!=null) {
            return nList.item(0).getTextContent();
        }
        return null;
    }
}
