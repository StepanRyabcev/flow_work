import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import org.w3c.dom.*;

public class XMLparser 
{
	public static String parse(String fname)
	{	
		try 
		{
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
            return output;
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
