// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DfspService.java

package me.songbx.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import me.songbx.model.Dfsp;
import me.songbx.util.HibernateUtil;
import me.songbx.util.constants.GlobalConstant;
import org.hibernate.*;

public class DfspService
{

    public DfspService()
    {
        bina = new Dfsp();
    }

    public void binaRun()
    {
        bina.setCreateTime(new Date());
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.save(bina);
        s.getTransaction().commit();
    }

    public Dfsp getBinaByUUID(String uuid)
    {
        bina = new Dfsp();
        File file = new File((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).toString());
        bina.setIsExist(0);
        if(file.exists())
        {
            File files[] = file.listFiles();
            File afile[];
            int j = (afile = files).length;
            for(int i = 0; i < j; i++)
            {
                File f = afile[i];
                if(f.isFile())
                    bina.setIsExist(1);
            }

        }
        if(bina.getIsExist() == 0)
            return bina;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List binas = s.createQuery((new StringBuilder("from Dfsp bina where bina.uuid='")).append(uuid).append("'").toString()).list();
        s.getTransaction().commit();
        if(binas.size() > 0)
        {
            bina = (Dfsp)binas.get(0);
            bina.setIsExist(1);
            return bina;
        } else
        {
            return null;
        }
    }

    public Dfsp getBina()
    {
        return bina;
    }

    public void setBina(Dfsp bina)
    {
        this.bina = bina;
    }

    Dfsp bina;
}
