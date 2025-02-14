package com.shds.sma.alarm.service.mail;

import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.common.exception.MessagingBizException;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;
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

    private final static String MAIL_FROM = "spcoff@naver.com";

    private final static String MAIL_LOG_TO = "spcoff@naver.com";

    private final static String MAIL_LOG_SUBJECT = "에러로그 알림 메일 입니다.";

    private final static String MAIL_IP_SUBJECT = "IP만료 전 알림 메일 입니다.";

    /**
     * 에러로그 메일 발송
     * sendLogMail
     * @param logs
     */
    @Override
    public void sendLogMail(List<LogAlarmRequestDto> logs) {
        try {
            String subject = MAIL_LOG_SUBJECT + "_" + LocalDateTime.now();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            StringBuilder emailContent = new StringBuilder();
            setLogMessage(logs, emailContent);

            helper.setTo(MAIL_LOG_TO);
            helper.setSubject(subject);
            helper.setText(emailContent.toString());
            helper.setFrom(MAIL_FROM);  // 보낸 사람 이메일 설정
            mailSender.send(message);

            // 알림 로그

        } catch (MessagingException e) {
            throw new MessagingBizException(e);
        }
    }

    /**
     * IP만료 메일 발송
     * sendIpMail
     * @param ips
     */
    @Override
    public void sendIpMail(List<IpAlarmRequestDto> ips) {
        for (IpAlarmRequestDto ip : ips) {
            try {
                String subject = MAIL_IP_SUBJECT + "_" + LocalDateTime.now();
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                StringBuilder emailContent = new StringBuilder();
                setIpMessage(ip, emailContent);

                helper.setTo(ip.getMember().getMail());
                helper.setSubject(subject);
                helper.setText(emailContent.toString());
                helper.setFrom(MAIL_FROM);  // 보낸 사람 이메일 설정
                mailSender.send(message);

                // 알림 로그

            } catch (MessagingException e) {
                throw new MessagingBizException(e);
            }
        }
    }

    /**
     * IP만료 메일 발송
     * sendCertMail
     * @param certs
     */
    @Override
    public void sendCertMail(List<CertAlarmRequestDto> certs) {
        for (CertAlarmRequestDto cert : certs) {
            try {
                String subject = MAIL_IP_SUBJECT + "_" + LocalDateTime.now();
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                StringBuilder emailContent = new StringBuilder();
                setCertMessage(cert, emailContent);

                helper.setTo(cert.getMember().getMail());
                helper.setSubject(subject);
                helper.setText(emailContent.toString());
                helper.setFrom(MAIL_FROM);  // 보낸 사람 이메일 설정
                mailSender.send(message);

                // 알림 로그

            } catch (MessagingException e) {
                throw new MessagingBizException(e);
            }
        }
    }


//    public void sendIpMail(Member systemManager, IpResponseDto ipExpiration) {
////        List<Member> systemManagers = ipExpiration.getApplySystem().getSystemManagers();
////        for (Member systemManager : systemManagers) {
////            sendIpMail(systemManager, ipExpiration);
////        }
//
//        try {
//            String subject = MAIL_IP_SUBJECT + "_" + LocalDateTime.now();
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//            StringBuilder emailContent = new StringBuilder();
//            setIpMessage(ipExpiration, emailContent);
//
//            helper.setTo(systemManager.getMail());
//            helper.setSubject(subject);
//            helper.setText(emailContent.toString());
//            helper.setFrom(MAIL_FROM);  // 보낸 사람 이메일 설정
//            mailSender.send(message);
//
//            // 알림 로그
//
//        } catch (MessagingException e) {
//            throw new MessagingBizException(e);
//        }
//    }

    /**
     * 인증서 메시지 본문 값 세팅
     * setCertMessage
     * @param cert
     * @param emailContent
     */
    private static void setCertMessage(CertAlarmRequestDto cert, StringBuilder emailContent) {
        emailContent.append("인증서 만료전 알림 정보: \n\n");
        emailContent.append("▶ [인증서 타입]: ").append(cert.getCertType()).append("\n")
                .append("▶ [인증서 명]: ").append(cert.getCertName()).append("\n")
                .append("▶ [시작일자]: ").append(cert.getStartDate()).append("\n\n")
                .append("▶ [종료일자]: ").append(cert.getEndDate()).append("\n\n")
                .append("▶ [내용]: ").append(cert.getContent()).append("\n\n")
                .append("▶ [적용시스템]: ").append(cert.getApplySystem().getSystemName()).append("\n\n")
                .append("▶ [결재번호]: ").append(cert.getApprovalNo()).append("\n\n");
    }

    /**
     * IP 메시지 본문 값 세팅
     * setIpMessage
     * @param ip
     * @param emailContent
     */
    private static void setIpMessage(IpAlarmRequestDto ip, StringBuilder emailContent) {
        emailContent.append("IP 만료전 알림 정보: \n\n");
        emailContent.append("▶ [IP 타입]: ").append(ip.getIpType()).append("\n")
                .append("▶ [출발지 IP]: ").append(ip.getStartIpAddr()).append("\n")
                .append("▶ [도착지 IP]: ").append(ip.getEndIpAddr()).append("\n")
                .append("▶ [시작일자]: ").append(ip.getStartDate()).append("\n\n")
                .append("▶ [종료일자]: ").append(ip.getEndDate()).append("\n\n")
                .append("▶ [내용]: ").append(ip.getContent()).append("\n\n")
                .append("▶ [적용시스템]: ").append(ip.getApplySystem().getSystemName()).append("\n\n")
                .append("▶ [결재번호]: ").append(ip.getApprovalNo()).append("\n\n");
    }

    /**
     * 로그 메시지 본문 값 세팅
     * setLogMessage
     * @param logs
     * @param emailContent
     */
    private static void setLogMessage(List<LogAlarmRequestDto> logs, StringBuilder emailContent) {
        emailContent.append("에러 로그 리스트: \n\n");
        for (LogAlarmRequestDto log : logs) {
            emailContent.append("▶ [에러 ID]: ").append(log.getLogId()).append("\n")
                    .append("▶ [에러 타입]: ").append(log.getLogType()).append("\n")
                    .append("▶ [에러 코드]: ").append(log.getLogCode()).append("\n")
                    .append("▶ [발생 메시지]: ").append(log.getContent()).append("\n\n")
                    .append("▶ [발생 시간]: ").append(log.getLogDate()).append("\n\n");
        }
    }


}
