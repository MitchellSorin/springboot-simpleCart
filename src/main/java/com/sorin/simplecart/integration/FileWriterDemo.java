package com.sorin.simplecart.integration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LSD
 * @date 2020/04/12
 **/
@RestController
@RequestMapping("/integration/file")
@Api(tags = "写入文件", description = "spring integration demo")
public class FileWriterDemo {

    @Autowired
    private FileWriterGateway fileWriter;

    @GetMapping
    @ApiOperation(value = "写入")
    public String main(
            @RequestParam("fileName") String fileName,
            @RequestParam("data") String data
    ) {
        fileWriter.writeToFile(fileName, data);
        return data;
    }
}
