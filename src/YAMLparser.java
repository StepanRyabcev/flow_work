import java.util.ArrayList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YAMLparser {
	public static String parse(String textToParse)
	{
		Yaml yaml = new Yaml();	
		Map<String, Object> obj = yaml.load(textToParse);
		@SuppressWarnings("unchecked")
		ArrayList<String> VariablesNames = (ArrayList<String>) obj.get("VariablesNames");
		@SuppressWarnings("unchecked")
		ArrayList<Integer> VariablesValues = (ArrayList<Integer>) obj.get("VariablesValues");
		String out = obj.get("Expression").toString() + "\r\n";
		for (int i = 0; i < VariablesNames.size(); i++)
		{
			out += VariablesNames.get(i) + " = " + VariablesValues.get(i) + "\r\n";
		}
		return out;
	}
}