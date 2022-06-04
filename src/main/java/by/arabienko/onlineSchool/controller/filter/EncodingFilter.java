package by.arabienko.onlineSchool.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The type Character encoding filter.
 */
public class EncodingFilter implements Filter {
    private static final Logger LOGGER =
            LogManager.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        LOGGER.info("EncodingFilter finished working.");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
