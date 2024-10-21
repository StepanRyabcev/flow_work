import java.io.FileReader;
import java.io.IOException;

public class enpryptionOptions {
	
	enpryptionOptions() throws IOException
	{
		 try(FileReader reader = new FileReader("encrOpt.txt"))
		 {			 
		 int c;
		 String out = "";
		 while((c=reader.read())!=-1)
		 	{ 
			 	out += (char)c;
		 	}
		 reader.close();

		 needDecr = Boolean.valueOf(out.substring(0, out.indexOf("\n") - 1));
		 out = out.substring(out.indexOf("\n") + 1);
		 Decrkey = out.substring(0, out.indexOf("\n") - 1);
		 out = out.substring(out.indexOf("\n") + 1);
		 needEncr = Boolean.valueOf(out.substring(0, out.indexOf("\n") - 1));
		 if (needEncr)
			 Encrkey = out.substring(out.indexOf("\n") + 1);
		 }
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