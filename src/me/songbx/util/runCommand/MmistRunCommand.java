// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MmistRunCommand.java

package me.songbx.util.runCommand;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import me.songbx.mmist.model.*;
import me.songbx.mmist.service.SequenceService;
import me.songbx.model.Mmist;
import me.songbx.util.HibernateUtil;
import me.songbx.util.constants.GlobalConstant;
import org.hibernate.*;

// Referenced classes of package me.songbx.util.runCommand:
//            ZipMaker

public class MmistRunCommand
{

    public MmistRunCommand(Mmist mmist)
    {
        this.mmist = mmist;
    }

    public void run()
        throws Exception
    {
        String mmistFile = (new StringBuilder(String.valueOf(GlobalConstant.SOFTWARE))).append("mmist").append(File.separator).append("mmist").toString();
        String sequenceFile = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(mmist.getUuid()).append(File.separator).append(mmist.getSequenceFile()).toString();
        String logFIle = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(mmist.getUuid()).append(File.separator).append("log").toString();
        SequenceService sequenceService = new SequenceService(sequenceFile, mmistFile, logFIle);
        PrintWriter fileOut = new PrintWriter(new FileWriter((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(mmist.getUuid()).append(File.separator).append("MMIST_PPIN.txt").toString()));
        for(Iterator iterator = sequenceService.getPpiHashSet().iterator(); iterator.hasNext(); fileOut.write("\n"))
        {
            Ppi ppi = (Ppi)iterator.next();
            fileOut.write((new StringBuilder(String.valueOf(ppi.getSequenceA().getAcc()))).append("\t").append(ppi.getSequenceB().getAcc()).append("\t").append(ppi.getBindInfors().size()).toString());
            BindInfor bindInfor;
            for(Iterator iterator1 = ppi.getBindInfors().iterator(); iterator1.hasNext(); fileOut.write((new StringBuilder(";\t")).append(bindInfor.getProfileA().getGi()).append(" ").append(bindInfor.getProfileA().getSeq()).append(" ").append(bindInfor.getProfileB().getGi()).append(" ").append(bindInfor.getProfileB().getSeq()).toString()))
                bindInfor = (BindInfor)iterator1.next();

        }

        fileOut.flush();
        fileOut.close();
        String outPath = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(mmist.getUuid()).append(File.separator).toString();
        File srcFile = new File(outPath);
        String zipFilePath = (new StringBuilder(String.valueOf(outPath))).append(File.separator).append("mmist.zip").toString();
        if(srcFile.exists())
        {
            File files[] = srcFile.listFiles();
            ZipMaker.compressFiles2Zip(files, zipFilePath);
        }
        mmist.setComplete(1);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.update(mmist);
        s.getTransaction().commit();
    }

    private Mmist mmist;
}
