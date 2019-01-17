package com.tsco.web.service.impl;

import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class FileService {

    /**
     * @param multipartFile 上传文件数据
     * @param toPath        文件保存目录
     */
    public void saveFile(MultipartFile multipartFile, String toPath) {
        log.info("begin save multipart file,fileName is:{}", multipartFile.getOriginalFilename());
        if (multipartFile.isEmpty()) {
            log.warn("multipart file is empty,file name is:{}", multipartFile.getOriginalFilename());
            return;
        }
        try {
            //创建文件存放目录
            File fileDir = new File(toPath + File.separator);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = new File(fileDir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error("save multipart file erro,fileName is:{}", multipartFile.getOriginalFilename(), e);
            throw new WebException(ExceptionCode.INNER_ERROR, "文件写入异常");

        }
    }


}
