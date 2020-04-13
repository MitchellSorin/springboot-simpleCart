package com.sorin.simplecart.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;

import java.io.File;

/**
 * @author LSD
 * @date 2020/04/09
 **/
@Configuration
public class FileWriterIntegrationConfig {

    @Bean
    @Transformer(inputChannel = "textInChannel", outputChannel = "textOutChannel")
    public GenericTransformer<String, String> upperCaseTransformer() {
        return String::toUpperCase;
    }

    @Bean
    @ServiceActivator(inputChannel = "textOutChannel")
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/temp"));
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        handler.setRequiresReply(false);
        return handler;
    }

    //-------------------DSL--------------------//
//    @Bean
//    public IntegrationFlow fileWriterFlow() {
//        return IntegrationFlows
//                .from(MessageChannels.direct("textInChannel"))
//                .<String, String>transform(String::toUpperCase)
//                .handle(Files
//                        .outboundAdapter(new File("/temp"))
//                        .fileExistsMode(FileExistsMode.APPEND)
//                        .appendNewLine(true))
//                .get();
//    }

}
