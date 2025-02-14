package com.shds.sma.common.alarm.mail;

import com.shds.sma.common.exception.MessagingBizException;
import com.shds.sma.common.log.dto.LogErrorResponseDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
@Primary
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final static String MAIL_TO = "";
    private final static String MAIL_SUBJECT = "";
    /**
     * 에러로그 메일 발송
     * @param errorLogs
     * @throws MessagingException
     */
    @Override
    public void sendMail(List<LogErrorResponseDto> errorLogs) {
        try {
            String subject = MAIL_SUBJECT + "_" + LocalDateTime.now();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            StringBuilder emailContent = new StringBuilder();
            setMessage(errorLogs, emailContent);

            helper.setTo(MAIL_TO);
            helper.setSubject(subject);
            helper.setText(emailContent.toString());
            helper.setFrom("spcoff@naver.com");  // 보낸 사람 이메일 설정
            mailSender.send(message);

            // 알림 로그



        } catch (MessagingException e) {
            throw new MessagingBizException(e);
        }
    }

    /**
     * 메시지 본문 값 세팅
     * @param errorLogs
     * @param emailContent
     */
    private static void setMessage(List<LogErrorResponseDto> errorLogs, StringBuilder emailContent) {
        emailContent.append("에러 로그 리스트: \n\n");
        for (LogErrorResponseDto log : errorLogs) {
            emailContent.append("▶ [에러 ID]: ").append(log.getLogId()).append("\n")
                    .append("▶ [에러 타입]: ").append(log.getLogType()).append("\n")
                    .append("▶ [에러 코드]: ").append(log.getLogCode()).append("\n")
                    .append("▶ [발생 메시지]: ").append(log.getContent()).append("\n\n")
                    .append("▶ [발생 시간]: ").append(log.getLogDate()).append("\n\n");
        }
    }


}
