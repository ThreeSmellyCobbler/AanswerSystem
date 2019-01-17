package com.tsco.web.controller.upload;


import com.tsco.web.domain.Response;
import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.service.impl.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "数据上传", tags = "upload")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private FileService fileService;

    private static final String FILE_SAVE_PATH = "/data/answerSystem/files";

    private static final String USER_IMAGE_PATH = "/data/answerSystem/images/user";


    @ApiOperation(value = "文件上传", notes = "fileUpload")
    @ApiImplicitParam(name = "file", value = "", type = "MultipartFile")
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public Response fileUploadHandle(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "上传文件不能为空");
        }
        fileService.saveFile(file, FILE_SAVE_PATH);
        return Response.SUCCESS();
    }

    @ApiOperation(value = "上传图片", notes = "ImageUpload")
    @ApiImplicitParam(name = "image", value = "", type = "MultipartFile")
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Response imageUploadHandle(@RequestParam("image") MultipartFile image) {
        if (image.isEmpty()) {
            throw new WebException(ExceptionCode.INVALID_PARAMETER, "上传图片不能为空");
        }
        fileService.saveFile(image, USER_IMAGE_PATH);
        return Response.SUCCESS();

    }


}
