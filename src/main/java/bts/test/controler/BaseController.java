package bts.test.controler;

import bts.test.exception.BadPasswordException;
import bts.test.exception.UserAlreadyExistException;
import bts.test.util.DateUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.Data;
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
        MetaData metaData = new MetaData(exception.getMessage(), DateUtil.getNowDate(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(metaData);
    }

    @ExceptionHandler(BadPasswordException.class)
    public ResponseEntity<MetaData> handle403(Exception exception) {
        MetaData metaData = new MetaData(exception.getMessage(), DateUtil.getNowDate(), HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(metaData);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<MetaData> handleExisting(UserAlreadyExistException exception) {
        MetaData metaData = new MetaData(exception.getMessage(), DateUtil.getNowDate(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(metaData);
    }

    @AllArgsConstructor
    @Data
    public static class MetaData {
        private String errorMessage;
        private Date date;
        private HttpStatus status;
    }
}
