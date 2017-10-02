// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Constant.java

package me.songbx.action;

import com.opensymphony.xwork2.ActionSupport;

public class Constant extends ActionSupport
{

    public Constant()
    {
        webTitle = "Protein-Protein Interaction Prediction Platform";
    }

    public String getWebTitle()
    {
        return webTitle;
    }

    public void setWebTitle(String webTitle)
    {
        this.webTitle = webTitle;
    }

    private static final long serialVersionUID = 1L;
    public String webTitle;
}
