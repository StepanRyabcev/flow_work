
public class director {
	
	public static String parse(String input)
	{
		builder bd = new builder(input);
		bd.removespaces();
		bd.divide();
		while(bd.ifsumexist("*"))
		{
			bd.findoperation("*");
		} 
		while(bd.ifsumexist("/"))
		{
			bd.findoperation("/");
		} 
		bd.parseString();
		return bd.getresult();
	}
	
}
