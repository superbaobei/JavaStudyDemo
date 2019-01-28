package com.sxy.www;

import com.sxy.www.enums.FileEncode;
import com.sxy.www.util.CheckFileEncodeUtils;
import com.sxy.www.util.EncodingDetect;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangyusun on 2019/1/22.
 */
public class JVMStudy {

    @Test
    public void testMultipartFile() throws IOException {
        String path = "/Users/xiangyusun/Downloads/test.txt";
        File file = new File(path);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileEncode fileEncode = CheckFileEncodeUtils.getFilecharset(in);

        System.out.println("fileEncode = " + fileEncode);

        System.out.println("------------------");
        String id = "中文2";
        String content = new String(id.getBytes(),"ASCII");
        MultipartFile multipartFile = new MockMultipartFile("test.txt",content.getBytes());
        fileEncode =CheckFileEncodeUtils.chechFileEncode(multipartFile);
        System.out.println("fileEncode = " + fileEncode);
    }

    @Test
    public void testJvm(){

        List<OOMObject> list=new ArrayList<OOMObject>();

        while(true){

            list.add(new OOMObject());

        }
    }

    @Test
    public void testFileEncode(){

        String filePath = "/Users/xiangyusun/Downloads/test.txt";

        System.out.println("fileEncode = " + checkFileEncode(filePath));
        System.out.println("fileContent = " + readFile(filePath,checkFileEncode(filePath)));

        filePath = "/Users/xiangyusun/Downloads/工作簿1.txt";
        System.out.println("fileEncode = " + checkFileEncode(filePath));
        System.out.println("fileContent = " + readFile(filePath,checkFileEncode(filePath)));

        filePath = "/Users/xiangyusun/Desktop/file.sh";
        System.out.println("fileEncode = " + checkFileEncode(filePath));
        System.out.println("fileContent = " + readFile(filePath,checkFileEncode(filePath)));
    }

    private String checkFileEncode(String filePath){
        String fileEncode= EncodingDetect.getJavaEncode(filePath);
        return fileEncode;
    }

    private String readFile(String filePath,String fileEncode){
        String fileContent = null;
        try {
            fileContent= FileUtils.readFileToString(new File(filePath),fileEncode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    @Test
    public void testFileCharset() throws UnsupportedEncodingException {
        File  file = new File("/Users/xiangyusun/Downloads/33444.txt");
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String id = "123412";
        String content = new String(id.getBytes(),"UTF-8");
        MultipartFile multipartFile = new MockMultipartFile("test.txt",content.getBytes());
        try {
            CheckFileEncodeUtils.chechFileEncode(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("---------------");
        try {
            System.out.println(getFilecharset(multipartFile.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilecharset(InputStream inputStream) {

        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset; // 文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF
                    && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; // 文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; // 文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; // 文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }

    @Test
    public void testEnunm(){
        System.out.println(FileEncode.OTHER);
    }
}
