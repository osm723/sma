package com.shds.sma.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Component
@Slf4j
public class JobListener extends JobExecutionListenerSupport {


    /**
     * Listener (리스너)
     * 역할: Job 또는 Step의 실행 전후에 추가 작업(예: 로깅, 리소스 정리, 알림 등)을 수행합니다.
     * 설명: JobExecutionListener, StepExecutionListener, ChunkListener 등 다양한 리스너 인터페이스를 구현하여
     * 배치 처리의 특정 시점에 동작할 코드를 삽입할 수 있습니다.
     * @param jobExecution
     */

    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getJobId();
        log.info("jobId={} !!! JOB START !!!",jobExecution.getJobId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            Duration duration = getDuration(jobExecution);
            // 초 단위로 구하기
            //long seconds = duration.getSeconds();
            // 밀리초 단위로 구하기
            long millis = duration.toMillis();

            log.info("jobId={} 배치 실행 시간: {}ms", jobExecution.getJobId(), millis);
            log.info("jobId={} !!! JOB FINISHED !!!", jobExecution.getJobId());
        }
    }

    /**
     * getDuration
     * 시간차 구하기
     * @param jobExecution
     * @return
     */
    private static Duration getDuration(JobExecution jobExecution) {
        LocalDateTime startTime = jobExecution.getStartTime();
        LocalDateTime endTime = jobExecution.getEndTime();
        Duration duration = Duration.between(startTime, endTime);
        return duration;
    }
}
