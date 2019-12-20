package job.utils;

import java.util.Random;

public class RandomUtil {
    public static String getRandom(int number) {
        String str = "";
        if(number<=0){
            return str;
        }else {
            Random random = new Random();
            for (int i = 0; i < number; i++) {
                str += random.nextInt(10);
            }
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(getRandom(6));
    }
}

