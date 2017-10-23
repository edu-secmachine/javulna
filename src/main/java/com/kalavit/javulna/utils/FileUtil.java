/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author peti
 */
public class FileUtil {
    
    private static final String FILE_BASE = "/home/javu/files/";
    
    public static void saveFile(InputStream is, String fileName) throws Exception{
        File outFile = new File(FILE_BASE + fileName);
        FileUtils.copyInputStreamToFile(is, outFile);
    } 
    
}
