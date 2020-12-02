package com.ryan.springbootdemo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 类描述：文件操作工具类
 *
 * @author guankai
 * @date 2020/10/27
 **/
public class FileUtil {

    private static final String POINT = ".";

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @author guankai
     *@date 2020/10/27
     * @return
     */
    public static boolean isExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 判断文件夹是否存在
     *
     * @param path 文件夹路径
     * @author guankai
     * @date 2020/10/27
     * @return
     */
    public static boolean isDir(String path) {
        File file = new File(path);
        if(file.exists()){
            return file.isDirectory();
        }else{
            return false;
        }
    }

    /**
     * 读取txt文件内容
     *
     * @param filePath 文件路径
     * @author guankai
     * @date 2020/10/27
     * @return
     */
    public static String readText(String filePath) {
        String lines = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 将数据写入txt文件
     *
     * @param filePath 文件路径
     * @param content 内容
     * @param isAppend 是否追加写入（true：在文件原有内容追加写入；false：清空原有文件内容再写入）
     * @author guankai
     * @date 2020/10/27
     * @return
     */
    public static void writeText(String filePath, String content,boolean isAppend) {
        try(
                FileOutputStream outputStream = new FileOutputStream(filePath,isAppend);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)
            ) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果目录不存在，就创建文件
     *
     * @param dirPath 文件夹路径
     * @author guankai
     * @date 2020/10/27
     * @return
     */
    public static String mkdirs(String dirPath) {
        try{
            File file = new File(dirPath);
            if(!file.exists()){
                file.mkdirs();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dirPath;
    }

    /**
     * 复制文件
     *
     * @param sourceFile 源文件路径
     * @param targetFile 目标文件路径
     * @author guankai
     * @date 2020/10/27
     * @return
     */
    public static void copy(String sourceFile, String targetFile) {
        File source = new File(sourceFile);
        File target = new File(targetFile);
        target.getParentFile().mkdirs();
        try(
                FileInputStream fis = new FileInputStream(source);
                FileOutputStream fos = new FileOutputStream(target);
                //得到对应的文件通道
                FileChannel in = fis.getChannel();
                //得到对应的文件通道
                FileChannel out = fos.getChannel()
            ) {
            //连接两个通道，并且从in通道读取，然后写入out通道
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  创建临时文件，使用缓冲区将 MultipartFile 类型转换成 File 类型
     *
     * @param multipartFile
     * @author guankai
     * @date 2020/10/27
     * @return
     **/
    private File transferToFile(MultipartFile multipartFile) {
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file= File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            //程序结束后删除临时文件
//            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


}
