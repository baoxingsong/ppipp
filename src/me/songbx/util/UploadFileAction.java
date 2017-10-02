// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UploadFileAction.java

package me.songbx.util;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import me.songbx.util.constants.GlobalConstant;
import org.apache.struts2.ServletActionContext;

public class UploadFileAction extends ActionSupport
{

    public UploadFileAction()
    {
    }

    public String execute()
        throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        setSavePath((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(savePath).append(File.separator).toString());
        File file = new File(savePath);
        if(!file.exists())
            file.mkdir();
        getFileInput().renameTo(new File((new StringBuilder(String.valueOf(savePath))).append(File.separator).append(getFileInputFileName()).toString()));
        response.getWriter().print("<font color='red'>Hello world!</font>");
        return null;
    }

    public File getFileInput()
    {
        return fileInput;
    }

    public void setFileInput(File fileInput)
    {
        this.fileInput = fileInput;
    }

    public String getFileInputFileName()
    {
        return fileInputFileName;
    }

    public void setFileInputFileName(String fileInputFileName)
    {
        this.fileInputFileName = fileInputFileName;
    }

    public String getSavePath()
    {
        return savePath;
    }

    public void setSavePath(String savePath)
    {
        this.savePath = savePath;
    }

    private File fileInput;
    private String fileInputFileName;
    private String savePath;
}
