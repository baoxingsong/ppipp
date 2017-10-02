
package me.songbx.action;

import java.io.*;
import me.songbx.util.constants.GlobalConstant;


public class InterlogResultDownloadAction extends Constant
{
    public void setInputPath(String value)
    {
        inputPath = value;
    }
    public InputStream getTargetFile()
        throws Exception
    {
        return new FileInputStream((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(inputPath).append(File.separator).append("interlog.zip").toString());
    }
    private String inputPath;
}
