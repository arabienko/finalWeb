package by.arabienko.onlineSchool.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class HelloTag extends TagSupport {
    private static final Logger LOGGER =
            LogManager.getLogger(HelloTag.class);
    private Long role;

    @Override
    public int doStartTag() throws JspException {
        LOGGER.info("Start doStartTagHELLO...");
        String to = "   Welcome, guest.";
        String logOut = "";
        try {
            if (role!=null) {

                if (role==0) {
                    to = "Welcome, admin.-->>";
                    pageContext.getOut().write("<br>");
                    logOut = "<form method=\"POST\" " +
                            "action=\"${pageContext.request.contextPath}/my\"/>" +
                            "<a href=\"/webProject/my?command=logOut\">    LOG OUT</a>" +
                            "</form>";
                }
                if (role==1) {
                    to = "Welcome, teacher.-->>";
                    logOut = "<form method=\"POST\" " +
                            "action=\"${pageContext.request.contextPath}/my\"/>" +
                            "<a href=\"/webProject/my?command=logOut\">    LOG OUT</a>" +
                            "</form>";
                }
                if (role==2) {
                    to = "Welcome, student.-->>";
                    logOut = "<form method=\"POST\" " +
                            "action=\"${pageContext.request.contextPath}/my\"/>" +
                            "<a href=\"/webProject/my?command=logOut\">    LOG OUT</a>" +
                            "</form>";
                }
                pageContext.getOut().write("<br><hr/>" + to + "<hr/>");
                pageContext.getOut().write("<br><hr/>" + logOut + "<hr/>");
            }
        } catch (IOException e) {
            LOGGER.error("Hello tag error: " + e);
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    public void setRole(Long role) {
        this.role = role;
    }
}
