// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MmistAction.java

package me.songbx.action;

import me.songbx.model.Mmist;
import me.songbx.service.MmistService;

// Referenced classes of package me.songbx.action:
//            Constant

public class MmistAction extends Constant
{

    public MmistAction()
    {
        mmistService = new MmistService();
    }

    public String startMmist()
    {
        mmist = new Mmist();
        mmist.setUuid(uuid);
        mmist.setSequenceFile(uploadsequencefile);
        mmistService.setMmist(mmist);
        mmistService.mmistRun();
        return "success";
    }

    public String resultMmist()
    {
        mmist = mmistService.getMmistByUUID(uuid);
        if(mmist.getIsExist() == 0 || mmist == null)
            return "none";
        if(mmist.getComplete() == 0)
            if(mmist.getIsBegin() == 0)
                return "queue";
            else
                return "doing";
        if(1 == mmist.getError())
            return "error";
        else
            return "success";
    }

    public String getUploadsequencefile()
    {
        return uploadsequencefile;
    }

    public void setUploadsequencefile(String uploadsequencefile)
    {
        this.uploadsequencefile = uploadsequencefile;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public static long getSerialversionuid()
    {
        return 1L;
    }

    private static final long serialVersionUID = 1L;
    private String uploadsequencefile;
    private String uuid;
    private Mmist mmist;
    private MmistService mmistService;
}
