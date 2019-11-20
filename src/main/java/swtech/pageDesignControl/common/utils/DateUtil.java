package swtech.pageDesignControl.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getNewDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString = df.format(new Date());
        return dateString;
    }

    public static void main(String[] args) {
        System.out.println(getNewDate());
    }
}

