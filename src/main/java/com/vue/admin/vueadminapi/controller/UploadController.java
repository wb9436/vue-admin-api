package com.vue.admin.vueadminapi.controller;

import com.vue.admin.vueadminapi.config.ApplicationConfig;
import com.vue.admin.vueadminapi.utils.FileUploadUtils;
import com.vue.admin.vueadminapi.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: WB
 * @Date: 2018/6/22 16:46
 * @Description:
 */
@RestController
public class UploadController {

    @PostMapping("/upload")
    Result upload(MultipartFile file, String urlPrefix) {
        try {
            String avatar = FileUploadUtils.uploadImage(ApplicationConfig.getUploadPath(), null, urlPrefix,
                    file);
            return Result.ok()
                    .put("resPath", avatar)
                    .put("visitPath", ApplicationConfig.getUploadVisit() + avatar);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("文件上传失败");
        }
    }


}
