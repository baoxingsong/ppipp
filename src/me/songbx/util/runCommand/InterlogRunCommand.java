

package me.songbx.util.runCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import me.songbx.fromInterPrologToPpi.model.Ppi;
import me.songbx.fromInterPrologToPpi.service.ClusterService;
import me.songbx.model.Interlog;
import me.songbx.util.HibernateUtil;
import me.songbx.util.constants.GlobalConstant;
import org.hibernate.*;

// Referenced classes of package me.songbx.util.runCommand:
//            ZipMaker

public class InterlogRunCommand
{
	private Interlog interlog;
    public InterlogRunCommand(Interlog interlog)
    {
        this.interlog = interlog;
    }

    public void run()
        throws Exception
    {
    	String logFile = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(interlog.getUuid()).append(File.separator).append("log").toString();
    	PrintWriter logFileOut = new PrintWriter(new FileWriter(logFile));
    	
    	String filePath=GlobalConstant.UPLOADFILEPATH + interlog.getUuid()+File.separator;
    	String queryFile=interlog.getSequenceFile();
    	String codePath=GlobalConstant.SOFTWARE+"inparanoid"+File.separator;
    	String fullCodePath=codePath+"SONGbAOXINGinparanoid.pl";
    	
    	List<String> cmds = new ArrayList<String>();
		cmds.add("perl");
		cmds.add(fullCodePath);
		cmds.add(queryFile);
		cmds.add(filePath);
		try {
			ProcessBuilder pb=new ProcessBuilder(cmds);
			Process p = pb.start();
			BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		    BufferedReader er=new BufferedReader(new InputStreamReader(p.getErrorStream()));
		    String inline;
		    String oriPath=System.getProperty("user.dir");
		    System.setProperty("user.dir", GlobalConstant.SOFTWARE+"inparanoid");
	    	while(null!=(inline=br.readLine()) || null!=(inline=er.readLine())){
		    	System.out.println(inline);
		    	logFileOut.write(inline+"\n");
		    	logFileOut.flush();
		    }
		    
		    while(null!=(inline=er.readLine())){
		    	System.out.println(inline);
		    	logFileOut.write(inline+"\n");
		    	logFileOut.flush();
		    }
			
		    if (p.waitFor() != 0) {
                if (p.exitValue() == 1)//
                    System.err.println("blast failed"); 
                	logFileOut.write("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! blast failed\n");
                	logFileOut.flush();
                	throw new Exception();
            }
		    System.setProperty("user.dir", oriPath);
	    }catch (InterruptedException e) {
			e.printStackTrace();
			logFileOut.write(e.getMessage()+"\n");
			logFileOut.flush();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			logFileOut.write(e.getMessage()+"\n");
			logFileOut.flush();
			throw e;
		}
		
    	
		
		ClusterService cluster=new ClusterService(filePath+"table."+queryFile+"-TARGET", codePath+"allInOnePin", filePath+queryFile);
		
		String resultFile = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(interlog.getUuid()).append(File.separator).append("interlogResultPPI").toString();
    	PrintWriter resultFileOut = new PrintWriter(new FileWriter(resultFile));
    	for(Ppi p:cluster.getResultPpis()){
			System.out.println(p.getProteinA()+"	"+p.getProteinB());
			resultFileOut.write(p.getProteinA()+"	"+p.getProteinB()+"\n");
		}
		System.out.println("complete !");
		logFileOut.write("complete !\n");
		logFileOut.flush();
		logFileOut.close();
		resultFileOut.flush();
		resultFileOut.close();
        String outPath = (new StringBuilder(String.valueOf(GlobalConstant.UPLOADFILEPATH))).append(interlog.getUuid()).append(File.separator).toString();
        File srcFile = new File(outPath);
        String zipFilePath = (new StringBuilder(String.valueOf(outPath))).append(File.separator).append("interlog.zip").toString();
        if(srcFile.exists())
        {
            File files[] = srcFile.listFiles();
            ZipMaker.compressFiles2Zip(files, zipFilePath);
        }
        interlog.setComplete(1);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.getCurrentSession();
        s.beginTransaction();
        s.update(interlog);
        s.getTransaction().commit();
    }


}
