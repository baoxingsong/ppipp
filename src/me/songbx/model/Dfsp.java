// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Dfsp.java

package me.songbx.model;

import java.io.File;
import java.io.PrintStream;
import java.util.Date;
import me.songbx.util.constants.GlobalConstant;

public class Dfsp
{

    public Dfsp()
    {
        isExist = 1;
    }

    public String[] toFormatComamnd()
    {
        String formatCommand[] = new String[3];
        formatCommand[0] = (new StringBuilder(String.valueOf(GlobalConstant.SOFTWARE))).append("blast").append(File.separator).append("formatdb").toString();
        formatCommand[1] = "-i";
        formatCommand[2] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append(sequenceFile).toString();
        System.out.println((new StringBuilder(String.valueOf(formatCommand[0]))).append(" ").append(formatCommand[1]).append(" ").append(formatCommand[2]).toString());
        return formatCommand;
    }

    public String[] toBlastComamnd()
    {
        String blastCommand[] = new String[11];
        blastCommand[0] = (new StringBuilder(String.valueOf(GlobalConstant.SOFTWARE))).append("blast").append(File.separator).append("blastall").toString();
        blastCommand[1] = "-p";
        blastCommand[2] = "blastp";
        blastCommand[3] = "-i";
        blastCommand[4] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append(sequenceFile).toString();
        blastCommand[5] = "-d";
        blastCommand[6] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append(sequenceFile).toString();
        blastCommand[7] = "-o";
        blastCommand[8] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append(sequenceFile).append("_").append(sequenceFile).toString();
        blastCommand[9] = "-e";
        blastCommand[10] = eValue;
        System.out.println((new StringBuilder(String.valueOf(blastCommand[0]))).append(" ").append(blastCommand[1]).append(" ").append(blastCommand[2]).append(" ").append(blastCommand[3]).append(" ").append(blastCommand[4]).append(" ").append(blastCommand[5]).append(" ").append(blastCommand[6]).append(" ").append(blastCommand[7]).append(" ").append(blastCommand[8]).append(" ").append(blastCommand[9]).append(" ").append(blastCommand[10]).toString());
        return blastCommand;
    }

    public String[] toBinaComamnd()
    {
        String binaCommand[] = new String[21];
        binaCommand[0] = "java";
        binaCommand[1] = "-jar";
        if(condition == 0)
            binaCommand[2] = (new StringBuilder(String.valueOf(GlobalConstant.SOFTWARE))).append("bina").append(File.separator).append("MGKAligner_just_ne.jar").toString();
        else
        if(1 == condition)
            binaCommand[2] = (new StringBuilder(String.valueOf(GlobalConstant.SOFTWARE))).append("bina").append(File.separator).append("MGKAligner_ne_more_than_2.jar").toString();
        else
        if(2 == condition)
            binaCommand[2] = (new StringBuilder(String.valueOf(GlobalConstant.SOFTWARE))).append("bina").append(File.separator).append("MGKAligner_ne.jar").toString();
        else
        if(3 == condition)
            binaCommand[2] = (new StringBuilder(String.valueOf(GlobalConstant.SOFTWARE))).append("bina").append(File.separator).append("MGKAligner_ultimate.jar").toString();
        binaCommand[3] = "-query";
        binaCommand[4] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append(netWrokFile).toString();
        binaCommand[5] = "-target";
        binaCommand[6] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append(netWrokFile).toString();
        binaCommand[7] = "blast_net1_net2";
        binaCommand[8] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append(sequenceFile).append("_").append(sequenceFile).toString();
        binaCommand[9] = "-blast_net2_net1";
        binaCommand[10] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append(sequenceFile).append("_").append(sequenceFile).toString();
        binaCommand[11] = "-numThreads";
        binaCommand[12] = "4";
        binaCommand[13] = "-clusterMethod";
        binaCommand[14] = "khop";
        binaCommand[15] = "-graphKernel";
        binaCommand[16] = "sp";
        binaCommand[17] = "-printProgress";
        binaCommand[18] = "-printNodeMatch";
        binaCommand[19] = "-outputFile";
        binaCommand[20] = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(uuid).append(File.separator).append("DFSP_Result.txt").toString();
        System.out.println((new StringBuilder(String.valueOf(binaCommand[0]))).append(" ").append(binaCommand[1]).append(" ").append(binaCommand[2]).append(" ").append(binaCommand[3]).append(" ").append(binaCommand[4]).append(" ").append(binaCommand[5]).append(" ").append(binaCommand[6]).append(" ").append(binaCommand[7]).append(" ").append(binaCommand[8]).append(" ").append(binaCommand[9]).append(" ").append(binaCommand[10]).append(" ").append(binaCommand[11]).append(" ").append(binaCommand[12]).append(" ").append(binaCommand[13]).append(" ").append(binaCommand[14]).append(" ").append(binaCommand[15]).append(" ").append(binaCommand[16]).append(" ").append(binaCommand[17]).append(" ").append(binaCommand[18]).append(" ").append(binaCommand[19]).append(" ").append(binaCommand[20]).toString());
        return binaCommand;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getError()
    {
        return error;
    }

    public void setError(int error)
    {
        this.error = error;
    }

    public int getComplete()
    {
        return complete;
    }

    public void setComplete(int complete)
    {
        this.complete = complete;
    }

    public int getIsExist()
    {
        return isExist;
    }

    public void setIsExist(int isExist)
    {
        this.isExist = isExist;
    }

    public int getIsBegin()
    {
        return isBegin;
    }

    public void setIsBegin(int isBegin)
    {
        this.isBegin = isBegin;
    }

    public String getNetWrokFile()
    {
        return netWrokFile;
    }

    public void setNetWrokFile(String netWrokFile)
    {
        this.netWrokFile = netWrokFile;
    }

    public String getSequenceFile()
    {
        return sequenceFile;
    }

    public void setSequenceFile(String sequenceFile)
    {
        this.sequenceFile = sequenceFile;
    }

    public String geteValue()
    {
        return eValue;
    }

    public void seteValue(String eValue)
    {
        this.eValue = eValue;
    }

    public int getCondition()
    {
        return condition;
    }

    public void setCondition(int condition)
    {
        this.condition = condition;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    private int id;
    private int error;
    private int complete;
    private int isExist;
    private int isBegin;
    private String uuid;
    private String netWrokFile;
    private String sequenceFile;
    private String eValue;
    private int condition;
    private Date createTime;
}
