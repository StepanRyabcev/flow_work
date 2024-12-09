import javax.swing.JOptionPane;

public class director {
	static boolean GUIMode = true;
	
	public static String parse(String input)
	{
		builder bd = new builder(input);
		bd.removespaces();
		try {
			try {
			bd.divide();
			}
			catch(Exception e)
			{
				if(e.getMessage() == "Incorrect")
					if(GUIMode)
						JOptionPane.showMessageDialog(null, "Выражение задано некорректно", "Ошибка", JOptionPane.ERROR_MESSAGE);
					else
					System.out.println("Выражение задано некорректно"); 
				System.exit(0);
			}
			while(bd.ifsumexist("*"))
			{
				bd.findoperation("*");
			} 
			while(bd.ifsumexist("/"))
			{
				bd.findoperation("/");
			} 
			bd.parseString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bd.getresult();
	}
	
}
