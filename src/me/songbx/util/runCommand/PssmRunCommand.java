// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PssmRunCommand.java

package me.songbx.util.runCommand;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import me.songbx.model.Pssm;
import me.songbx.pssm.action.PssmRead;
import me.songbx.pssm.model.ProteinPair;
import me.songbx.util.HibernateUtil;
import me.songbx.util.constants.GlobalConstant;
import org.hibernate.*;

// Referenced classes of package me.songbx.util.runCommand:
//            ZipMaker

public class PssmRunCommand
{

    public PssmRunCommand(Pssm pssm)
    {
        this.pssm = pssm;
    }

    public void run()
        throws Exception
    {
        String pssmFile = (new StringBuilder(String.valueOf(GlobalConstant.SOFTWARE))).append("pssm").append(File.separator).append("pssm").toString();
        String uniprotFile = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(pssm.getUuid()).append(File.separator).append(pssm.getSequenceFile()).toString();
        PssmRead pssmRead = new PssmRead(pssmFile, uniprotFile);
        PrintWriter fileOut = new PrintWriter(new FileWriter((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(pssm.getUuid()).append(File.separator).append("DMIST_PPIN.txt").toString()));
        ProteinPair pp;
        for(Iterator iterator = pssmRead.getPredictedPPIs().iterator(); iterator.hasNext(); fileOut.println((new StringBuilder(String.valueOf(pp.getP1()))).append("\t").append(pp.getP2()).toString()))
            pp = (ProteinPair)iterator.next();

        fileOut.flush();
        fileOut.close();
        String outPath = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(pssm.getUuid()).append(File.separator).toString();
        File srcFile = new File(outPath);
        String zipFilePath = (new StringBuilder(String.valueOf(outPath))).append(File.separator).append("pssm.zip").toString();
        if(srcFile.exists())
        {
            File files[] = srcFile.listFiles();
            ZipMaker.compressFiles2Zip(files, zipFilePath);
        }
        pssm.setComplete(1);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.update(pssm);
        s.getTransaction().commit();
    }

    private Pssm pssm;
}
