package com.itour.common.image;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCVHelper {
public static void main(String[] args) {
	String filename = null;
	int scale = 0;
	String desImagePath = null;
	thumbnail(filename, scale, desImagePath);

}
/**
  * 压缩图片
 * @param filename 文件路径
 * @param scale    压缩率
 * @param desImagePath 目标路径
 */
public static void thumbnail(String filename, int scale, String desImagePath) {
	Mat src = Imgcodecs.imread(filename);
	Mat dst = src.clone();
	Imgproc.resize(src, dst, new Size(src.width() * scale, src.height() * scale));
	Imgcodecs.imwrite(desImagePath, dst);
}
}
