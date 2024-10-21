import static org.junit.jupiter.api.Assertions.*;

class TestBuilder {

	private String generatetext()
	{
		String str = "";
		int n = 10 + (int) ( Math.random() * 100 );
		for (int i = 0; i <= n; i++)
		{
			str += String.valueOf((char)(48 + (int) ( Math.random() * 74))); 
		}
		return str;
	}
	
	private String insertspaces(String str)
	{
		int n = 10 + (int) ( Math.random() * 100 );
		for (int i = 0; i <= n; i++)
		{
			int position = (int) ( Math.random() * (str.length() - 2));
			int numofspaces = (int) ( Math.random() * 5);
			String toInsert = " ";
			for (int j = 0; j < numofspaces; j++)
			{
				toInsert += " "; 
			}
			str = str.substring(0, position) + toInsert + str.substring(position);
		}
		return str;
	}
	
	private String insertoperation(String str, String oper)
	{
		int position = 3 + (int) ( Math.random() * (str.length() - 2));
		str = str.substring(0, position) + oper + str.substring(position);
		return str;
	}
	
	@org.junit.jupiter.api.Test
	void testBuilder() {
		for (int i = 0; i <= 100; i++)
		{
			String expected = generatetext();
			builder bd = new builder(expected);
			assertEquals(bd.getinput(), expected);
		}
	}

	@org.junit.jupiter.api.Test
	void testRemovespaces() {
		for (int i = 0; i <= 100; i++)
		{
			String expected = generatetext();
			String input = insertspaces(expected);
			builder bd = new builder(input);
			bd.removespaces();
			generatetext();
			assertEquals(bd.getinput(), expected);
		}
	}

	@org.junit.jupiter.api.Test
	void testFindoperation() throws Exception {
		String input = "3 * 4";
		String expected = "12";
		builder bd = new builder(input);
		bd.removespaces();
		bd.divide();
		bd.findoperation("*");
		assertEquals(bd.getresult(), expected);
		
		input = "14 / 7";
		expected = "2";
		builder bd1 = new builder(input);
		bd1.removespaces();
		bd1.divide();
		bd1.findoperation("/");
		assertEquals(bd1.getresult(), expected);
	}

	@org.junit.jupiter.api.Test
	void testIfsumexist() throws Exception {

			boolean expected = true;
			String input = "3 + 4";
			builder bd = new builder(input);
			bd.removespaces();
			bd.divide();
			System.out.println(input + "\n" + expected + " " + bd.ifsumexist("+"));
			assertEquals(bd.ifsumexist("+"), expected);
			
			
		
		input = "3 - 4";
		expected = true;
		builder bd1 = new builder(input);
		bd1.removespaces();
		bd1.divide();
		assertEquals(bd1.ifsumexist("-"), expected);
		
		input = "-3 + 4";
		expected = false;
		builder bd2 = new builder(input);
		bd2.removespaces();
		bd2.divide();
		assertEquals(bd2.ifsumexist("-"), expected);
		
		input = "3 * 4";
		expected = true;
		builder bd3 = new builder(input);
		bd3.removespaces();
		bd3.divide();
		assertEquals(bd3.ifsumexist("*"), expected);
		
		input = "12 / 4";
		expected = true;
		builder bd4 = new builder(input);
		bd4.removespaces();
		bd4.divide();
		assertEquals(bd4.ifsumexist("/"), expected);
	}

	@org.junit.jupiter.api.Test
	void testParseString() throws Exception {
		String input = "-1 + 3 + 4 - 1 + 4 - 4";
		String expected = "5";
		builder bd = new builder(input);
		bd.removespaces();
		bd.divide();
		bd.parseString();
		assertEquals(bd.getresult(), expected);
	}

}