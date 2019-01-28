package com.sxy.www.util;

import com.sxy.www.enums.FileEncode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * 文件编码解析工具类
 */
public class CheckFileEncodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(CheckFileEncodeUtils.class);

    /**
     * 传入文件路径,返回文件编码格式
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static FileEncode checkFileEncode(String filePath) throws FileNotFoundException {
        File file  = new File(filePath);
        return chechFileEncode(file);
    }

    /**
     * 传入要解析的文件,返回文件编码格式的枚举类型,之后有新的文件类型可以扩展枚举
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static FileEncode chechFileEncode(File file) throws FileNotFoundException {
        if(!file.exists()){
            throw new FileNotFoundException("the file " + file.toPath() + " not exists");
        }
        String encode = EncodingDetect.getJavaEncode(file);
        FileEncode fileEncode = encodeString2FileEncode(encode);

        return fileEncode;
    }

    public static FileEncode chechFileEncode(MultipartFile file) throws IOException {
        Objects.requireNonNull(file);
        String encode = EncodingDetect.getJavaEncode(file);
        FileEncode fileEncode = encodeString2FileEncode(encode);
        return fileEncode;
    }

    private static FileEncode encodeString2FileEncode(String fileEncodeStr){
        FileEncode fileEncode = FileEncode.OTHER;
        switch (fileEncodeStr){
            case "UTF-16LE":
                fileEncode = FileEncode.UTF16LE;
                break;
            case "UTF-16BE":
                fileEncode = FileEncode.UTF16BE;
                break;
            case "ASCII":
                fileEncode = FileEncode.ASCII;
                break;
            case "UTF-8":
                fileEncode = FileEncode.UTF8;
                break;
            case "GBK":
                fileEncode = FileEncode.GBK;
                break;
            default:
                fileEncode = FileEncode.OTHER;
        }
        return fileEncode;
    }

    /**
     * 传入文件输入流,返回文件编码格式
     * @param inputStream
     * @return
     */
    public static FileEncode getFilecharset(InputStream inputStream) {

        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return FileEncode.OTHER; // 文件编码为 ANSI
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
            logger.error("check file encode error",e);
        }
        logger.info("上传文件的编码为{}",charset);
        FileEncode fileEncode = encodeString2FileEncode(charset);
        return fileEncode;
    }
}
