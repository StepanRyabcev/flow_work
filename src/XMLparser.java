import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.w3c.dom.*;

public class XMLparser 
{
	public static String parse(String fname, enpryptionOptions op)
	{	
		try 
		{
			if (op.isNeedtoDecrypt() == true)
				fname = decrypt(fname, op);
		    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		    Document doc = builder.parse(new File(fname));
		    NodeList expressionList = doc.getElementsByTagName("T");
            String output = expressionList.item(0).getTextContent() + ".\n";
            NodeList variableList = doc.getElementsByTagName("Variable");
            for (int i = 0; i < variableList.getLength(); i++) 
            {
                Element variable = (Element) variableList.item(i);
                if (i != variableList.getLength() - 1)
                	output += variable.getAttribute("name") + " = " + variable.getTextContent() + ".\n";
                else
                	output += variable.getAttribute("name") + " = " + variable.getTextContent();
            }
            if (op.isNeedtoDecrypt() == true)
            {
            	File toDelete = new File(fname);
            	toDelete.delete();
            }
            return output;
		} 
		catch(Exception e)
		{
			System.out.println("Ошибка разбора XML");
			System.exit(0);
		}
		return "";
	}
	
	private static String decrypt(String fname, enpryptionOptions op) throws IOException
	{
		Path tempFile = Files.createTempFile("decrypted", ".xml");
		FileReader reader = new FileReader(fname);
		int c;
		String readed = "";
        while((c=reader.read())!=-1){ 
        	readed += (char)c;
        }
        reader.close();
        readed = crypto.decryptIfNeeded(readed, op);
        Files.writeString(tempFile, readed);
		return tempFile.toString();
	}
}
