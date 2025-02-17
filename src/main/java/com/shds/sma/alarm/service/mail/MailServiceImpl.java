package com.shds.sma.alarm.service.mail;

import com.shds.sma.admin.entity.Member;
import com.shds.sma.alarm.dto.AlarmRequestDto;
import com.shds.sma.alarm.entity.Alarm;
import com.shds.sma.alarm.service.AlarmService;
import com.shds.sma.alarm.types.AlarmSendType;
import com.shds.sma.alarm.types.PreAlarmTarget;
import com.shds.sma.alarm.types.Sender;
import com.shds.sma.cert.dto.CertAlarmRequestDto;
import com.shds.sma.common.exception.MessagingBizException;
import com.shds.sma.ip.dto.IpAlarmRequestDto;
import com.shds.sma.log.dto.LogAlarmRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
@Primary
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    public final static String MAIL_FROM = "spcoff@naver.com";

    public final static String LOG_MAIL_TO = "spcoff@naver.com";

    public final static String MAIL_LOG_SUBJECT = "에러로그 알림 메일 입니다.";

    public final static String MAIL_IP_SUBJECT = "IP 만료 전 알림 메일 입니다.";

    public final static String MAIL_CERT_SUBJECT = "인증서 만료 전 알림 메일 입니다.";

    /**
     * 에러로그 메일 발송
     * sendLogMail
     * @param logs
     */
    @Override
    public AlarmRequestDto sendLogMail(List<LogAlarmRequestDto> logs) {
        // 메일 발송
        StringBuilder emailContent = new StringBuilder();
        setLogMessage(logs, emailContent);
        sendMail(MAIL_LOG_SUBJECT, emailContent.toString(), LOG_MAIL_TO, MAIL_FROM);

        // 알림 저장
        return AlarmRequestDto.builder()
                .alarmSendType(AlarmSendType.MAIL)
                .preAlarmTarget(PreAlarmTarget.LOG)
                .system(null)
                .sender(Sender.SYSTEM)
                .subject(MAIL_LOG_SUBJECT)
                .content(emailContent.toString())
                .preAlarm(0)
                .sendDate(LocalDateTime.now())
                .isSuccess("Y").build();
    }

    /**
     * IP만료 메일 발송
     * sendIpMail
     * @param ips
     */
    @Override
    public List<AlarmRequestDto> sendIpMail(List<IpAlarmRequestDto> ips) {
        List<AlarmRequestDto> alarmRequest = new ArrayList<>();
        for (IpAlarmRequestDto ip : ips) {
            // 메일 발송
            StringBuilder emailContent = new StringBuilder();
            setIpMessage(ip, emailContent);
            sendMail(MAIL_IP_SUBJECT, emailContent.toString(), ip.getMember().getMail(), MAIL_FROM);

            setAlarmBulid(ip, emailContent, alarmRequest);
        }

        return alarmRequest;
    }

    private static void setAlarmBulid(IpAlarmRequestDto ip, StringBuilder emailContent, List<AlarmRequestDto> alarmRequest) {
        AlarmRequestDto alarmRequestDto = AlarmRequestDto.builder()
                .alarmSendType(AlarmSendType.MAIL)
                .preAlarmTarget(PreAlarmTarget.IP)
                .system(ip.getApplySystem())
                .sender(Sender.SYSTEM)
                .subject(MAIL_IP_SUBJECT)
                .content(emailContent.toString())
                .preAlarm(ip.getApplySystem().getPreIpAlarm())
                .sendDate(LocalDateTime.now())
                .isSuccess("Y").build();
        alarmRequest.add(alarmRequestDto);
    }

    /**
     * IP만료 메일 발송
     * sendCertMail
     * @param certs
     */
    @Override
    public List<AlarmRequestDto> sendCertMail(List<CertAlarmRequestDto> certs) {
        List<AlarmRequestDto> alarmRequest = new ArrayList<>();
        for (CertAlarmRequestDto cert : certs) {
            // 메일 발송
            StringBuilder emailContent = new StringBuilder();
            setCertMessage(cert, emailContent);
            sendMail(MAIL_CERT_SUBJECT, emailContent.toString(), cert.getMember().getMail(), MAIL_FROM);

            // 알림 저장
            AlarmRequestDto alarmRequestDto = AlarmRequestDto.builder()
                    .alarmSendType(AlarmSendType.MAIL)
                    .preAlarmTarget(PreAlarmTarget.CERT)
                    .system(cert.getApplySystem())
                    .sender(Sender.SYSTEM)
                    .subject(MAIL_CERT_SUBJECT)
                    .content(emailContent.toString())
                    .preAlarm(cert.getApplySystem().getPreIpAlarm())
                    .sendDate(LocalDateTime.now())
                    .isSuccess("Y").build();
            alarmRequest.add(alarmRequestDto);
        }

        return alarmRequest;
    }

    @Override
    public List<AlarmRequestDto> sendIpToManagerMail(List<IpAlarmRequestDto> ips) {
        List<AlarmRequestDto> alarmRequest = new ArrayList<>();
        for (IpAlarmRequestDto ip : ips) {
            List<Member> systemManagers = ip.getApplySystem().getSystemManagers();
            for (Member systemManager : systemManagers) {
                // 메일 발송
                StringBuilder emailContent = new StringBuilder();
                setIpMessage(ip, emailContent);
                sendMail(MAIL_IP_SUBJECT, emailContent.toString(), systemManager.getMail(), MAIL_FROM);

                // 알림 저장
                AlarmRequestDto alarmRequestDto = AlarmRequestDto.builder()
                        .alarmSendType(AlarmSendType.MAIL)
                        .preAlarmTarget(PreAlarmTarget.IP)
                        .system(ip.getApplySystem())
                        .sender(Sender.SYSTEM)
                        .subject(MAIL_IP_SUBJECT)
                        .content(emailContent.toString())
                        .preAlarm(ip.getApplySystem().getPreIpAlarm())
                        .sendDate(LocalDateTime.now())
                        .isSuccess("Y").build();
                alarmRequest.add(alarmRequestDto);
            }
        }

        return alarmRequest;
    }

    @Override
    public List<AlarmRequestDto> sendCertToManagerMail(List<CertAlarmRequestDto> certs) {
        List<AlarmRequestDto> alarmRequest = new ArrayList<>();
        for (CertAlarmRequestDto cert : certs) {
            List<Member> systemManagers = cert.getApplySystem().getSystemManagers();
            for (Member systemManager : systemManagers) {
                // 메일 발송
                StringBuilder emailContent = new StringBuilder();
                setCertMessage(cert, emailContent);
                sendMail(MAIL_CERT_SUBJECT, emailContent.toString(), systemManager.getMail(), MAIL_FROM);

                // 알림 저장
                AlarmRequestDto alarmRequestDto = AlarmRequestDto.builder()
                        .alarmSendType(AlarmSendType.MAIL)
                        .preAlarmTarget(PreAlarmTarget.CERT)
                        .system(cert.getApplySystem())
                        .sender(Sender.SYSTEM)
                        .subject(MAIL_CERT_SUBJECT)
                        .content(emailContent.toString())
                        .preAlarm(cert.getApplySystem().getPreIpAlarm())
                        .sendDate(LocalDateTime.now())
                        .isSuccess("Y").build();
                alarmRequest.add(alarmRequestDto);
            }
        }

        return alarmRequest;
    }

    /**
     * 메일 발송 처리
     * sendMail
     * @param mailSubject
     * @param text
     * @param toMail
     * @param fromMail
     */
    private void sendMail(String mailSubject, String text, String toMail, String fromMail) {
        try {
            String subject = mailSubject + "_" + LocalDateTime.now();
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(fromMail);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MessagingBizException(e);
        }
    }

    /**
     * 인증서 메시지 본문 값 세팅
     * setCertMessage
     * @param cert
     * @param emailContent
     */
    private static void setCertMessage(CertAlarmRequestDto cert, StringBuilder emailContent) {
        emailContent.append("인증서 만료전 알림 정보: \n\n");
        emailContent.append("▶ [적용 대상자]: ").append(cert.getMember().getName()).append("\n")
                .append("▶ [인증서 타입]: ").append(cert.getCertType()).append("\n")
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
        emailContent.append("▶ [적용대상자]: ").append(ip.getMember().getName()).append("\n")
                .append("▶ [IP 타입]: ").append(ip.getIpType()).append("\n")
                .append("▶ [출발지 IP]: ").append(ip.getStartIpAddr()).append("\n")
                .append("▶ [도착지 IP]: ").append(ip.getEndIpAddr()).append("\n")
                .append("▶ [포트]: ").append(ip.getPort()).append("\n")
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
