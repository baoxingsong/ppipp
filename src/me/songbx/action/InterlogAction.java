
package me.songbx.action;

import me.songbx.model.Interlog;
import me.songbx.service.InterlogService;


public class InterlogAction extends Constant
{
	private static final long serialVersionUID = 1L;
    private String uploadsequencefile;
    private String uuid;
    private Interlog interlog;
    private InterlogService interlogService;
    
    public InterlogAction()
    {
    	interlogService = new InterlogService();
    }

    public String startInterlog()
    {
    	interlog = new Interlog();
    	interlog.setUuid(uuid);
    	interlog.setSequenceFile(uploadsequencefile);
    	interlogService.setInterlog(interlog);
    	interlogService.interlogRun();
        return "success";
    }

    public String log()
    {
    	interlog = interlogService.getInterlogByUUID(uuid);
        return "success";
    }

    public String resultInterlog()
    {
    	interlog = interlogService.getInterlogByUUID(uuid);
        if(interlog.getIsExist() == 0 || interlog == null)
            return "none";
        if(interlog.getComplete() == 0)
            if(interlog.getIsBegin() == 0)
                return "queue";
            else
                return "doing";
        if(1 == interlog.getError())
            return "error";
        else
            return "success";
    }

	public String getUploadsequencefile() {
		return uploadsequencefile;
	}

	public void setUploadsequencefile(String uploadsequencefile) {
		this.uploadsequencefile = uploadsequencefile;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Interlog getInterlog() {
		return interlog;
	}

	public void setInterlog(Interlog interlog) {
		this.interlog = interlog;
	}

	public InterlogService getInterlogService() {
		return interlogService;
	}

	public void setInterlogService(InterlogService interlogService) {
		this.interlogService = interlogService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
