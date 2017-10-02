// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PssmService.java

package me.songbx.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import me.songbx.model.Pssm;
import me.songbx.util.HibernateUtil;
import me.songbx.util.constants.GlobalConstant;
import org.hibernate.*;

public class PssmService
{

    public PssmService()
    {
    }

    public void pssmRun()
    {
        pssm.setCreateTime(new Date());
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.save(pssm);
        s.getTransaction().commit();
    }

    public Pssm getPssmByUUID(String uuid)
    {
        pssm = new Pssm();
        File file = new File((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).toString());
        pssm.setIsExist(0);
        if(file.exists())
        {
            File files[] = file.listFiles();
            File afile[];
            int j = (afile = files).length;
            for(int i = 0; i < j; i++)
            {
                File f = afile[i];
                if(f.isFile())
                    pssm.setIsExist(1);
            }

        }
        if(pssm.getIsExist() == 0)
            return pssm;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List pssms = s.createQuery((new StringBuilder("from Pssm pssm where pssm.uuid='")).append(uuid).append("'").toString()).list();
        s.getTransaction().commit();
        if(pssms.size() > 0)
        {
            pssm = (Pssm)pssms.get(0);
            pssm.setIsExist(1);
            return pssm;
        } else
        {
            return null;
        }
    }

    public Pssm getPssm()
    {
        return pssm;
    }

    public void setPssm(Pssm pssm)
    {
        this.pssm = pssm;
    }

    Pssm pssm;
}
