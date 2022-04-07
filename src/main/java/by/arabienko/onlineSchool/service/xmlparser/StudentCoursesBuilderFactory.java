package by.arabienko.onlineSchool.service.xmlparser;

import by.arabienko.onlineSchool.service.xmlparser.DOM.StudentCourseDomBuilder;
import by.arabienko.onlineSchool.service.xmlparser.SAX.StudentCoursesSAXBuilder;
import by.arabienko.onlineSchool.service.xmlparser.StAX.StudentCourseStaxBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *The Factory Method pattern is designed to create objects
 * that are in a hierarchical relationship.
 * In this situation, the class implementing
 * the template will produce instances of subclasses
 * of the StudentCoursesBuilderFactory abstract class.
 * A decision about a specific type will be made based on
 * the string value passed to the factory method
 * with the name of the desired parser.
 */
public class StudentCoursesBuilderFactory {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCoursesBuilderFactory.class);

    public StudentCoursesBuilderFactory() {
    }
    public static AbstractStudentCoursesBuilder createStudentBuilder(String typeParser) {

        // insert parser name validation
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new StudentCourseDomBuilder();
            case STAX:
                return new StudentCourseStaxBuilder();
            case SAX:
                return new StudentCoursesSAXBuilder();
            default:
                    LOGGER.debug("no parse" + type);
                return null;
                /*throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());*/
        }
    }
}
