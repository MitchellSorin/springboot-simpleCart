package com.sorin.simplecart.integeration;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 文件操作
 *
 * @author LSD
 * @date 2020/04/09
 **/
@MessagingGateway(defaultRequestChannel = "textInChannel")
public interface FileWriterGateway {

    void writeToFile(
            @Header(FileHeaders.FILENAME) String fileName,
            String data
    );
}
