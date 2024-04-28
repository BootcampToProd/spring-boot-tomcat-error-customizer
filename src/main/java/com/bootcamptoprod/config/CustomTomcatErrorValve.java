package com.bootcamptoprod.config;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ErrorReportValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.Writer;

public class CustomTomcatErrorValve extends ErrorReportValve {

    private static final Logger logger = LoggerFactory.getLogger(CustomTomcatErrorValve.class);

    @Override
    protected void report(Request request, Response response, Throwable throwable) {

        logger.error("Tomcat failed to prepare the request for spring", throwable);

        if (!response.setErrorReported())
            return;

        HttpStatus status = HttpStatus.valueOf(response.getStatus());

        try {
            Writer writer = response.getReporter();

            if (status == HttpStatus.NOT_FOUND) {
                logger.error("Customizing response for 404 error using CustomTomcatErrorValve", throwable);
                String errorJson = String.format("""
                        {
                          "message": "Resource not found",
                          "code": %d
                        }""", status.value());
                writer.write(errorJson);
                response.setContentType("application/json");


                // Use below code to set HTML response instead of the JSON response that is configured above.
                /*String errorHtml = """
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <meta charset="ISO-8859-1">
                            <title>404 Error</title>
                        </head>
                        <body>
                            <h3>404 - Resource not found</h3>
                        </body>
                        </html>
                        """;
                writer.write(errorHtml);
                response.setContentType("text/html");*/

                response.finishResponse();
            }
        } catch (IOException exception) {
            logger.error("IOException encountered.", exception);
        }
    }
}