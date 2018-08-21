package com.howie.story.biz.util;

import com.recognition.software.jdeskew.ImageDeskew;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @Author:xiaohaoyun
 * @Description： 图像文字识别
 * @Date：created in 上午10:38 2018/8/14
 * @Modified by:xiaohaoyun
 */
public class PicDiscernUtil {

    static final double MINIMUM_DESKEW_THRESHOLD = 0.05d;
    private static final String LANGUAGE= "chi_sim";


    public static void main(String[] args) {
        //图片文件
        File imageFile = new File("/Users/xiaohaoyun/Desktop/123.png");

        try {
            if (!imageFile.exists()) {
                System.out.println("文件不存在");
                return;
            }
            BufferedImage image = ImageIO.read(imageFile);
            //对图片进行处理
            image = flipImage(image);
            image = convertImage(image);

            ITesseract instance = new Tesseract();  // JNA Interface Mapping
            String dataPath = System.getProperty("user.dir");
            File file = new File(dataPath+"/tessdata");
            if (!file.exists()) {
                System.out.println("不存在");
                return;
            }
            instance.setDatapath(dataPath);//设置训练库的位置
            instance.setLanguage(LANGUAGE);
            String result = instance.doOCR(image);
            System.out.println("result : "+result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static BufferedImage flipImage(BufferedImage image) throws Exception {
        //按指定宽高创建一个图像副本
        image = ImageHelper.getSubImage(image, 0, 0, image.getWidth(), image.getHeight());
        ImageDeskew id = new ImageDeskew(image);
        double imageSkewAngle = id.getSkewAngle(); //获取倾斜角度
        if ((imageSkewAngle > MINIMUM_DESKEW_THRESHOLD || imageSkewAngle < -(MINIMUM_DESKEW_THRESHOLD))) {
            image = ImageHelper.rotateImage(image, -imageSkewAngle); //纠偏图像
        }
        return image;
    }

    //对图片进行处理 - 提高识别度
    public static BufferedImage convertImage(BufferedImage image) throws Exception {
        //按指定宽高创建一个图像副本
        image = ImageHelper.getSubImage(image, 0, 0, image.getWidth(), image.getHeight());
        //图像转换成灰度的简单方法 - 黑白处理
        image = ImageHelper.convertImageToGrayscale(image);
        //图像缩放 - 放大n倍图像
        image = ImageHelper.getScaledInstance(image, image.getWidth() * 3,   image.getHeight() * 3);
        return image;
    }
}
