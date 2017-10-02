// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DfspRunCommand.java

package me.songbx.util.runCommand;

import java.io.*;
import me.songbx.model.Dfsp;
import me.songbx.util.HibernateUtil;
import me.songbx.util.constants.GlobalConstant;
import org.hibernate.*;

// Referenced classes of package me.songbx.util.runCommand:
//            ZipMaker

public class DfspRunCommand
{

    public DfspRunCommand()
    {
        bina = new Dfsp();
    }

    public void run()
        throws Exception
    {
        Runtime r = Runtime.getRuntime();
        Process p = r.exec(bina.toFormatComamnd());
        p.waitFor();
        p = r.exec(bina.toBlastComamnd());
        p.waitFor();
        p = r.exec(bina.toBinaComamnd());
        p.waitFor();
        PrintWriter fileOut = new PrintWriter(new FileWriter((new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(bina.getUuid()).append(File.separator).append("command.txt").toString()));
        fileOut.println((new StringBuilder("Sequence File: ")).append(bina.getSequenceFile()).toString());
        fileOut.println((new StringBuilder("PPI network file: ")).append(bina.getNetWrokFile()).toString());
        fileOut.println((new StringBuilder("Condition: ")).append(bina.getCondition()).toString());
        fileOut.println((new StringBuilder("Evalue: ")).append(bina.geteValue()).toString());
        fileOut.flush();
        fileOut.close();
        String outPath = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(bina.getUuid()).append(File.separator).toString();
        File srcFile = new File(outPath);
        String zipFilePath = (new StringBuilder(String.valueOf(outPath))).append(File.separator).append("bina.zip").toString();
        if(srcFile.exists())
        {
            File files[] = srcFile.listFiles();
            ZipMaker.compressFiles2Zip(files, zipFilePath);
        }
        bina.setComplete(1);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.update(bina);
        s.getTransaction().commit();
    }

    public Dfsp getBina()
    {
        return bina;
    }

    public void setBina(Dfsp bina)
    {
        this.bina = bina;
    }

    private Dfsp bina;
}
