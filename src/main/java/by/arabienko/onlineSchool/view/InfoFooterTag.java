package by.arabienko.onlineSchool.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;

@SuppressWarnings("serial")
public class InfoFooterTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        String time = "<b> " + gc.getWeekYear() + " </b>";
        String locale = "   <b> " + Locale.getDefault() + " </b>";
        String name = "<p>&#169; Arabiyenka Tatsiana</p>";
        String email = "<p>t.v.arabienko@gmail.com</p>";

        try {
            JspWriter out = pageContext.getOut();
            out.write(name + email + time + locale);
            out.close();
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
