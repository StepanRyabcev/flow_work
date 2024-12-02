import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class crypto {
	public static String encrypt(String input, String key) throws Exception
	{
		 SecretKeySpec secretKey = getKey(key);
	     Cipher cipher = Cipher.getInstance("AES");
	     cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	     byte[] encryptedBytes = cipher.doFinal(input.getBytes());
	     return Base64.getEncoder().encodeToString(encryptedBytes);
	}
	
	public static String decrypt(String input, String key) throws Exception
	{
		SecretKeySpec secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(input));
        return new String(decryptedBytes);
	}
	
	private static SecretKeySpec getKey(String key) throws Exception {	
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(key.getBytes("UTF-8"));
        return new SecretKeySpec(keyBytes, "AES");
    }
	
	public static String decryptIfNeeded(String input, enpryptionOptions op)
	{
  		boolean selected = op.isNeedtoDecrypt();
         if (selected == true)
         {
 				String key = op.DecryptionKey();
 				try {
 					input = decrypt(input, key);
 				} 
 				catch(BadPaddingException ex)
 				{
 					System.out.println("Неверный ключ для дешифовки");
 					System.exit(0);
 				}
 				catch(IllegalArgumentException ex1)
 				{
 					System.out.println("Ошибка дешифровки. Вероятно файл не был зашифрован");
 					System.exit(0);
 				}
 				catch (Exception e) {	
 					System.out.println("Ошибка дешифровки");
 					System.exit(0);
 				} 			
         }
		return input;
	}
}
