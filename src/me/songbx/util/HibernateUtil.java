// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HibernateUtil.java

package me.songbx.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil
{

    public HibernateUtil()
    {
    }

    public static SessionFactory getSessionFactory()
    {
        return sf;
    }

    private static SessionFactory sf = (new AnnotationConfiguration()).configure().buildSessionFactory();

}
