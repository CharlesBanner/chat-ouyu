///**
// * BBD Service Inc
// * All Rights Reserved @2018
// */
//package com.charles.facade.impl.config;
//
//import com.bbd.fastdfsclient.SimpleFastdfsClient;
//import com.bbd.fastdfsclient.core.FastdfsExecutor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// *
// * @author huangr
// * @version $Id: FastDFSConfig.java, v0.1 2018/12/1 17:45 huangr Exp $$
// */
//@Configuration
//public class FastDFSConfig {
//    @Bean
//    public FastdfsExecutor fastdfsExecutor() {
//        FastdfsExecutor executor = new FastdfsExecutor();
//        return executor;
//    }
//
//    @Bean
//    public SimpleFastdfsClient simpleFastdfsClient(FastdfsExecutor fastdfsExecutor, @Value("${fastdfs.tracker.host}") String trackerServerAddr) {
//        return new SimpleFastdfsClient(fastdfsExecutor, trackerServerAddr);
//    }
//
//}
