package pl.dgebert.generator.data;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;

import com.sun.javafx.css.CalculatedValue;

public class Pesel implements Document {

	@SuppressWarnings("static-access")
	@Override
	public String generate() {
		String pesel = "";
		RandomDataGen rdg = new RandomDataGen();
		DecimalFormat df= new DecimalFormat("00");
		
		GregorianCalendar gc = rdg.getRandomDate();
		
		int year = gc.get(gc.YEAR);
		int month = gc.get(gc.MONTH) + 1;
		int dayOfMonth = gc.get(gc.DAY_OF_MONTH);
		
		System.out.println( year + " " + month + " " + dayOfMonth);
		try {
			pesel = String.valueOf(df.format(year%100));
			pesel += getMonthForPesel(year, month);
			pesel += df.format(Double.valueOf(dayOfMonth));
			pesel += getSeriesNumberForPesel();
			pesel += getSexNumberForPesel();
			pesel += String.valueOf(calculateControlNumber(pesel));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pesel;
	}

	private int calculateControlNumber(String pesel) {
		System.out.println( "pesel to Contro number: " + pesel);
		char[] p = pesel.toCharArray();
		Integer sum =  Integer.valueOf(String.valueOf(p[0])) * 1
			+  Integer.valueOf(String.valueOf(p[1])) * 3
			+  Integer.valueOf(String.valueOf(p[2])) * 7
			+  Integer.valueOf(String.valueOf(p[3])) * 9
			+  Integer.valueOf(String.valueOf(p[4])) * 1
			+  Integer.valueOf(String.valueOf(p[5])) * 3
			+  Integer.valueOf(String.valueOf(p[6])) * 7
			+  Integer.valueOf(String.valueOf(p[7])) * 9
			+  Integer.valueOf(String.valueOf(p[8])) * 1
			+  Integer.valueOf(String.valueOf(p[9])) * 3;
		
		int result = sum%10;
		result = 10 - result;
		return result%10;
				
	}

	@Override
	public boolean validate(String number) {
		char[] p = number.toCharArray();

		Integer controlNumber = calculateControlNumber(number);
		
		if(controlNumber.equals(Integer.valueOf(String.valueOf(p[10])))){
			return true;
		}
		
		return false;
	}
	
	private String getMonthForPesel(int year, int month) throws Exception{
		
		
		if(year < 1800){
			throw new Exception(); //TODO add Custom exception
		}else if(year >= 1800 && year < 1900){
			return String.valueOf(month + 80);
		}else if(year >= 1900 && year < 2000){
			DecimalFormat mFormat= new DecimalFormat("00");
			return mFormat.format(Double.valueOf(month));
		}else if(year >= 2000 && year < 2100){
			return String.valueOf(month + 20);
		}else if(year >= 2100 && year < 2200){
			return String.valueOf(month + 40);
		}else if(year >= 2200 && year < 2300){
			return String.valueOf(month + 60);
		}else{
			throw new Exception();
		}
	}

	private String getSeriesNumberForPesel(){
		RandomDataGen rdg = new RandomDataGen();
		DecimalFormat df = new DecimalFormat("000");
		String result = df.format(rdg.randBetween(0, 999));
		System.out.println("series:" + result);
		return result;
	}
	//TODO Already just a one digit - add sex enum as param and workflow based on it
	private String getSexNumberForPesel(){
		RandomDataGen rdg = new RandomDataGen();
		String result = String.valueOf(rdg.randBetween(0, 9));
		System.out.println("sex: " + result);
		return result;
	}
}
