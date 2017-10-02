// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyServlet.java

package me.songbx.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

// Referenced classes of package me.songbx.util:
//            MultipleThreadRun

public class MyServlet extends HttpServlet
{

    public MyServlet()
    {
    }

    public void init()
        throws ServletException
    {
        super.init();
        MultipleThreadRun multipleThreadRun = new MultipleThreadRun();
        multipleThreadRun.start();
    }

    private static final long serialVersionUID = 0x402cead01cf26aa9L;
}
