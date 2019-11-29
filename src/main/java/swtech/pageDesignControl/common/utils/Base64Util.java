package swtech.pageDesignControl.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * @Description
 * @Author zzl
 * @Date 2019-04-19 17:19
 * @Version 1.0
 */
@Slf4j
public class Base64Util {

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    private static final String salt = "hotel";
    private static final int[] indexArr = {35, 39, 26, 12, 9};
    private static final int COUNT = 5;

    public static String encodeUserId(String userId){
        if (StringUtils.isBlank(userId)){
            return null;
        }
        return encode(userId, COUNT);
    }

    public static String decodeUserId(String userId){
        if (StringUtils.isBlank(userId)){
            return null;
        }

        try {
            return decode(userId, COUNT);
        } catch (Exception e) {
            log.error("base64 decode failure...", e.getCause());
        }
        return null;
    }

    public static String decode(byte[] data){
        return new String(decoder.decode(data));
    }

    public static String encode(byte[] data){
        return new String(encoder.encode(data));
    }

    public static String decode(String data){
        return new String(decoder.decode(data.getBytes()));
    }

    public static String encode(String data){
        return new String(encoder.encode(data.getBytes()));
    }

    public static String encode(String data, int count){
        String encodeStr = doEncode(data.getBytes(), count);
        return addSalt(encodeStr);
    }

    public static String decode(byte[] data, int count){
        String decodeStr = delSalt(new String(data));
        String src = doDecode(decodeStr.getBytes(), count);
        return src;
    }

    public static String decode(String data, int count){
        return decode(data.getBytes(), count);
    }

    private static String addSalt(String src){
        StringBuilder saltSb = new StringBuilder(salt);
        StringBuilder srcSb = new StringBuilder(src);
        for (int i = 0; i < indexArr.length; i++){
            srcSb.insert(indexArr[i], saltSb.charAt(i));
        }
        return srcSb.toString();
    }

    private static String delSalt(String src){
        StringBuilder srcSb = new StringBuilder(src);
        for (int i = indexArr.length - 1; i >= 0; i--){
            srcSb.deleteCharAt(indexArr[i]);
        }
        return srcSb.toString();
    }

    private static String doDecode(byte[] data, int count) {
        for (int i = 0; i < count; i++){
            data = decoder.decode(data);
        }
        return new String(data);
    }

    private static String doEncode(byte[] data, int count) {
        for (int i = 0; i < count; i++){
            data = encoder.encode(data);
        }
        return new String(data);
    }

    public static void main(String[] args) {
        String str = "13711216312";
//        StringBuilder sb = new StringBuilder(str);
//        sb
//                .insert(9, 9)
//                .insert(5, 5)
//                .insert(1, 1);
//
//        System.out.println(Base64Util.encode(sb.toString()));

        System.out.println(Base64Util.encodeUserId(str));

//        String result = Base64Util.decodeUserId("VmtaYVUxUlnJNeSGROVmxaVFYwZDtRUMVpyV25hOT2oJGSlhWV3R3VVZWVU1Eaz0=");
//        System.out.println(result);

        // MTUzNzExNTIxNjM1MTI=
//        String str1 = Base64Util.encode(str, 5);
//
//        System.out.println(str1);
//        System.out.println(Base64Util.decode(str1, 5));
    }

}
