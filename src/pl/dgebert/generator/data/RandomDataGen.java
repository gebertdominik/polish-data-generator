package pl.dgebert.generator.data;

import java.util.GregorianCalendar;

public class RandomDataGen {




	public GregorianCalendar getRandomDate(int startYear, int stopYear){
		GregorianCalendar gc = new GregorianCalendar();
		int year = randBetween(startYear, stopYear);
		gc.set(gc.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
		gc.set(gc.DAY_OF_YEAR, dayOfYear);
		
		return gc;

	}
	
	public GregorianCalendar getRandomDate(){
		GregorianCalendar gc = new GregorianCalendar();
		return getRandomDate(1900, gc.get(gc.YEAR));
	}

	public int randBetween(int start, int end) {
		return start + (int)Math.round(Math.random() * (end - start));
	}
}