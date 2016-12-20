package pl.dgebert.generator.data;

import java.util.Random;

public class IdentityCard implements Document {
	
	public static int numberWeights[] = {7,3,1,7,3};
	public static int letterWeights[] = {7,3,1};
	private static char firstLetter = 'A';
	private static char lastLetter = 'Z';
	@Override
	public String generate() {
		String seria = "";
		String numer = "";
		Random random = new Random();
		int sum = 0;

		for(int i = 0; i < 3; i++){
			seria += (char)(random.nextInt(lastLetter-firstLetter) + firstLetter);
			sum += letterWeights[i] * (seria.charAt(i)- firstLetter + 10);
		}
		
		for(int i = 0; i < 5; i++){
			numer += (char)(random.nextInt('9' - '0') + '0');
			sum += numberWeights[i] * (numer.charAt(i) - '0');
		}

		return seria + sum%10 + numer;	
	}

	@Override
	public boolean validate(String number) {
		char[] charArray = number.toCharArray();
		int controlNumber = Integer.valueOf(String.valueOf(charArray[3]));
		int sum = 0;
		for (int i = 0; i < charArray.length; i++) {
			if(i < 3){
				System.out.println(letterWeights[i] + " " + (int)(charArray[i] - 55));
				sum += letterWeights[i] * (charArray[i] - 55);	
			}else if(i==3){
				continue;
			}else{
				System.out.println(numberWeights[i -4] + " " + (int)charArray[i]);
				sum += numberWeights[i -4] * Integer.valueOf(String.valueOf(charArray[i]));
			}
			System.out.println("sum = " + sum);
		}
		
		System.out.println("cn" + controlNumber);
		System.out.println(sum % 10);
		if( controlNumber == (sum % 10)){
			return true;
		}
		
		
		return false;
	}

}
