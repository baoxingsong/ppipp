// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MmistService.java

package me.songbx.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import me.songbx.model.Mmist;
import me.songbx.util.HibernateUtil;
import me.songbx.util.constants.GlobalConstant;
import org.hibernate.*;

public class MmistService
{

    public MmistService()
    {
    }

    public void mmistRun()
    {
        mmist.setCreateTime(new Date());
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.save(mmist);
        s.getTransaction().commit();
    }

    public Mmist getMmistByUUID(String uuid)
    {
        mmist = new Mmist();
        File file = new File((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).toString());
        mmist.setIsExist(0);
        if(file.exists())
        {
            File files[] = file.listFiles();
            File afile[];
            int j = (afile = files).length;
            for(int i = 0; i < j; i++)
            {
                File f = afile[i];
                if(f.isFile())
                    mmist.setIsExist(1);
            }

        }
        if(mmist.getIsExist() == 0)
            return mmist;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List mmists = s.createQuery((new StringBuilder("from Mmist mmist where mmist.uuid='")).append(uuid).append("'").toString()).list();
        s.getTransaction().commit();
        if(mmists.size() > 0)
        {
            mmist = (Mmist)mmists.get(0);
            mmist.setIsExist(1);
            return mmist;
        } else
        {
            return null;
        }
    }

    public Mmist getMmist()
    {
        return mmist;
    }

    public void setMmist(Mmist mmist)
    {
        this.mmist = mmist;
    }

    Mmist mmist;
}
