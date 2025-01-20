public class brackets {	
	public static String findAndParseBr(String str)
	{
		int firstbracket = str.length();
		for (int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i) == '(')
			{
				firstbracket = i;
				break;
			}
		}
		int numOFopenBR = 1;
		int lastbracket = 0;
		for (int i = firstbracket + 1; i < str.length(); i++)
		{
			if(str.charAt(i) == ')')
			{
				lastbracket = i;
				if(numOFopenBR == 1)
					break;
				else
					numOFopenBR--;
			}
			if(str.charAt(i) == '(')
			{
				numOFopenBR++;
			}
		}
		String toparse = str.substring(firstbracket + 1, lastbracket);
		while(toparse.indexOf("(") != -1)
		{
			toparse = findAndParseBr(toparse + ".\n" +  str.substring(str.indexOf("\n") + 1));
		}
		str = str.substring(0, firstbracket) + director.parse(toparse + ".\n" +  str.substring(str.indexOf("\n") + 1)) + str.substring(lastbracket + 1);
		return str;		
	}
	
}
