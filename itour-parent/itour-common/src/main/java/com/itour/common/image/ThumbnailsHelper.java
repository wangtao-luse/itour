package com.itour.common.image;


import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
/**
 * 支持：图片缩放，区域裁剪，水印，旋转，保持比例
 * @author wwang
 * 
 */
public class ThumbnailsHelper {
public static final double SCALE_DEFUALT = 0.9;
//图片裁剪
public static void thumCut(File file, String outFile, int x ,int y,int width,int height) throws IOException {
     Thumbnails.of(file)
    		   .sourceRegion(x, y, width, height)
    		   .toFile(outFile);
    		  
}
 /**
     * 压缩图片
     *
     * @param srcImagePath 源图片路径
     * @param desImagePath 目标路径
     * @param scale        压缩率
     * @throws IOException the io exception
     */
    public static void thumbnail(String srcImagePath, String desImagePath, double scale) throws IOException {
        Thumbnails.of(srcImagePath)
        		  .scale(scale)
        		  .toFile(desImagePath);
    }
}
