package com.shds.sma.batch.scheduler;


import com.shds.sma.cert.service.CertService;
import com.shds.sma.common.exception.JobException;
import com.shds.sma.log.service.LogService;
import com.shds.sma.ip.service.IpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchScheduler {

    private final LogService logService;

    private final IpService ipService;

    private final CertService certService;

    private final JobLauncher jobLauncher;

    private final Job importPersonJob;

    private final Job processOrderJob;

    private final Job expiredIpJob;

    private final Job expiredCertJob;

    private final Job errorLogDailyJob;

    public BatchScheduler(LogService logService, IpService ipService, CertService certService, JobLauncher jobLauncher,
                          @Qualifier("importPersonJob") Job importPersonJob,
                          @Qualifier("processOrderJob") Job processOrderJob,
                          @Qualifier("expiredIpJob") Job expiredIpJob,
                          @Qualifier("expiredCertJob") Job expiredCertJob,
                          @Qualifier("errorLogDailyJob") Job errorLogDailyJob
                          ) {
        this.logService = logService;
        this.ipService = ipService;
        this.certService = certService;
        this.jobLauncher = jobLauncher;
        this.importPersonJob = importPersonJob;
        this.processOrderJob = processOrderJob;
        this.expiredIpJob = expiredIpJob;
        this.expiredCertJob = expiredCertJob;
        this.errorLogDailyJob = errorLogDailyJob;
    }

    /**
     * 10분마다 에러로그를 조회해서 5건 이상이면 알림 발송
     * alarmErrorLogForTenMin
     */
    @Scheduled(cron = "0 */10 * * * *")
    //@Scheduled(fixedRate = 600000)
    public void alarmErrorLogForTenMin() {
        logService.getLogErrorForTenMin();
    }



    /**
     * Ip 만료도래시 알림 발송
     * 매일 10시, 16시 발송
     * alarmPreExpiration
     */
    @Scheduled(cron = "0 0 10,16 * * *")
    public void alarmIpPreExpiration() {
        ipService.getIpPreExpiration();
    }

    /**
     * 인증서 만료도래시 알림 발송
     * 매일 10시10분, 16시10분 발송
     * alarmPreExpiration
     */
    @Scheduled(cron = "0 10 10,16 * * *")
    public void alarmCertPreExpiration() {
        certService.getCertPreExpiration();
    }

    /**
     * Ip 만료 하루전 담당자들에게 알림 발송
     * 매일 8시, 12시, 16시, 18시 발송
     * alarmIpPreExpirationToManager
     */
    @Scheduled(cron = "0 0 8,12,16,18 * * *")
    public void alarmIpPreExpirationToManager() {
        ipService.getIpPreExpirationToManager();
    }

    /**
     * 인증서 만료 하루전 담당자들에게 알림 발송
     * 매일 8시, 12시, 16시, 18시 발송
     * alarmCertExpirationToManager
     */
    @Scheduled(cron = "0 0 8,12,16,18 * * *")
    public void alarmCertExpirationToManager() {
        certService.getCertPreExpirationToManager();
    }

    /**
     * sprig batch 실행 순서
     * 1. Scheduler
     * 2. JobLauncher
     * 3. Job
     * 4. Step
     * 5. Reader
     * 6. Processor
     * 7. Writer
     * 8. Listener
     */

    /**
     * 10분마다 에러로그를 조회해서 5건 이상이면 알림 발송
     * alarmErrorLogForTenMin
     */
    @Scheduled(cron = "0 */10 * * * *")
    public void errorLogTenMinJob() {
        logService.getLogErrorForTenMin();
    }

    /**
     * 하루동안 에러로그를 조회해서 알림 발송
     * 매일 8시
     * errorLogDailyJob
     */
//    //AS-IS
//    @Scheduled(cron = "0 8 0 * * *")
//    public void alarmErrorLogForDaily() {
//        logService.getLogErrorForDaily();
//    }
    @Scheduled(cron = "0 8 0 * * *")
    public void errorLogDailyJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("errorLogDailyJob", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution execution = jobLauncher.run(errorLogDailyJob, params);
            log.info("errorLogDailyJob 실행 상태: {}", execution.getStatus());
        } catch (Exception e) {
            throw new JobException(e);
        }
    }

    /**
     * 기간이 만료된 IP 사용유무 N 처리
     * 매일 1시 5분
     * expiredIpJob
     */
    @Scheduled(cron = "0 5 1 * * ?")
    public void expiredIpJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("expiredIpJob", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution execution = jobLauncher.run(expiredIpJob, params);
            log.info("expiredIpJob 실행 상태: {}", execution.getStatus());
        } catch (Exception e) {
            throw new JobException(e);
        }
    }

    /**
     * 기간이 만료된 인증서 사용유무 N 처리
     * 매일 1시 10분
     * expiredCertJob
     */
    @Scheduled(cron = "30 0/1 * * * ?")
    public void expiredCertJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("expiredCertJob", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution execution = jobLauncher.run(expiredCertJob, params);
            log.info("expiredCertJob 실행 상태: {}", execution.getStatus());
        } catch (Exception e) {
            throw new JobException(e);
        }
    }

    // Person 배치는 매 분마다 실행 (cron: 매 분 0초에 실행)
    //@Scheduled(cron = "10 0/1 * * * ?")
    public void schedulePersonJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("personJobTime", System.currentTimeMillis())
                .toJobParameters();
        JobExecution execution = jobLauncher.run(importPersonJob, params);
        log.info("personJobTime 실행 상태: {}", execution.getStatus());
    }

    // Order 배치는 매 2분마다 실행 (cron: 매 2분마다 0초에 실행)
    //@Scheduled(cron = "30 0/1 * * * ?")
    public void scheduleOrderJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("orderJobTime", System.currentTimeMillis())
                .toJobParameters();
        JobExecution execution = jobLauncher.run(processOrderJob, params);
        log.info("orderJobTime 실행 상태: {}", execution.getStatus());
    }

}
