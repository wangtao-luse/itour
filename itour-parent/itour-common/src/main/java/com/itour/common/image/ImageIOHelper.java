package com.itour.common.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;


public class ImageIOHelper {
/**
 * Image是一个抽象类，BufferedImage是其实现类，是一个带缓冲区图像类，主要作用是将一幅图片加载到内存中
 *  （BufferedImage生成的图片在内存里有一个图像缓冲区，利用这个缓冲区我们可以很方便地操作这个图片），
 *     提供获得绘图对象、图像缩放、选择图像平滑度等功能，通常用来做图片大小变换、图片变灰、设置透明不透明等。
 * 
 * Graphics
	提供基本绘图和显示格式化文字的方法，画图用的坐标系原点在左上角，纵轴向下。主要有画线段、矩形、圆、椭圆、圆弧、多边形等各种颜色的图形、线条。
	Graphics2D类提供更强大的绘图能力。
 */
	
	

    /**
     * 按照固定宽高原图压缩
     *
     * @param img    源图片文件
     * @param width  宽
     * @param height 高
     * @param out    输出流
     * @throws IOException the io exception
     */
    public static void thumbnail(File img, int width, int height, OutputStream out) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(img);
        Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = tag.getGraphics();
        graphics.setColor(Color.RED);
        // 绘制处理后的图
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        ImageIO.write(tag, "JPEG", out);
    }
    public static void thumbnail(InputStream inputStream, int width, int height, OutputStream out) throws IOException {
    	//1.构建BufferedImage对象(将图片加载到内存中)
    	BufferedImage bufferedImage = ImageIO.read(inputStream);
    	//2.创建此图像的缩放版本
		/*
		 * width:缩放图像的宽度;
		 * height:缩放图像的高度; 
		 * hints: -标志，指示用于图像重采样的算法类型。( SCALE_DEFAULT,SCALE_FAST,SCALE_SMOOTH,SCALE_REPLICATE,SCALE_AREA_AVERAGING)
		 * IllegalArgumentException-如果width 或height为零。
		 */
    	Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    	//3.构建BufferedImage预定义图像类型
    	BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	//3.1获取Graphics2D
    	Graphics graphics = tag.getGraphics();
    	//3.2将此图形上下文的当前颜色设置为指定的颜色
    	graphics.setColor(Color.RED);
    	// 3.3绘制处理后的图
    	//img-要绘制的指定图像。如果img为null，则此方法不执行任何操作。
    	//x,y图像的左上角位于（x，  y）
    	graphics.drawImage(image, 0, 0, null);
    	//3.4处置此图形上下文并释放它正在使用的所有系统资源
    	graphics.dispose();
    	//输出压缩后的图片
    	ImageIO.write(tag, "JPEG", out);
    }
    
    public static OutputStream resize(InputStream is, int resizeWidth) {
		try {
			//1.将图片加载到内存中
			Image src = ImageIO.read(is);
			//1.1获取该图片的高和宽
			int srcHeight = src.getHeight(null);
			int srcWidth = src.getWidth(null);
			int deskHeight = 0;// 缩略图高
			int deskWidth = 0;// 缩略图宽
			int comBase=resizeWidth;
			int scale=1;
			double srcScale = (double) srcHeight / srcWidth;
			/**缩略图宽高算法*/
			if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
				if (srcScale >= scale || 1 / srcScale > scale) {
					if (srcScale >= scale) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				} else {
					if ((double) srcHeight > comBase) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			}
			//构建BufferedImage对象
			BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
			tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
			
			OutputStream os = new ByteArrayOutputStream();  
			ImageIO.write(tag, "jpg", os); 
			return os;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
}
