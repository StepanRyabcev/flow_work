import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import org.yaml.snakeyaml.Yaml;

public class YAMLparser {
	
	static boolean GUIMode = true;
	
	public static String parse(String textToParse)
	{
		String out = "";
		try
		{
		Yaml yaml = new Yaml();	
		Map<String, Object> obj = yaml.load(textToParse);
		@SuppressWarnings("unchecked")
		ArrayList<String> VariablesNames = (ArrayList<String>) obj.get("VariablesNames");
		@SuppressWarnings("unchecked")
		ArrayList<Integer> VariablesValues = (ArrayList<Integer>) obj.get("VariablesValues");
		out = obj.get("Expression").toString() + "\r\n";
		for (int i = 0; i < VariablesNames.size(); i++)
		{
			out += VariablesNames.get(i) + " = " + VariablesValues.get(i) + "\r\n";
		}
		}
		catch(Exception e)
		{
			if(GUIMode)
				JOptionPane.showMessageDialog(null, "Ошибка разбора YAML", "Ошибка", JOptionPane.ERROR_MESSAGE);
		 	else
			System.out.println("Ошибка разбора YAML");
			System.exit(0);
		}
		return out;
	}
}