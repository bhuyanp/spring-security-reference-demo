package com.example.springsecurityexamples;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Configuration
@Slf4j
public class AppErrorViewResolver implements ErrorViewResolver {

    /**
     * The Constant ERROR_VIEW_PREFIX.
     */
    private static final String ERROR_VIEW_PREFIX = "error/";

    @Autowired
    Environment env;

    public static final String ERROR_TEMPLATE = "error/error";


    @Override
    public ModelAndView resolveErrorView(final HttpServletRequest request,
                                         final HttpStatus status, final Map<String, Object> model) {
        String view =
                switch (status) {
                    case UNAUTHORIZED, FORBIDDEN, NOT_FOUND -> ERROR_VIEW_PREFIX + status.value();
                    default -> ERROR_VIEW_PREFIX.concat("500");
                };
        log.debug("Status:{}. Final error view:{}", status, view);
        return new ModelAndView(view, model, status);
    }

}