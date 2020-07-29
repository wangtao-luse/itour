package com.itour.common.image;

import java.io.IOException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

public class GraphicsMagickHelper {
	/**
     * 根据尺寸缩放图片[等比例缩放:参数height为null,按宽度缩放比例缩放;参数width为null,按高度缩放比例缩放]
     *
     * @param imagePath 源图片路径
     * @param newPath   处理后图片路径
     * @param width     缩放后的图片宽度
     * @param height    缩放后的图片高度
     * @param quality   压缩质量（0-100）
     * @throws InterruptedException the interrupted exception
     * @throws IOException          the io exception
     * @throws IM4JavaException     the im 4 java exception
     */
    public static void zoomImage(String imagePath, String newPath, Integer width, Integer height, Double quality) throws InterruptedException, IOException, IM4JavaException {
        IMOperation op = new IMOperation();
        op.addImage(imagePath);
        if (width == null) {
            // 根据高度缩放图片
            op.resize(null, height);
        } else if (height == null) {
            // 根据宽度缩放图片
            op.resize(width);
        } else {
            op.resize(width, height);
        }
        op.quality(quality);
        op.addImage(newPath);
        ConvertCmd convert = new ConvertCmd(true);
        // linux下不要设置此值，不然会报错
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            convert.setSearchPath("C:\\Program Files\\GraphicsMagick-1.3.31-Q16");
        }
        convert.run(op);
    }
}
