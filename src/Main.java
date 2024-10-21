import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		
		reader rd = new reader();
		String out = rd.read();
		while(out.indexOf("(") >= 0)
			out = brackets.findAndParseBr(out);
		String result = director.parse(out);
		System.out.println(result);
		write wr = new write();
		wr.save(result);
	}
}