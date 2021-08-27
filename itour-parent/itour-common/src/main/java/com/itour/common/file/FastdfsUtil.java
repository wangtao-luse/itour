package com.itour.common.file;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

@Component
public class FastdfsUtil {
	public static final String DEFAULT_CHARSET = "UTF-8";
	@Autowired
    private FastFileStorageClient fastFileStorageClient;
	/**
     * 上传文件
     */
    public StorePath upload(MultipartFile file) throws IOException {
        // 设置文件信息
        Set<MetaData> mataData = new HashSet<>();
        mataData.add(new MetaData("author", "fastdfs"));
        mataData.add(new MetaData("description", file.getOriginalFilename()));
        // 上传
        return fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        
    }
    /**
     * 删除
     */
    public void delete(String path) {
        fastFileStorageClient.deleteFile(path);
    }

    /**
     * 删除
     */
    public void delete(String group, String path) {
        fastFileStorageClient.deleteFile(group, path);
    }

    /**
     * 文件下载
     *
     * @param path     文件路径，例如：/group1/M00/00/00/itstyle.png
     * @param filename 下载的文件命名
     */
    public void download(String path, String filename, HttpServletResponse response) throws IOException {
        // 获取文件
        StorePath storePath = StorePath.parseFromUrl(path);
        if (StringUtils.isBlank(filename)) {
            filename = FilenameUtils.getName(storePath.getPath());
        }
        byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
        response.reset();
        response.setContentType("applicatoin/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.close();
    }
    public String filterUrl(String urlAddr) {
    	if(StringUtils.isNotBlank(urlAddr)&&urlAddr.length()>5) {
    		//http:wangtao.club/itour/index
    		
    	}
    	return urlAddr;
    }
	

}
