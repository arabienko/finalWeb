package by.arabienko.onlineSchool.service.xmlparser.SAX;

import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.xmlparser.AbstractStudentCoursesBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Formation of a collection
 * of objects based on the parsing
 * of an XML document
 */
public class StudentCoursesSAXBuilder extends AbstractStudentCoursesBuilder {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCoursesSAXBuilder.class);

    private Set<StudentCourse> studentCourseSet;
    private StudentCourseSaxHandler courseSaxHandler;
    private XMLReader reader;
    private Schema schema;

    public StudentCoursesSAXBuilder() {
        // creating a SAX parser
        courseSaxHandler = new StudentCourseSaxHandler();
        try {
            // creating a handler object
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser =
                    spf.newSAXParser();
            // create schema
            String constant =
                    XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory xsdFactory =
                    SchemaFactory.newInstance(constant);
            schema = xsdFactory.newSchema(
                    new File("D:\\JavaProg\\task07xml\\src" +
                            "\\main\\resources\\date\\curses.xsd"));
            // set schema
            spf.setSchema(schema);
            spf.setNamespaceAware(true);
            spf.setValidating(false);
            reader = parser.getXMLReader();
            reader.setContentHandler(courseSaxHandler);
        } catch (SAXException | ParserConfigurationException e) {
            LOGGER.debug("ошибка SAX парсера: " + e);
        }
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourseSet;
    }

    public void buildStudentCurses(String fileName) {
        try {
            if (fileName.length()==0) {
                throw new ExceptionService(
                        "Document is not exist. "
                                + fileName);
            }
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            LOGGER.debug(
                    fileName + " is valid.");
            // parsing an XML document
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.debug("ошибка SAX парсера:{}. ",
                    fileName + e.getMessage());
        } catch (IOException e) {
            LOGGER.debug("ошибка I/О потока (SAX): "
                    + e.getMessage());
        } catch (ExceptionService exceptionService) {
            LOGGER.debug(
                    "Document is not exist (SAX). "
                            + fileName);
        }
        studentCourseSet =
                courseSaxHandler.getStudentCourses();
    }
}
