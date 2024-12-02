import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class enpryptionOptions {
	
	enpryptionOptions()
	{
		String out = "";
		try
		{
		 FileReader reader = new FileReader("encrOpt.txt");		 
		 int c;
		 while((c=reader.read())!=-1)
		 	{ 
			 	out += (char)c;
		 	}
		 reader.close();
		}
		catch (FileNotFoundException ex) 
		{
			System.out.println("Не найден файл с опциями для шифрования");
			System.exit(0);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		 
		 needDecr = Boolean.valueOf(out.substring(0, out.indexOf("\n") - 1));
		 out = out.substring(out.indexOf("\n") + 1);
		 Decrkey = out.substring(0, out.indexOf("\n") - 1);
		 out = out.substring(out.indexOf("\n") + 1);
		 needEncr = Boolean.valueOf(out.substring(0, out.indexOf("\n") - 1));
		 if (needEncr)
			 Encrkey = out.substring(out.indexOf("\n") + 1);
		 }
		 
	
	enpryptionOptions(boolean decrypt, String decrKey)
	{
		needDecr = decrypt;
		Decrkey = decrKey;
	}
	
	boolean needDecr;
	String Decrkey;
	boolean needEncr;
	String Encrkey;
	
	public boolean isNeedtoDecrypt()
	{
		return needDecr;
	}
	public String DecryptionKey()
	{
		return Decrkey;
	}
	public boolean isNeedtoEncrypt()
	{
		return needEncr;
	}
	public String EncryptionKey()
	{
		return Encrkey;
	}
	
	public void SetDecr(boolean b)
	{
		needDecr = b;
	}
	
	public void SetDecrKey(String s)
	{
		Decrkey = s;
	}
}