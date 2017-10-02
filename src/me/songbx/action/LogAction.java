// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LogAction.java

package me.songbx.action;

import java.io.*;
import me.songbx.util.constants.GlobalConstant;

// Referenced classes of package me.songbx.action:
//            Constant

public class LogAction extends Constant
{
    private String uuid;
    private String type;
    private String content;

    public String content()
    {
        String filePath = (GlobalConstant.UPLOADFILEPATH+File.separator+uuid+File.separator+"log");
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuffer contentStringBuffer = new StringBuffer();
        try
        {
            reader = new BufferedReader(new FileReader(file));
            for(String tempString = null; (tempString = reader.readLine()) != null;){
                contentStringBuffer.append((new StringBuilder(String.valueOf(tempString))).append("\n\r").toString());
            }
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }finally{
	        if(reader != null){
	            try
	            {
	                reader.close();
	            }catch(IOException e)
		        {
		            e.printStackTrace();
		        }
	        }
	        this.setContent(contentStringBuffer.toString());
        }
        return "success";
    }

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
