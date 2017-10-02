// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Mmist.java

package me.songbx.model;

import java.util.Date;

public class Mmist
{

    public Mmist()
    {
        isExist = 1;
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

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getSequenceFile()
    {
        return sequenceFile;
    }

    public void setSequenceFile(String sequenceFile)
    {
        this.sequenceFile = sequenceFile;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    private int id;
    private int error;
    private int complete;
    private int isExist;
    private int isBegin;
    private String uuid;
    private String sequenceFile;
    private Date createTime;
}
