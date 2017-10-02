// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DfspAction.java

package me.songbx.action;

import me.songbx.model.Dfsp;
import me.songbx.service.DfspService;

// Referenced classes of package me.songbx.action:
//            Constant

public class DfspAction extends Constant
{

    public DfspAction()
    {
        binaService = new DfspService();
    }

    public String startDfsp()
    {
        bina = new Dfsp();
        bina.setCondition(condition);
        bina.seteValue(expectationValue);
        bina.setUuid(uuid);
        bina.setNetWrokFile(uploadppifile);
        bina.setSequenceFile(uploadsequencefile);
        binaService.setBina(bina);
        binaService.binaRun();
        return "success";
    }

    public String resultDfsp()
    {
        bina = binaService.getBinaByUUID(uuid);
        if(bina.getIsExist() == 0 || bina == null)
            return "none";
        if(bina.getComplete() == 0)
            if(bina.getIsBegin() == 0)
                return "queue";
            else
                return "doing";
        if(1 == bina.getError())
            return "error";
        else
            return "success";
    }

    public String getUploadppifile()
    {
        return uploadppifile;
    }

    public void setUploadppifile(String uploadppifile)
    {
        this.uploadppifile = uploadppifile;
    }

    public String getUploadsequencefile()
    {
        return uploadsequencefile;
    }

    public void setUploadsequencefile(String uploadsequencefile)
    {
        this.uploadsequencefile = uploadsequencefile;
    }

    public String getExpectationValue()
    {
        return expectationValue;
    }

    public void setExpectationValue(String expectationValue)
    {
        this.expectationValue = expectationValue;
    }

    public int getCondition()
    {
        return condition;
    }

    public void setCondition(int condition)
    {
        this.condition = condition;
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
    private String uploadppifile;
    private String uploadsequencefile;
    private String expectationValue;
    private int condition;
    private String uuid;
    private Dfsp bina;
    private DfspService binaService;
}
