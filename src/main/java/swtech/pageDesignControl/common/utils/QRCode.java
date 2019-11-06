package swtech.pageDesignControl.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具类
 * yjx
 */
public class QRCode {
  public static void main(String[] args) throws WriterException, IOException {
    // generateQRCode("https://bing.com");
    generateQRCode();
  }


  public static void generateQRCode(){
    //生成一个二维码

    //定义一个json格式的字符串，使用fastJson
    //创建jsonObject 对象
    JSONObject jsonObject=new JSONObject();
    //给jsonObject存放数据
    jsonObject.put("company","http://www.bjpowernode.com");
    jsonObject.put("author","yjx");
    //json对象转json字符串
    String s = jsonObject.toString();
    System.out.println(s);
    //二维码宽高
    int widch = 200 ;
    int hight = 200;
    //创建map集合
    Map<EncodeHintType,Object> hints= new HashMap<EncodeHintType, Object>();
    hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
    //创建一个矩阵对象
    BitMatrix bitMatrix = null;
    try {
      bitMatrix = new MultiFormatWriter().encode(s, BarcodeFormat.QR_CODE,widch,hight,hints);
      //生成的路径根目录
      String filePath = "F://Temp/Qrcode";
      String fileName = "QRcode.jpg";
      //创建一个路径对象
      Path path = FileSystems.getDefault().getPath(filePath,fileName);
      //将矩阵对象转为图片
      MatrixToImageWriter.writeToPath(bitMatrix,"jpg",path);
      System.out.println("二维码生成成功");
    } catch (WriterException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void generateQRCode(String content, OutputStream outputStream) throws WriterException, IOException {
    // String content = "https://bing.com";
    // 二维码的宽高
    int w = 200, h = w;

    // 创建map集合
    Map<EncodeHintType, Object> hints = new HashMap<>();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

    // 创建一个矩阵对象
    BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, w, h, hints);
    String fileFormat = "jpg";
    
    // // 生成的路径
    // String filePath = "d:/";
    // String fileFormat = "jpg";
    // String fileName = "testQRC" + "." +fileFormat;
    // // 路径对象
    // Path path = FileSystems.getDefault().getPath(filePath, fileName);
    //
    // // 将矩阵对象生成图片
    // MatrixToImageWriter.writeToPath(bitMatrix, fileFormat, path);
    MatrixToImageWriter.writeToStream(bitMatrix, fileFormat, outputStream);
  }
}