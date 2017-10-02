// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MultipleThreadRun.java

package me.songbx.util;

import java.util.Iterator;
import java.util.List;
import me.songbx.model.*;
import me.songbx.util.runCommand.DfspRunCommand;
import me.songbx.util.runCommand.InterlogRunCommand;
import me.songbx.util.runCommand.MmistRunCommand;
import me.songbx.util.runCommand.PssmRunCommand;
import org.hibernate.*;

// Referenced classes of package me.songbx.util:
//            HibernateUtil

public class MultipleThreadRun extends Thread
{

    public MultipleThreadRun()
    {
    }

	public void run()
	{
		do
		{
			runUncompletedTask();
			runUnstartedTask();
			try
			{
				Thread.sleep(10000L);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		} while(true);
	}

    private void runUncompletedTask()
    {
        runUncompletedDfspTask();
        runUncompletedPssmTask();
        runUncompletedMmistTask();
        runUncompletedInterlogTask();
    }

    private void runUnstartedTask()
    {
        do
        {
            runUnstartedDfspTask();
            runUnstartedPssmTask();
            runUnstartedMmistTask();
            runUnstartedInterlogTask();
            try
            {
                Thread.sleep(10000L);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        } while(true);
    }

    private void runUncompletedDfspTask()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List dfsps = s.createQuery("from Dfsp dfsp where dfsp.isBegin=1 and dfsp.error=0 and dfsp.complete=0").list();
        s.getTransaction().commit();
        runDfsp(dfsps);
    }
    

    public void runUnstartedDfspTask()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List dfsps = s.createQuery("from Dfsp dfsp where dfsp.isBegin=0").list();
        s.getTransaction().commit();
        runDfsp(dfsps);
    }

    private void runDfsp(List dfsps)
    {
        for(Iterator iterator = dfsps.iterator(); iterator.hasNext();)
        {
            Dfsp dfsp = (Dfsp)iterator.next();
            DfspRunCommand binaRunCommand = new DfspRunCommand();
            binaRunCommand.setBina(dfsp);
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session s = sf.getCurrentSession();
            try
            {
                dfsp.setIsBegin(1);
                sf = HibernateUtil.getSessionFactory();
                s = sf.getCurrentSession();
                s.beginTransaction();
                s.update(dfsp);
                s.getTransaction().commit();
                binaRunCommand.run();
            }
            catch(Exception e)
            {
                dfsp.setError(1);
                sf = HibernateUtil.getSessionFactory();
                s = sf.getCurrentSession();
                s.beginTransaction();
                s.update(dfsp);
                s.getTransaction().commit();
                e.printStackTrace();
            }
        }

    }

    private void runUncompletedPssmTask()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List pssms = s.createQuery("from Pssm pssm where pssm.isBegin=1 and pssm.error=0 and pssm.complete=0").list();
        s.getTransaction().commit();
        runPssm(pssms);
    }

    public void runUnstartedPssmTask()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List pssms = s.createQuery("from Pssm pssm where pssm.isBegin=0").list();
        s.getTransaction().commit();
        runPssm(pssms);
    }

    private void runPssm(List pssms)
    {
        for(Iterator iterator = pssms.iterator(); iterator.hasNext();)
        {
            Pssm pssm = (Pssm)iterator.next();
            PssmRunCommand pssmRunCommand = new PssmRunCommand(pssm);
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session s = sf.getCurrentSession();
            try
            {
                pssm.setIsBegin(1);
                sf = HibernateUtil.getSessionFactory();
                s = sf.getCurrentSession();
                s.beginTransaction();
                s.update(pssm);
                s.getTransaction().commit();
                pssmRunCommand.run();
            }
            catch(Exception e)
            {
                pssm.setError(1);
                sf = HibernateUtil.getSessionFactory();
                s = sf.getCurrentSession();
                s.beginTransaction();
                s.update(pssm);
                s.getTransaction().commit();
                e.printStackTrace();
            }
        }

    }
    
    private void runUncompletedInterlogTask()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List interlogs = s.createQuery("from Interlog interlog where interlog.isBegin=1 and interlog.error=0 and interlog.complete=0").list();
        s.getTransaction().commit();
        runInterlog(interlogs);
    }

    public void runUnstartedInterlogTask()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List interlogs = s.createQuery("from Interlog interlog where interlog.isBegin=0").list();
        s.getTransaction().commit();
        runInterlog(interlogs);
    }

    private void runInterlog(List interlogs)
    {
        for(Iterator iterator = interlogs.iterator(); iterator.hasNext();)
        {
        	Interlog interlog  = (Interlog)iterator.next();
        	InterlogRunCommand interlogRunCommand = new InterlogRunCommand(interlog);
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session s = sf.getCurrentSession();
            try
            {
            	interlog.setIsBegin(1);
                sf = HibernateUtil.getSessionFactory();
                s = sf.getCurrentSession();
                s.beginTransaction();
                s.update(interlog);
                s.getTransaction().commit();
                interlogRunCommand.run();
            }
            catch(Exception e)
            {
            	interlog.setError(1);
                sf = HibernateUtil.getSessionFactory();
                s = sf.getCurrentSession();
                s.beginTransaction();
                s.update(interlog);
                s.getTransaction().commit();
                e.printStackTrace();
            }
        }

    }

    private void runUncompletedMmistTask()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List mmists = s.createQuery("from Mmist mmist where mmist.isBegin=1 and mmist.error=0 and mmist.complete=0").list();
        s.getTransaction().commit();
        runMmist(mmists);
    }

    public void runUnstartedMmistTask()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        List mmists = s.createQuery("from Mmist mmist where mmist.isBegin=0").list();
        s.getTransaction().commit();
        runMmist(mmists);
    }

    private void runMmist(List mmists)
    {
        for(Iterator iterator = mmists.iterator(); iterator.hasNext();)
        {
            Mmist mmist = (Mmist)iterator.next();
            MmistRunCommand mmistRunCommand = new MmistRunCommand(mmist);
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session s = sf.getCurrentSession();
            try
            {
                mmist.setIsBegin(1);
                sf = HibernateUtil.getSessionFactory();
                s = sf.getCurrentSession();
                s.beginTransaction();
                s.update(mmist);
                s.getTransaction().commit();
                mmistRunCommand.run();
            }
            catch(Exception e)
            {
                mmist.setError(1);
                sf = HibernateUtil.getSessionFactory();
                s = sf.getCurrentSession();
                s.beginTransaction();
                s.update(mmist);
                s.getTransaction().commit();
                e.printStackTrace();
            }
        }

    }
}
