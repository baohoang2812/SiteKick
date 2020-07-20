/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import prx.constant.CommonConstant;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class FileUtils {

    public static String read(String path) throws IOException {
        return String.join(CommonConstant.LINE_FEED, Files.readAllLines(new File(path).toPath()));
    }
}
