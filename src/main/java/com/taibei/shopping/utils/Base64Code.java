package com.taibei.shopping.utils;
import java.util.Base64;

public class Base64Code {

    public static void main(String[] args) throws Exception {

         // root----base64--->cm9vdA==
         // lejia----base64-------->bGVqaWE=
         //lejia123----base64-------->bGVqaWExMjM=

          String text = "lejia";


         Base64.Decoder decoder = Base64.getDecoder();
         Base64.Encoder encoder = Base64.getEncoder();
         byte[] textByte = text.getBytes("UTF-8");

        //编码
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(text+"----base64-------->"+encodedText);
        //解码
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
    }
}
