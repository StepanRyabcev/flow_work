import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException 
	{
		enpryptionOptions op = new enpryptionOptions();
		reader rd = new reader(op);
		String out = rd.read();
		while(out.indexOf("(") >= 0)
			out = brackets.findAndParseBr(out);
		String result = director.parse(out);
		System.out.println(result);
		write wr = new write(op);
		wr.save(result);
	}
}