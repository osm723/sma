package com.shds.sma.common.advice;

import com.shds.sma.common.exception.BizException;
import com.shds.sma.common.exception.ErrorResult;
import com.shds.sma.common.exception.MessagingBizException;
import com.shds.sma.log.dto.LogRequestDto;
import com.shds.sma.log.service.LogService;
import com.shds.sma.common.types.ErrorType;
import com.shds.sma.log.types.LogType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

import static com.shds.sma.common.types.ErrorType.*;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalApiExHandler {

    private final LogService logService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        saveLog(BAD_REQUEST, e);
        return new ErrorResult(BAD_REQUEST.toString(), "잘못된 요청 정보입니다.", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult exHandle(MethodArgumentNotValidException e) {
        log.error("[exceptionHandle] ex", e);
        saveLog(NOT_VALID, e);
        return new ErrorResult(NOT_VALID.name(), "요청 값이 잘못 되었습니다.", e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResult exHandle(NoResourceFoundException e) {
        log.error("[exceptionHandle] ex", e);
        saveLog(NO_RESOURCE, e);
        return new ErrorResult(NO_RESOURCE.name(), "요청 정보를 찾을수 없습니다." , e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(BizException e) {
        log.error("[exceptionHandle] ex", e);
        saveLog(BIZ_EX, e);
        return new ErrorResult(BIZ_EX.name(), "내부 오류가 발생했습니다. 관리자에게 문의 바랍니다.", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(MessagingBizException e) {
        log.error("[exceptionHandle] ex", e);
        saveLog(MESSAGE_EX, e);
        return new ErrorResult(MESSAGE_EX.name(), "메시지 오류가 발생했습니다. 관리자에게 문의 바랍니다.", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        saveLog(EX, e);
        return new ErrorResult(EX.name(), "내부 오류가 발생했습니다. 관리자에게 문의 바랍니다.", e.getMessage());
    }

    private void saveLog(ErrorType errorType ,Exception e) {
        logService.saveLog(new LogRequestDto(LogType.ERROR, errorType.name(), e.getMessage(), null, LocalDateTime.now()));
    }

}
