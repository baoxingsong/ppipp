// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PssmAction.java

package me.songbx.action;

import me.songbx.model.Pssm;
import me.songbx.service.PssmService;

// Referenced classes of package me.songbx.action:
//            Constant

public class PssmAction extends Constant
{

    public PssmAction()
    {
        pssmService = new PssmService();
    }

    public String startPssm()
    {
        pssm = new Pssm();
        pssm.setUuid(uuid);
        pssm.setSequenceFile(uploadsequencefile);
        pssmService.setPssm(pssm);
        pssmService.pssmRun();
        return "success";
    }

    public String log()
    {
        pssm = pssmService.getPssmByUUID(uuid);
        return "success";
    }

    public String resultPssm()
    {
        pssm = pssmService.getPssmByUUID(uuid);
        if(pssm.getIsExist() == 0 || pssm == null)
            return "none";
        if(pssm.getComplete() == 0)
            if(pssm.getIsBegin() == 0)
                return "queue";
            else
                return "doing";
        if(1 == pssm.getError())
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
    private Pssm pssm;
    private PssmService pssmService;
}
