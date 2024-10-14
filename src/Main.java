import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		reader rd = new reader();
		String out = rd.read();
		
		System.out.println("Зашифрован ли файл");
		boolean selected = false;
		Scanner in1 = new Scanner(System.in);
		selected = in1.nextBoolean();
        if (selected == true)
        {
        	System.out.println("Введите ключ");
        	try (Scanner in2 = new Scanner(System.in)) {
				String key = in2.nextLine();
				try {
					out = crypto.decrypt(out, key);
				} catch (Exception e) {	
					e.printStackTrace();
				}
			}
        }
		while(out.indexOf("(") >= 0)
			out = brackets.findAndParseBr(out);
		String result = director.parse(out);
		System.out.println(result);
		System.out.println("Надо ли зашифровать файл");
		selected = in1.nextBoolean();
		if (selected == true)
		{
        	System.out.println("Введите ключ");
        	try (Scanner in2 = new Scanner(System.in)) {
				String key = in2.nextLine();
				try {
					result = crypto.encrypt(result, key);
				} catch (Exception e) {	
					e.printStackTrace();
				}
			}
		}
		write wr = new write();
		wr.save(result);
		in1.close();
	}
}	