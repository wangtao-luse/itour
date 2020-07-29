package com.itour.common.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;
/**
 * 支持：图片缩放，区域裁剪，水印，旋转，保持比例
 * @author wwang
 * 
 */
public class ThumbnailsHelper {

	 /**
     * 压缩图片
     *
     * @param srcImagePath 源图片路径
     * @param desImagePath 目标路径
     * @param scale        压缩率
     * @throws IOException the io exception
     */
    public static void thumbnail(String srcImagePath, String desImagePath, double scale) throws IOException {
        Thumbnails.of(srcImagePath).scale(scale).toFile(desImagePath);
    }


}
