package by.arabienko.onlineSchool.service.xmlparser.SAX;

/**
 * Enumeration of XML file tags.
 */
public enum StudentCourseXmlTag {
    COURSES("courses"),
    STUDENTCOURSE("studentCourse"),
    ID("id"),
    COURSETEACHER("courseTeacher"),
    TEACHERSUBJECT("teacherSubject"),
    USERINFO("userInfo"),
    USERTEACHER("userTeacher"),
    SUBJECT("subject"),
    SURNAME("surname"),
    NAME("name"),
    PHONE("phone"),
    PATHPHOTO("pathPhoto"),
    STARTDATE("startDate"),
    ENDDATE("endDate"),
    NAMESUBJECT("nameSubject"),
    STATUS("status");

    private String value;

    StudentCourseXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // get enum by key
    public static StudentCourseXmlTag findByKey(String key) {
        for (StudentCourseXmlTag v : StudentCourseXmlTag.values()) {
            if (v.getValue().equals(key)) {
                return v;
            }
        }
        return null;
    }
}
