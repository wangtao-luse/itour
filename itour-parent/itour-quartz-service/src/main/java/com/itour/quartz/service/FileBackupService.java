package com.itour.quartz.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itour.util.FtpFileUtil;
@Service
public class FileBackupService {
	@Value("${fileupload.path}")
    private String uploadFileLocation;
public void backupFile() {
	
	
}
public boolean saveFile() {
	try {
		File f = new File(uploadFileLocation);
		File[] listFiles = f.listFiles();
		for (File file : listFiles) {
			boolean isFile = file.isFile();
			if (isFile) {
				FileInputStream fis = new FileInputStream(file);
				//FtpFileUtil.uploadFile(uploadFileLocation, file.getName(), fis);
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	return true;

}
}
