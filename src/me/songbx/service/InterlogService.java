

package me.songbx.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import me.songbx.model.Interlog;

import me.songbx.util.HibernateUtil;
import me.songbx.util.constants.GlobalConstant;
import org.hibernate.*;

public class InterlogService
{

	Interlog interlog;
    public void interlogRun()
    {
    	interlog.setCreateTime(new Date());
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.save(interlog);
        s.getTransaction().commit();
    }

    public Interlog getInterlogByUUID(String uuid)
    {
    	interlog = new Interlog();
        File file = new File((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).toString());
        interlog.setIsExist(0);
        if(file.exists())
        {
            File files[] = file.listFiles();
            File afile[];
            int j = (afile = files).length;
            for(int i = 0; i < j; i++)
            {
                File f = afile[i];
                if(f.isFile())
                	interlog.setIsExist(1);
            }

        }
        if(interlog.getIsExist() == 0)
            return interlog;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List interlogs = s.createQuery((new StringBuilder("from Interlog interlog where interlog.uuid='")).append(uuid).append("'").toString()).list();
        s.getTransaction().commit();
        if(interlogs.size() > 0)
        {
        	interlog = (Interlog)interlogs.get(0);
        	interlog.setIsExist(1);
            return interlog;
        } else
        {
            return null;
        }
    }

	public Interlog getInterlog() {
		return interlog;
	}

	public void setInterlog(Interlog interlog) {
		this.interlog = interlog;
	}
    
}
