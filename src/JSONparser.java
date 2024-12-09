import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONparser 
{
	static boolean GUIMode = true;
	
	public static String parse(String json)
	{
		String out = "";
		try
		{
		ObjectMapper objectMapper = new ObjectMapper();
		Expression data = objectMapper.readValue(json, Expression.class);
		out = data.getExpression() + "\r\n";
		for (int i = 0; i < data.getVariablesNames().size(); i++)
		{
			out += data.getVariablesNames().get(i) + " = " + data.getVariablesValues().get(i) + "\r\n";
		}	
		}
		catch(JsonMappingException e1)
		{
			if(GUIMode)
				JOptionPane.showMessageDialog(null, "Нарушена структура JSON файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
		 	else
		 		System.out.println("Нарушена структура JSON файла");
			System.exit(0);
		}
		catch(JsonProcessingException e2)
		{
			if(GUIMode)
				JOptionPane.showMessageDialog(null, "Не удалось считать данные с JSON файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
		 	else
		 		System.out.println("Не удалось считать данные с JSON файла");
			System.exit(0);
		}
		return out;
	}
}