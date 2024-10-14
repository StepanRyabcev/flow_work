public class builder 
{
	private String input;
	private String toparse;
	private String[] vararray;
	private int[] intarray;

	private boolean isParsable(String input) {
	    try {
	    	Integer.parseInt(input);
	        return true;
	    } catch (final NumberFormatException e) {
	        return false;
	    }
	}	
		
	private int operationindex(String str, int n)
	{	
        for (int i = n - 1; i >= 0; i--) {
            char currentChar = str.charAt(i);
            if (currentChar == '-' || currentChar == '+' || currentChar == '*' || currentChar == '/') {
                return i;
            }
        }
  
	        return 0;
	}

	private int operationindexAft(String str, int n)
	{
        for (int i = n + 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (currentChar == '-' || currentChar == '+' || currentChar == '*' || currentChar == '/') {
                return i;
            }
        }

        return str.length();
	}
	
	public builder(String in)
	{
		input = in;
	}
	public void removespaces()
	{
		input = input.trim();
		input = input.replaceAll(" ", "");
	}
	
	void divide()
	{
		//System.out.println(input + " =");
		if (input.indexOf("\n") > 0)
			toparse = input.substring(0, input.indexOf("\n") - 1);
		else
			toparse = input;
		//System.out.println(toparse + " =");
		int n = input.length() - input.replace(String.valueOf("="), "").length();
		vararray = new String[n];
		intarray = new int[n];
		String temp = input.substring(input.indexOf("\n") + 1);
		for (int i = 0; i < n; i++)
		{
			int tocut = temp.indexOf("\n") - 1;
			String cur;
			if (tocut > 0)
			{
				cur = temp.substring(0, temp.indexOf("\n") - 1);
			}
			else
			{
				cur = temp;
			}
			temp = temp.substring(temp.indexOf("\n") + 1);
			vararray[i] = cur.substring(0, cur.indexOf("=")); 
			intarray[i] = Integer.parseInt(cur.substring(cur.indexOf("=") + 1));
		}
	}
	
	public void findoperation(String oper)
	{
		int n = toparse.indexOf(oper);
		String var1;
		if (operationindex(toparse, n) > 0)	
			var1 = toparse.substring(operationindex(toparse, n) + 1, n);
		else
			var1 = toparse.substring(0, n);
		String var2 = toparse.substring(n + 1, operationindexAft(toparse, n));
		//System.out.println(var1 + " | " + var2 + " == " + oper);
		String torepl = parse(var1, var2, oper);
		if (operationindex(toparse, n) != 0)
			toparse = toparse.substring(0, operationindex(toparse, n) + 1) + torepl + toparse.substring(operationindexAft(toparse, n));
		else
			toparse = torepl + toparse.substring(operationindexAft(toparse, n));
	}
	
	private String parse(String v1, String v2, String o)
	{
		int vv1 = 0, vv2 = 0;
		if (isParsable(v1)) {
			vv1 = Integer.parseInt(v1);
		}
		else
		{	
		//System.out.println(v1 + " | " + v2);
		for (int i = 0; i < vararray.length; i++)
		{
			if (v1.equalsIgnoreCase(vararray[i]))
			{
				vv1 = intarray[i];
			}
		}
		}			
		
		//System.out.println(v1 + " = " + vv1);
		
		if (isParsable(v2))
		{
			vv2 = Integer.parseInt(v2);
		}
		else
		{
		for (int i = 0; i < vararray.length; i++)	
		{
			if (v2.equalsIgnoreCase(vararray[i]))
			{
				vv2 = intarray[i];
			}
		}
		}
		
		//System.out.println(vv1 + " | " + vv2);
			
		if (o == "+")
		{
			return String.valueOf(vv1 + vv2);
		}
		if (o == "/")
		{
			return String.valueOf((int)(vv1 / vv2));
		}
		if (o == "*")	
		{
			return String.valueOf(vv1 * vv2);
		}
		if (o == "-")
		{
			return String.valueOf(vv1 - vv2);
		}
		return null;
	}

	public boolean ifsumexist(String s)
	{
		if ((s == "-") && (toparse.charAt(0) == '-'))
		{
			return toparse.lastIndexOf("-") > 0;
		}
		return toparse.indexOf(s) >= 0;
	}
	public void parseString()
	{
		while((toparse.indexOf("+") > 0) || (toparse.lastIndexOf("-") > 0))
		{
		for (int i = 1; i < toparse.length(); i++)
		{
			if((toparse.charAt(i) == '+') || (toparse.charAt(i) == '-'))
			{
				String var1;
				if (operationindex(toparse, i) > 0)	
					var1 = toparse.substring(operationindex(toparse, i) + 1, i);
				else
					var1 = toparse.substring(0, i);
				String var2 = toparse.substring(i + 1, operationindexAft(toparse, i));
				String parsed;
				//System.out.println(var1 + " | " + var2 + " --- " + toparse);
				if (toparse.charAt(i) == '+')
					parsed = parse(var1, var2, "+");
				else
					parsed = parse(var1, var2, "-");
				toparse = parsed + toparse.substring(operationindexAft(toparse, i));
				//System.out.println(toparse);
				break;
			}
				
		}
		}
	}
	
	public String getresult()
	{
		return toparse;
	}
	
	public String getinput()
	{
		return input;
	}
}	

