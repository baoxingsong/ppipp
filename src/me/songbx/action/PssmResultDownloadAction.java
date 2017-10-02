// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PssmResultDownloadAction.java

package me.songbx.action;

import java.io.*;
import me.songbx.util.constants.GlobalConstant;

// Referenced classes of package me.songbx.action:
//            Constant

public class PssmResultDownloadAction extends Constant
{

    public PssmResultDownloadAction()
    {
    }

    public void setInputPath(String value)
    {
        inputPath = value;
    }

    public InputStream getTargetFile()
        throws Exception
    {
        return new FileInputStream((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(inputPath).append(File.separator).append("pssm.zip").toString());
    }

    private String inputPath;
}
