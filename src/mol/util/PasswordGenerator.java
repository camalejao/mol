package mol.util;

import java.util.Random;

public class PasswordGenerator {

	public static String generatePassword(int length) {
		String result = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result += Integer.toString(random.nextInt(10));
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.print("Informe a quantidade de caracteres: ");
		//int quant = Integer.parseInt(Debug.readCommand());
		System.out.println("Senha gerada... " + PasswordGenerator.generatePassword(6));
		
	}
	
}
