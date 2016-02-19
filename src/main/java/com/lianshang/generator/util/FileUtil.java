package com.lianshang.generator.util;

import com.lianshang.generator.constant.Constant;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by walker on 16/2/16.
 */
public class FileUtil {

    private static Logger LOGGER = Logger.getLogger(FileUtil.class);

    public static final String TEXT_DEFAULT_ENCODING = "UTF-8";

    public enum Mode{
        OPEN,
        CREATE,
        OPEN_OR_CREATE,
    }

    public static File openDirWithParent(String parent, String child, Mode mode ){

        boolean result = true;

        File dir = new File(parent, child);

        switch (mode){
            case OPEN:
                if (!dir.exists()){
                    result = false;
                }
                break;

            case CREATE:
                if (dir.exists()){
                    result = false;
                }
                else {
                    result = dir.mkdirs();
                }
                break;

            case OPEN_OR_CREATE:
                if (!dir.exists()){
                    result = dir.mkdirs();
                }
                break;

            default:
                result = false;
        }

        if (!result){
            return null;
        }

        if (!dir.isDirectory()){
            return null;
        }

        return dir;
    }

    public static boolean makeDir(String path){
        return openDirWithParent(null, path, Mode.CREATE) != null;
    }

    public static boolean isExistedDir(String path){
        return openDirWithParent(null, path, Mode.OPEN) != null;
    }

    public static File openDir(String path, Mode mode){
        return openDirWithParent(null, path, mode);
    }

    public static boolean deleteDir(String dir){
        return deleteDir(new File(dir));
    }

    public static boolean deleteDir(File dir){

        if (dir == null){
            return false;
        }

        if (!dir.exists()){
            return false;
        }

        if (dir.isDirectory()){
            for (File file : dir.listFiles()){
                deleteDir(file);
            }
        }

        return dir.delete();
    }

    public static InputStream openFileInputStream(String path){

        InputStream inputStream = null;

        try{
            inputStream = new FileInputStream(path);
        }
        catch (FileNotFoundException e){
            LOGGER.error("open file input stream fails, path<" + path + ">", e);
        }

        return inputStream;
    }

    public static InputStreamReader openFileInputStreamReader(String path){
        return openFileInputStreamReader(path, TEXT_DEFAULT_ENCODING);
    }

    public static InputStreamReader openFileInputStreamReader(String path, String encoding){
        InputStream inputStream = openFileInputStream(path);

        if (inputStream == null){
            return null;
        }

        return new InputStreamReader(inputStream);
    }

    public static OutputStreamWriter openFileOutputStreamWriter(String path) {

        return openFileOutputStreamWriter(path, TEXT_DEFAULT_ENCODING, false);
    }

    public static OutputStreamWriter openFileOutputStreamWriter(String path, boolean append) {
        return openFileOutputStreamWriter(path, TEXT_DEFAULT_ENCODING, append);
    }

    public static OutputStreamWriter openFileOutputStreamWriter(String path, String encoding, boolean append)
    {
        OutputStreamWriter outputStreamWriter = null;
        try{
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path, append), encoding);
        }
        catch (Exception e){
            LOGGER.error("create output stream writer fails, path<" + path + ">, encoding<" + encoding + ">", e);
        }

        return outputStreamWriter;
    }

    public static List<File> getNormalFileList(String path){

        return getNormalFileList(path, null);
    }

    public static List<File> getNormalFileList(String path, final String suffix){

        List<File> fileList = new ArrayList<File>();

        File file = new File(path);
        if (file != null && file.exists()){

            if (file.isDirectory()){

                File[] fileArray = file.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file) {

                        if (!file.isFile()){
                            return false;
                        }

                        if (suffix != null && !file.getName().endsWith(suffix)){
                            return false;
                        }

                        return true;
                    }
                });

                fileList = Arrays.asList(fileArray);
            }
            else{

                fileList.add(file);
            }
        }

        return fileList;
    }

    public static boolean copyFile(String sourceFilePath, String targetFilePath)
    {
        boolean result = false;

        BufferedReader reader = null;
        PrintWriter writer = null;
        try
        {
            reader = new BufferedReader(openFileInputStreamReader(sourceFilePath));
            writer = new PrintWriter(new BufferedWriter(openFileOutputStreamWriter(targetFilePath, false)));

            String line;
            while ((line = reader.readLine()) != null)
            {
                writer.println(line);
            }

            result = true;
        }
        catch (Exception e){
            LOGGER.error("write file fails, sourceFilePath<" + sourceFilePath + ">, targetFilePath<" + targetFilePath + ">", e);
        }

        if (!close(reader)){
            result = false;
        }

        if (!close(writer)){
            result = false;
        }

        return result;
    }

    public static boolean close(Closeable closeable){
        try{
            if (closeable != null){
                closeable.close();
            }
        }
        catch (IOException e){
            LOGGER.error("clone fails", e);
            return false;
        }

        return true;
    }

    public static boolean writeFile(String path, String content) {

        OutputStreamWriter writer = null;

        boolean result = true;

        try {
            writer = FileUtil.openFileOutputStreamWriter(path);

            writer.write(content);
            writer.flush();
        }
        catch (IOException e) {
            result = false;
            LOGGER.error(e);
        }
        finally {
            FileUtil.close(writer);
        }

        return result;
    }

    public static String loadFromFile(String path) {

        BufferedReader reader = null;
        String content = "";
        try
        {
            reader = new BufferedReader(openFileInputStreamReader(path));

            String line;
            while ((line = reader.readLine()) != null)
            {
                content += line + Constant.RETURN;
            }
        }
        catch (Exception e){
            LOGGER.error("load fails from file<" + path + ">", e);
            content = null;
        }

        close(reader);

        return content;
    }
}
