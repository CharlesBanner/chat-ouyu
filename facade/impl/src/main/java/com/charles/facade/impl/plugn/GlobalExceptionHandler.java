package com.charles.facade.impl.plugn;


import com.charles.common.enums.ErrorEnum;
import com.charles.common.exception.BusinessException;
import com.charles.common.result.FoxResult;
import com.charles.common.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.charles.common.enums.ErrorEnum.INVALID_PARAMS;


@EnableWebMvc
@ControllerAdvice(annotations = { RestController.class })
@ResponseBody
@SuppressWarnings("all")
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handle business exception and wrap response
     *
     * @param cause
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public FoxResult error(BusinessException cause) {
        LOG.error("Business exception, errorCode: {}, errorDesc: {}", cause.getErrorCode().getCode(), cause.getMessage());
        return new FoxResult(Boolean.FALSE, cause.getErrorCode().getValue(), cause.getMessage(), null);
    }

    /**
     * Handle violation exception
     *
     * @param cve
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public FoxResult error(ConstraintViolationException cve) {
        LOG.error("Params violation excetion", cve);
        Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
        List<String> errorMsg = new LinkedList<>();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            for (ConstraintViolation<?> violation : constraintViolations) {
                errorMsg.add(violation.getMessage());
            }
        }
        return new FoxResult(Boolean.FALSE, INVALID_PARAMS, errorMsg.toString());
    }

    @ExceptionHandler(value = BindException.class)
    public FoxResult error(BindException bindException) {
        LOG.error("Params bind exception", bindException);
        List<String> errorMsg = new LinkedList<>();
        for (ObjectError objectError : bindException.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            errorMsg.add(fieldError.getField() + fieldError.getDefaultMessage());
        }
        return new FoxResult(Boolean.FALSE, INVALID_PARAMS, errorMsg.toString());
    }

    /**
     * Handle uncatched exception, wrap a default response
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public FoxResult error(Exception ex) {
        LOG.error("Unkown exception", ex);
        return new FoxResult(Boolean.FALSE, ErrorEnum.UNKOWN_ERROR.getValue(), ExceptionUtils.stackTrace(ex), null);
    }
}
