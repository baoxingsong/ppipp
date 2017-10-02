package me.songbx.util.runCommand;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

public class ZipMaker {
	
	public static void main(String[] args){
		String outPath="/home/songbaoxing/Workspaces/MyEclipse/Mesh/WebRoot/upload/username/parallemetacomapre/28";
		File srcFile = new File(outPath);
	    String zipFilePath = outPath + File.separator + "combine.zip";
	    if(srcFile.exists()) {
	    	File[] files = srcFile.listFiles();
	    	(new ZipMaker()).compressFiles2Zip(files, zipFilePath);
	    }
	}
	
	/**
	 * @param fileName       
	 * @return boolen
	 */
	public static boolean isEndsWithZip(String fileName) {
		boolean flag = false;
		if(fileName != null && !"".equals(fileName.trim())) {
			if(fileName.endsWith(".ZIP")||fileName.endsWith(".zip")){
				flag = true;
			}
		}
		return flag;
	}
	List<File> fileslist = new ArrayList<File>();

	static ZipArchiveOutputStream zaos = null;
	public static void compressFiles2Zip(File[] files,String zipFilePath) {
		
		if(files != null && files.length >0) {
			if(isEndsWithZip(zipFilePath)) {
				try {				
						File zipFile = new File(zipFilePath);
						zaos = new ZipArchiveOutputStream(zipFile);
						zaos.setUseZip64(Zip64Mode.AsNeeded);
						for(File file : files) {
							//String dir= File.separator;
							String dir= "";
							if(file.isFile()){
								zipfile(file, dir);
							}else if(file.isDirectory()){
								zipDir(file, dir);
							}
						}
						zaos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	public static void zipfile(File tobeFile, String dir){
		if(tobeFile != null) {
			System.out.println(dir);
			ZipArchiveEntry zipArchiveEntry  = new ZipArchiveEntry(dir + tobeFile.getName());
			try {
				zaos.putArchiveEntry(zipArchiveEntry);
				InputStream is = null;
				try {
					is = new BufferedInputStream(new FileInputStream(tobeFile));
					byte[] buffer = new byte[1024 * 5];  
					int len = -1;
					while((len = is.read(buffer)) != -1) {
						zaos.write(buffer, 0, len);
					}
					zaos.closeArchiveEntry();  
				}catch(Exception e) {
					throw new RuntimeException(e);
				}finally {
					if(is != null) 
						is.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static void zipDir(File tobeDir, String dir){
		File[] files = tobeDir.listFiles();
		if (files.length < 1) {
			ZipArchiveEntry zipArchiveEntry  = new ZipArchiveEntry(dir + tobeDir.getName()+File.separator);
			try {
				zaos.putArchiveEntry(zipArchiveEntry);
				zaos.closeArchiveEntry();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		dir = dir + tobeDir.getName() + File.separator ;
		
		for(File file : files){
			if(file.isFile()){
				zipfile(file,dir);
			}else if(file.isDirectory()){
				zipDir(file,dir);
			}
		}
	}
	
	public void compressFiles2ZipDo(File[] files,String zipFilePath) {
	
			if(isEndsWithZip(zipFilePath)) {
				
				try {
					File zipFile = new File(zipFilePath);
					zaos = new ZipArchiveOutputStream(zipFile);
					zaos.setUseZip64(Zip64Mode.AsNeeded);
					List<File> filesList=null;
					for(File file : files) {
						if(file != null) {
							ZipArchiveEntry zipArchiveEntry  = new ZipArchiveEntry(file,file.getName());
							zaos.putArchiveEntry(zipArchiveEntry);
							InputStream is = null;
							try {
								is = new BufferedInputStream(new FileInputStream(file));
								byte[] buffer = new byte[1024 * 5];  
								int len = -1;
								while((len = is.read(buffer)) != -1) {
									zaos.write(buffer, 0, len);
								}
								zaos.closeArchiveEntry();  
							}catch(Exception e) {
								throw new RuntimeException(e);
							}finally {
								if(is != null) 
									is.close();
							}
						}
					}
					zaos.finish();
				}catch(Exception e){
					throw new RuntimeException(e);
				}finally {
						try {
							if(zaos != null) {
								zaos.close();
							}
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
				}
			}
		
	}
}
