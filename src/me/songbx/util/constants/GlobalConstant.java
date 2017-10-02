// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GlobalConstant.java

package me.songbx.util.constants;

import java.io.File;

public class GlobalConstant
{

    public GlobalConstant()
    {
    }

    public static final String WEBROOT;
    public static final String UPLOADFILEPATH;
    public static final String SOFTWARE;
    
    static 
    {
        WEBROOT = (new StringBuilder(String.valueOf(File.separator))).append("var").append(File.separator).append("lib").append(File.separator).append("tomcat7").append(File.separator).append("ppipp").append(File.separator).append("ROOT").append(File.separator).toString();
        UPLOADFILEPATH = (new StringBuilder(String.valueOf(WEBROOT))).append("WEB-INF").append(File.separator).append("upload").append(File.separator).toString();
        SOFTWARE = (new StringBuilder(String.valueOf(WEBROOT))).append("WEB-INF").append(File.separator).append("software").append(File.separator).toString();
    }
}
