import java.io.*;
import java.util.Scanner;
import java.awt.*;
import javax.swing.JFrame;

public class reader {
		
	private String fname = "input.txt";	
	boolean fromZIP = false;
	boolean fromXML = false;
	
	reader(String ffname)
	{
		fname = ffname;
	}
	
	reader() //порядок работы -> деархивация+ -> дешифрация -> чтение
	{
		JFrame jf = new JFrame();
		FileDialog fd = new FileDialog(jf, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\Users\\ryabt\\eclipse-workspace\\calculate\\src");
		fd.setFile("*.txt;*.zip;*.xml");
		fd.setVisible(true);
		fname = "C:\\Users\\ryabt\\eclipse-workspace\\calculate\\src\\" + fd.getFile();
		if (fname.endsWith(".zip"))
		{
	        UnZip uz = new UnZip(fname);
	        fname = uz.getFname();
	        fromZIP = true;
		}
		if (fname.endsWith(".xml"))
		{
			fromXML = true;
		}
	}
	
	void deleteTempFile()
	{
		File toDelete = new File(fname);
		toDelete.delete();
	}

	public String read()
	{
		if (fromXML == true)
		{
			return (XMLparser.parse(fname));
		}
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
         
 		System.out.println("Зашифрован ли файл");
 		boolean selected = false;
 		Scanner in1 = new Scanner(System.in);
 		selected = in1.nextBoolean();
         if (selected == true)
         {
         	System.out.println("Введите ключ");
         	try (Scanner in2 = new Scanner(System.in)) {	
 				String key = in2.nextLine();
 				try {
 					out = crypto.decrypt(out, key);
 				} catch (Exception e) {	
 					e.printStackTrace();
 				}
 			}
         }
         
         
         return out;
		 }	
		 catch(Exception ex){
			 System.out.println(ex.getMessage());
	        }
		return "";   
	}
}