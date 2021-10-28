package dev.gustavoteixeira.filetransformer.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * Class used to configure the batch job related beans.
 *
 * @author Chris Schaefer
 * @author David Turanski
 */

//@Slf4j
//@Configuration(proxyBeanMethods = false)
//public class BatchConfiguration {
//
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job1() {
//        return this.jobBuilderFactory.get("job1")
//                .start(this.stepBuilderFactory.get("job1step1")
//                        .tasklet(new Tasklet() {
//                            @Override
//                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                                log.info("Job1 was run");
//                                return RepeatStatus.FINISHED;
//                            }
//                        })
//                        .build())
//                .build();
//    }
//
//    @Bean
//    public Job job2() {
//        return this.jobBuilderFactory.get("job2")
//                .start(this.stepBuilderFactory.get("job2step1")
//                        .tasklet(new Tasklet() {
//                            @Override
//                            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                                log.info("Job2 was run");
//                                return RepeatStatus.FINISHED;
//                            }
//                        })
//                        .build())
//                .build();
//    }
//}


@Configuration
@Slf4j
@EnableBatchProcessing
public class BatchConfiguration {
    //
//    @Autowired
//    private ResourceLoader resourceLoader;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

//    @Bean
//    @StepScope
//    public ItemStreamReader<Person> reader(@Value("#{jobParameters['bucketName']}") String bucketName,
//                                           @Value("#{jobParameters['objectName']}") String objectName) {
//        log.info("THE LOG I WANT TO SEE");
//        log.info("bucketName: {}", bucketName);
//        log.info("objectName: {}", objectName);
//
//        if (!objectName.matches("[a-z]+:.*")) {
//            objectName = "file:" + objectName;
//        }
//        log.info("BEFORE THING THAT IS GOING TO BREAK FOR SURE");
//        return new FlatFileItemReaderBuilder<Person>()
//                .name("reader")
//                .resource(resourceLoader.getResource(objectName))
//                .delimited()
//                .names("firstName", "lastName")
//                .fieldSetMapper(new PersonFieldSetMapper())
//                .build();
//    }
//
//    @Bean
//    public ItemProcessor<Person, Person> processor() {
//        return new PersonItemProcessor();
//    }
//
//    @Bean
//    public ItemWriter<Person> writer() {
//        return new CustomItemWriter();
//    }
//
//    @Bean
//    public Job ingestJob() {
//        return jobBuilderFactory.get("ingestJob")
//                .incrementer(new RunIdIncrementer())
//                .start(step1())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("ingest")
//                .<Person, Person>chunk(10)
//                .reader(reader(null, null))
//                .processor(processor())
//                .writer(writer())
//                .build();
//    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((StepContribution contribution, ChunkContext chunkContext) -> RepeatStatus.FINISHED)
                .build();
    }

    @Bean
    public Job job(Step step1) throws Exception {
        log.info("WUBBA LUBBA DUB DUB");
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

}

