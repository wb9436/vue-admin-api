package com.vue.admin.vueadminapi.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtils {
    protected final Logger logger = Logger.getLogger(this.getClass());

    /**
     * 文件上传
     *
     * @param uploadPath    上传地址
     * @param multipartFile
     * @param filrUrl
     */
    public static void doFileUpload(String uploadPath, MultipartFile multipartFile, String filrUrl) {
        try {
            File destFile = new File(uploadPath, filrUrl);
            if (!destFile.getParentFile().exists()) { // 判断父目录是否存在,如果父目录不存在，则创建目录
                destFile.getParentFile().mkdirs();// mkdir() 不能创建多层目录
            }
            multipartFile.transferTo(destFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件copy
     *
     * @param uploadPath 上传地址
     * @param file
     * @param filrUrl
     */
    public static void doFileCopy(String uploadPath, File file, String filrUrl) {
        try {
            File destFile = new File(uploadPath, filrUrl);
            if (!destFile.isDirectory()) {
                destFile.createNewFile();
            }
            FileUtils.copyFile(file, destFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param uploadPath 上传地址
     * @param oldFileUrl
     */
    public static void doDeleteOldFile(String uploadPath, String oldFileUrl) {
        try {
            if (!StringUtils.isEmpty(oldFileUrl)) {
                File file = new File(uploadPath, oldFileUrl);
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片
     *
     * @param uploadPath 上传地址
     * @param oldFileUrl 旧图片地址
     * @param urlPrefix  上传目录前缀
     * @param imageFile
     * @return
     */
    public static String uploadImage(String uploadPath, String oldFileUrl, String urlPrefix, MultipartFile imageFile) {
        if (imageFile == null) {
            return null;
        }
        try {
            String fileName = imageFile.getOriginalFilename();
            if (!StringUtils.isEmpty(fileName)) {
                String url = urlPrefix + "/" + RandStrUtils.randNumeric0(6) + "_" + RandStrUtils.randNumeric(6)
                        + fileName.substring(fileName.indexOf("."));
                doFileUpload(uploadPath, imageFile, url);
                doDeleteOldFile(uploadPath, oldFileUrl);
                return url;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
