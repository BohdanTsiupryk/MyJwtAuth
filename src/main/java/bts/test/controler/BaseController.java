package bts.test.controler;

import bts.test.exception.BadPasswordException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@ControllerAdvice
public class BaseController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MetaData> handle(Exception exception) {
        Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        MetaData metaData = new MetaData(exception.getMessage(), now, HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(metaData);
    }

    @ExceptionHandler(BadPasswordException.class)
    public ResponseEntity<MetaData> handle403(BadPasswordException exception) {
        Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        MetaData metaData = new MetaData(exception.getMessage(), now, HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(metaData);
    }

    @AllArgsConstructor
    public static class MetaData {
        private String errorMessage;
        private Date date;
        private HttpStatus status;
    }
}
