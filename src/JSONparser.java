import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONparser 
{
	public static String parse(String json) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		Expression data = objectMapper.readValue(json, Expression.class);
		String out = data.getExpression() + "\r\n";
		for (int i = 0; i < data.getVariablesNames().size(); i++)
		{
			out += data.getVariablesNames().get(i) + " = " + data.getVariablesValues().get(i) + "\r\n";
		}	
		return out;
	}
}