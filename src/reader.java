import java.io.*;
import java.util.Scanner;
import java.awt.*;
import javax.swing.JFrame;

public class reader {
		
	private String fname = "input.txt";	
	boolean fromZIP = false;
	
	reader(String ffname)
	{
		fname = ffname;
	}
	
	reader()
	{
		JFrame jf = new JFrame();
		FileDialog fd = new FileDialog(jf, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\Users\\ryabt\\eclipse-workspace\\calculate\\src");
		fd.setFile("*.txt;*.zip");
		fd.setVisible(true);
		fname = "C:\\Users\\ryabt\\eclipse-workspace\\calculate\\src\\" + fd.getFile();
		if (fname.endsWith(".zip"))
		{
	        UnZip uz = new UnZip(fname);
	        fname = uz.getFname();
	        fromZIP = true;
		}
	}
	
	void deleteTempFile()
	{
		File toDelete = new File(fname);
		toDelete.delete();
	}

	public String read()
	{
		 try(FileReader reader = new FileReader(fname))
		 {			 
		 int c;
		 String out = "";
         while((c=reader.read())!=-1){ 
             out += (char)c;
         }
         reader.close();
         if (fromZIP)
         {
        	 deleteTempFile();
         }
         return out;
		 }	
		 catch(Exception ex){
			 System.out.println(ex.getMessage());
	        }
		return "";   
	}
}