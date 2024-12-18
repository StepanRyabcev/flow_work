import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.File; 

	
public class UnZip {

	String fname;
	Vector<String> files = new Vector<String>(0);
	
	UnZip(String ffname)
	{
		fname = ffname;
		try(ZipInputStream zin = new ZipInputStream(new FileInputStream(fname)))
        {
            ZipEntry entry;
            String name;
            while((entry=zin.getNextEntry())!=null){
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream("unzipped_" + name);
                files.add(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
            
            
            if (files.capacity() == 1)
            {
            	fname = "unzipped_" + files.get(0);
            }
            else
            {
            	select();
            }
        }
        catch(Exception ex){
              
            System.out.println(ex.getMessage());
        } 
	}
	
	public String getFname()
	{
		return fname;
	}
	
	private void select()
	{
		int selected;
		while(true)
		{
			try {
			System.out.println("Выберите файл");
			for (int i = 0; i < files.capacity(); i++)
			{
				System.out.println(i + ": " + files.get(i));
			}	
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			selected = in.nextInt();
			fname = "unzipped_" + files.get(selected);
			deleteunnessasaryfiles(selected);
			}
		catch(InputMismatchException e)
		{
			System.out.println("Неверный ввод");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		}
	}
	
	
	private void deleteunnessasaryfiles(int toKeep)
	{
        for (int i = 0; i < files.capacity(); i++)
        {
        	if (i != toKeep)
        	{
        		File toDelete = new File("unzipped_" + files.get(i));
        		toDelete.delete();
        	}
        }
	}
}
