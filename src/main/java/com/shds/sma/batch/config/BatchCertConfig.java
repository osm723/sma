package com.shds.sma.batch.config;

import com.shds.sma.batch.listener.JobListener;
import com.shds.sma.batch.processor.ExpiredCertProcessor;
import com.shds.sma.cert.entity.Cert;
import com.shds.sma.cert.repository.CertRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchCertConfig {

    private final EntityManagerFactory emf;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    private final CertRepository certRepository;


    // ===== Job : expiredCertJob 데이터 처리 =====
    /**
     * Reader
     * 역할: 데이터 소스(예: 데이터베이스, 파일 등)에서 데이터를 한 항목씩 읽어옵니다.
     * 설명: Chunk 기반 Step에서는 ItemReader가 각 청크의 첫 단계로, 데이터를 순차적으로 읽어와 다음 단계로 전달합니다.
     *
     * @return
     */
    @Bean
    public RepositoryItemReader<Cert> expiredCertReader(CertRepository certRepository) {
        RepositoryItemReader<Cert> reader = new RepositoryItemReader<>();
        reader.setRepository(certRepository);
        // Repository 에 정의한 메서드명
        reader.setMethodName("findByEndDateBefore");
        // 메서드 파라미터로 현재 시간을 전달
        reader.setArguments(Collections.singletonList(LocalDate.now()));
        // 정렬 조건(페이지 처리 시 반드시 필요)
        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        reader.setPageSize(10);

        return reader;
    }

    /**
     * Processor
     * 역할: 읽어온 데이터를 비즈니스 로직에 따라 변환하거나 검증합니다.
     * 설명: ItemProcessor는 각 아이템에 대해 처리(예: 데이터 가공, 필터링)를 수행하며, 변환된 결과를 Writer로 전달합니다.
     * 특징: 처리 결과가 null이면 해당 아이템은 Writer로 전달되지 않아 필터링 효과를 가질 수 있습니다.
     * @return
     */
    @Bean
    public ExpiredCertProcessor expiredCertProcessor() {
        return new ExpiredCertProcessor();
    }

    /**
     * Writer
     * 역할: 처리된 데이터를 최종 목적지(예: 데이터베이스, 파일 등)에 기록합니다.
     * 설명: Chunk 단위로 모인 처리된 아이템들을 한 번에 쓰는 역할을 하며, 데이터의 일괄 저장(또는 업데이트)을 수행합니다.
     * @return
     */
    @Bean
    public JpaItemWriter<Cert> expiredCertWriter() {
        JpaItemWriter<Cert> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }

    /**
     * Job
     * 역할: 배치 처리의 전체 단위를 정의합니다.
     * 설명: Job은 하나 이상의 Step으로 구성되며, 실행 순서(순차, 분기 등)와 전체 흐름을 관리합니다.
     * 또한 JobExecutionListener를 통해 작업 시작/종료 시 추가 로직을 수행할 수 있습니다.
     * @param listener
     * @return
     */
    @Bean
    public Job expiredCertJob(JobListener listener) {
        return new JobBuilder("expiredCertJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(expiredCertStep())
                .end()
                .build();
    }

    /**
     * Step
     * 역할: Job의 개별 단계로, 한 가지 특정 작업 단위를 수행합니다.
     * 설명: Step은 보통 두 가지 방식으로 구현됩니다.
     * - Chunk 기반 처리: 데이터를 일정 청크 단위로 읽고, 처리한 후, 결과를 쓰는 과정을 반복합니다.
     * - Tasklet 기반 처리: 단일 작업을 수행하는 간단한 로직을 구현할 때 사용합니다.
     * @return
     */
    @Bean
    public Step expiredCertStep() {
        return new StepBuilder("expiredCertStep", jobRepository)
                .<Cert, Cert>chunk(10, platformTransactionManager)
                .reader(expiredCertReader(certRepository))
                .processor(expiredCertProcessor())
                .writer(expiredCertWriter())
                .build();
    }


}
