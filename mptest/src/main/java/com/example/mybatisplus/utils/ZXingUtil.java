package com.example.mybatisplus.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ZXingUtil
 * @Description TODO
 * @Author KingXunlian
 * @Date 2019/12/5 16:07
 * @Version 1.0
 */
public class ZXingUtil {

    /**
     * @return java.lang.String
     * @Author lilinsong
     * @Description
     * @Date 16:07 2019/12/5
     * @Param [imgData]
     **/
    public static String readCode(byte[] imgData) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(imgData);
            BufferedImage image = ImageIO.read(in);
            if (image == null) {
                return null;
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "GBK");
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

            Result result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @return void
     * @Author lilinsong
     * @Description
     * @Param [msg, path]
     * @Date 2019/12/5 16:18
     **/
    public static void getBarCode(String msg, String path) {
        try {
            File file = new File(path);
            OutputStream ous = new FileOutputStream(file);
            if (StringUtils.isEmpty(msg) || ous == null)
                return;
            //选择条形码类型(好多类型可供选择)
            Code128Bean bean = new Code128Bean();
            //设置长宽
            final double moduleWidth = 0.20;
            final int resolution = 150;
            bean.setModuleWidth(moduleWidth);
            bean.doQuietZone(false);
            String format = "image/png";
            // 输出流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format,
                    resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            //生成条码
            bean.generateBarcode(canvas, msg);
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @return byte[]
     * @Author lilinsong
     * @Description 图片转byte数组
     * @Date 16:11 2019/12/5
     * @Param [path]
     **/
    public static byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

}
