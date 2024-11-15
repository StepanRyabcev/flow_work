import java.awt.FileDialog;
import java.io.*;
import javax.swing.JFrame;

import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class write 
{
	String fname;
	enpryptionOptions op;
	
	write(enpryptionOptions op1)
	{
		op = op1;
		JFrame jf = new JFrame();
		FileDialog fd = new FileDialog(jf, "Choose a file", FileDialog.SAVE);
		fd.setDirectory("C:\\Users\\ryabt\\eclipse-workspace\\calculate\\src");
		fd.setFile("*.txt");
		fd.setVisible(true);
		fname = "C:\\Users\\ryabt\\eclipse-workspace\\calculate\\src\\" + fd.getFile();
	}
	
	public void save(String dataToSave)
	{
		String selected = "false";
		System.out.println(selected);
		if (selected == "true") 	
		{
        	System.out.println("Введите ключ");
        	try (Scanner in2 = new Scanner(System.in)) {
				String key = in2.nextLine();
				try {
					dataToSave = crypto.encrypt(dataToSave, key);
				} catch (Exception e) {	
					e.printStackTrace();
				}
			}
		}
		 try(FileWriter writer = new FileWriter(fname + ".txt", false))
	        {
	            writer.write(dataToSave);
	            writer.flush();
	            saveToZip();
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 
	}
	
	private void saveToZip()
	{
		try (FileOutputStream fos = new FileOutputStream(fname + ".zip"))
		{
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(fname + ".txt");
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
}
