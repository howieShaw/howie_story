package com.howie.story.webUtil;

import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xiaohaoyun
 * @Description：
 * @Date：created in 下午4:43 2018/10/22
 * @Modified by:xiaohaoyun
 */
public class SPLUtils {
    private SPLUtils() {}

    private static List<String> numbers = new ArrayList<>();


    private static List<String> regex = new ArrayList<>();


    public static void main(String[] args) {
        regex = string2Unicode("0123456789-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz",16);
        String filePath = "/Users/xiaohaoyun/Desktop/FP00007.SPL";
        readSplFile(filePath);

        if (CollectionUtils.isNotEmpty(numbers)) {
            for (String pr : numbers) {
                System.out.print(pr+" ");
            }
        }

    }

    public static void readSplFile (String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在："+filePath);
            return;
        }

        try {
            byte[] files = Files.readAllBytes(Paths.get(filePath));

            for (int i=0;i<files.length;i++) {
                byte[] br = {files[i]};
                String brstr = bytesToHexString(br);
                if (regex.contains(brstr)) {
                    String pr = new String(br);
                    numbers.add(pr);
                    if (i == files.length -2) {
                        break;
                    }
                    byte[]f = {files[i+2],files[i+1]};
                    System.out.println(new String(f));

                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String> string2Unicode (String str, int unicode) {
        List<String> numbers = new ArrayList<>();
        for (int i =0 ; i < str.length(); i++) {
            char c = str.charAt(i);
            System.out.println(c);

            switch (unicode) {
                case 2 : System.out.println(Integer.toBinaryString(c));
                break;
                case 16 :
                    numbers.add(Integer.toHexString(c));
                break;
                case 8 : System.out.println(Integer.toOctalString(c));
                default:
                    System.out.println("~~");
            }
        }

        return numbers;
    }

    public static String toStringHex1(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void readFile (String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在："+filePath);
            return;
        }

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            byte[] read = new byte[2];
            int index =0;
            for (int i =4;i< bytes.length;i++) {
                if (index==2) {
                    byte[] pr = {read[0],read[1]};
                    System.out.print(new String(pr));
                    read = null;
                    read = new byte[2];
                    index = 0;
                }
                read[index] = bytes[i];
                index++;


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long dwordBytesToLong(short[] data) {
        return (data[3] << 8 * 3) | (data[2] << 8 * 2)  | (data[1] << 8)
                | data[0];
    }


    public static short[] longToDword(long value) {
        short[] data = new short[4];

        long mask = 0xff;

        for (int i = 0; i < data.length; i++) {
            data[i] = (short) ((value & mask) >> (8 * i));
            mask = mask << 8;
        }
        return data;
    }

}
