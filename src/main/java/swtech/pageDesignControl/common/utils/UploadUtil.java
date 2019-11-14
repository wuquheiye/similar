package swtech.pageDesignControl.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadUtil {
    public static List<Object> upload(HttpServletRequest request) throws ServletException, IOException {
        DiskFileItemFactory diskFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(diskFactory);
        List<FileItem> fileItems = null;
        List<Object> list=new ArrayList<>();
        upload.setHeaderEncoding("UTF-8");
        try {
            fileItems = upload.parseRequest(request);
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        }
        Iterator<FileItem> iter = fileItems.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            if (item.isFormField()) {
                String name = item.getFieldName(); // 获取name属性的值
                String value = item.getString("UTF-8"); // 获取value属性的值
                String decodeStr = URLDecoder.decode(value, "utf-8");
                list.add(decodeStr);
            } else {
                // 处理文件
                String fieldName = item.getFieldName(); // 文件域中name属性的值
                String fileName = item.getName(); // 文件的全路径，绝对路径名加文件名
                String contentType = item.getContentType(); // 文件的类型
                long size = item.getSize(); // 文件的大小，以字节为单位
                if(size!=0) {
                    String rowpath = request.getServletContext().getRealPath("/");
                    // 获取当前时间
                    String xdPath = "images/";
                    String path = rowpath + xdPath;
                    File pathDir = new File(path); // 定义一个file指向一个具体的文件
                    if (!pathDir.exists()) {
                        pathDir.mkdirs();
                    }
                    File storeFile = new File(path+fileName);
                    list.add(path+fileName);
                    try {
                        // 保存文件到硬盘
                        item.write(storeFile);// 把上传的内容写到一个文件中
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //删除缓存文件
                item.delete();
            }
        }
        return list;
    }
}
