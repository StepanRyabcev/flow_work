import java.io.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class reader {
		
	private String fname;	
	boolean Specified = false;
	boolean fromZIP = false;
	boolean fromXML = false;
	boolean fromJSON = false;
	boolean fromYAML = false;
	boolean from7z = false;
	enpryptionOptions op;
	
	boolean GUIMode = true;
	
	reader(String ffname, enpryptionOptions op1)
	{
		op = op1;
		fname = ffname;
		Specified = true;
		Reader();
	}
	
	private void Reader()
	{
		if (Specified == false)
		{	
			JFrame jf = new JFrame();
			FileDialog fd = new FileDialog(jf, "Choose a file", FileDialog.LOAD);
			fd.setDirectory("C:\\Users\\ryabt\\eclipse-workspace\\calculate\\src");
			fd.setFile("*.txt;*.zip;*.xml;*.json;*.yml;*.yaml;*.7z;*.rar");
			fd.setVisible(true);
			fname = "C:\\Users\\ryabt\\eclipse-workspace\\calculate\\src\\" + fd.getFile();
		}
		if((fname.endsWith(".7z")) || (fname.endsWith(".rar")))
		{
			from7z = true;
			ArchiveExtractor uz = new ArchiveExtractor(fname);
			fname = uz.select();
		}
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
		if (fname.endsWith(".json"))
		{
			fromJSON = true;
		}
		if ((fname.endsWith(".yml")) || (fname.endsWith(".yaml")))
		{
			fromYAML = true;
		}
	}
	
	reader(enpryptionOptions op1)
	{
		op = op1;
		Reader();
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
			return (XMLparser.parse(fname, op));
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
        	 deleteTempFile();
         if (from7z)
         {
        	 deleteTempFile();
         }
         
         out = crypto.decryptIfNeeded(out, op);
         if (fromJSON == true)
        	 out = JSONparser.parse(out);
         if (fromYAML == true)
        	 out = YAMLparser.parse(out);
         return out;
		 }	
		 catch (FileNotFoundException ex) 
			{
			 	if(GUIMode)
					JOptionPane.showMessageDialog(null, "Не найден файл для загрузки выражения", "Ошибка", JOptionPane.ERROR_MESSAGE);
			 	else
			 		System.out.println("Не найден файл для загрузки выражения");
				System.exit(0);
			}
			catch(IOException e)
			{
				if(GUIMode)
					JOptionPane.showMessageDialog(null, "Произошла ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
			 	else
				e.printStackTrace();
				System.exit(0);
			}
		return "";   
	}
}