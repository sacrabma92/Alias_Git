
package exception.handler;

import dto.ErrorDetail;
import exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(RestExceptionHandler exception, HttpServletRequest httpServletRequest){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Recurso no encontrado");
        errorDetail.setDetail(exception.getClass().getName());
        //errorDetail.setDeveloperMessage(exception.getMessage());
        return new ResponseEntity<>(errorDetail,null,HttpStatus.NOT_FOUND);
    }
}
