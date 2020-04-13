package com.sorin.simplecart.integration;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 文件操作
 *
 * @author LSD
 * @date 2020/04/09
 **/
@Component
@MessagingGateway(defaultRequestChannel = "textInChannel", defaultReplyChannel = "textOutChannel")
public interface FileWriterGateway {

    Object writeToFile(
            @Header(FileHeaders.FILENAME) String fileName,
            String data
    );
}
