package com.itour.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * 
 *
 */
@Slf4j
public class MinioUtil {
/**
 * minio地址+端口号
 */
private static final String URL = null;
/**
 * minio用户名
 */
private static final String accessKey = null;
/**
 *  minio密码
 */
private static final String secretKey = null;
/**
 *  文件桶的名称
 */
private static final String bucketName = null;
private static MinioClient minioClient;
/**
 * 初始化minio配置
 */
@PostConstruct
public static void init() {
	try {
		log.info("Minio Initialize........................");
		minioClient = MinioClient.builder().endpoint(URL).credentials(accessKey, secretKey).build();
		createBucket(bucketName);
		log.info("Minio Initialize........................successful");
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		log.error(e.getMessage());
	}
	
}
/**
 * 创建 bucket
 * @param bucketName
 */
@SneakyThrows(Exception.class)
public static void createBucket(String bucketName) {
	try {
		boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
		if(!bucketExists) {
           minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
		}
	} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
			| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
			| IllegalArgumentException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * 长传文件
 * @param bucketName
 */
public void upload(String bucketName) {
	try {
		minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).build());
	} catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
			| InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
			| IllegalArgumentException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * 删除文件
 * @param bucketName
 * @param fileName
 */
@SneakyThrows(Exception.class)
public static void deleteFile(String bucketName, String fileName) {
    minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).build());
}
/**
 * 下载文件
 * @param bucketName
 * @param fileName
 * @param response
 */
@SneakyThrows(Exception.class)
public static void download(String bucketName, String fileName, HttpServletResponse response) {
    // 获取对象的元数据
   StatObjectResponse statObject = minioClient.statObject(StatObjectArgs.builder().build());
    response.setContentType(statObject.contentType());
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
    InputStream is = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).build());
    IOUtils.copy(is, response.getOutputStream());
    is.close();
}
/**
 * 获取所有的bucket
 * @return
 */
@SneakyThrows(Exception.class)
public static List<Bucket> getAllBuckets() {
    return minioClient.listBuckets();
}
/**
 * 判断 bucket是否存在
 * @param bucketName
 * @return
 */
@SneakyThrows(Exception.class)
public static boolean bucketExists(String bucketName) {
    return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
}
}
