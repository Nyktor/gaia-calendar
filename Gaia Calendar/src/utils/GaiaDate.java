package utils;

import java.time.LocalDate;
import java.time.Month;

public class GaiaDate {
	
	private int day;
	private Sabbath sab;
	private int year;
	public Eneastra ene;
	
	/* CONSTRUCTORS */
	public GaiaDate(int day, Sabbath sab, int year) {
		this.setDay(day);
		this.setSabbath(sab);
		this.setYear(year);
	}
	public GaiaDate(int day, int sab, int year) {
		this.setDay(day);
		this.setSabbath(Sabbath.getSabbathById(sab, GaiaDate.isLeapYear(year)));
		this.setYear(year);
	}
	public GaiaDate() {
		this.setDay(1);
		this.setSabbath(Sabbath.Yule());
		this.setYear(0);
	}

	/* SETTERS AND GETTERS */
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Sabbath getSabbath() {
		return sab;
	}

	public void setSabbath(Sabbath sab) {
		this.sab = sab;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	//RETURNS the GDATE of the FIRST DAY of that sabbath
	public GaiaDate getFirstDayOfSabbath() {
		GaiaDate firstDay = new GaiaDate();
		firstDay.setDay(1);
		firstDay.setSabbath(this.getSabbath());
		firstDay.setYear(this.getYear());
		return firstDay;
	}
	
	//FIRST DAY OF THE GAIA CALENDAR
	public static final GaiaDate firstGaianDay() {
		int ISODay = 21;
		int ISOMonth = 12;
		int ISOYear = -10001;
				
		return GaiaDate.toGaiaDate(ISODay, ISOMonth, ISOYear);
	}
	
	//CHECK if the Gaian Date is a LEAP year
	public static boolean isLeapYear(GaiaDate gDate) {
		if(gDate.getYear() % 4 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isLeapYear() {
		if(this.getYear() % 4 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isLeapYear(int year) {
		if(year % 4 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//GET TODAY'S GAIA DATE
	public static final GaiaDate today() {
		return toGaiaDate(LocalDate.now());
	}
	
	/* ENEASTRA DAY GETTERS */
	public static Eneastra getEneastraDay(GaiaDate gDate) { //BY GAIA DATE
		
		int count = -1; //initialize variable
		
		count += (gDate.getYear()*365 + (gDate.getYear()/4) ); //add past year's days
		
		Sabbath sab = gDate.getSabbath();
		
		//add days for each sabbath before the desired one
		for(int i = 1; i < sab.id ; i++) {
			count += Sabbath.getSabbathById(i, gDate.isLeapYear()).days;
		}
		
		count += gDate.getDay(); //add current days
				
		//Obtain the id of the eneastra
		Eneastra ene = Eneastra.getDayByIndex((count) % 9); //ADD 2 SO IT ALL STARTS ON SUNGAIA
		
		return ene;
	}
	
	public Eneastra getEneastraDay() { //WITHOUT PARAMETERS
		
		return GaiaDate.getEneastraDay(this);
		
	}
	
	public static Eneastra getEneastraDay(int day, int month, int year) { //BY DAY MONTH(int) AND YEAR
				
		GaiaDate date = GaiaDate.toGaiaDate(day, month, year);
		Eneastra ene = date.getEneastraDay();
		
		return ene;
	}
	
	public static Eneastra getEneastraDay(int day, Month month, int year) { //BY DAY MONTH(enum) AND YEAR

		GaiaDate date = GaiaDate.toGaiaDate(day, month, year);
		Eneastra ene = GaiaDate.getEneastraDay(date);
		
		return ene;
	}
	
	public static Eneastra getEneastraDay(LocalDate date) { //BY LOCALDATE

		GaiaDate fecha = GaiaDate.toGaiaDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
		Eneastra ene = GaiaDate.getEneastraDay(fecha);
		return ene;
	}
	
	
	/* TO GAIA DATE CONVERSORS*/
	public static GaiaDate toGaiaDate(int initialDay, int initialMonth, int initialYear) {
		boolean leapYear;
		
		int gaiaDay = 0;
		Sabbath sabbath = null;
		int humanYear = 0;
		
		//Calculate if the year is a leap year
		if(initialYear % 4 == 0) {
			leapYear = true;
		}else {
			leapYear = false;
		}
		
		//EXCEPTION HANDLERS
		/* IF THE DAYS SURPASS THE MONTH'S MAX LENGTH      OR  IS FEBRUARY, NOT A LEAP YEAR AND DAYS ARE OVER 28*/
		if(initialDay > Month.of(initialMonth).maxLength() || Month.of(initialMonth).equals(Month.FEBRUARY) && !leapYear && initialDay > 28) {
			throw new ArithmeticException("ERROR: invalid DAY (too high)");
			
		/* IF THE DAY IS LOWER THAN 1*/
		}else if(initialDay < 1) {
			throw new ArithmeticException("ERROR: invalid DAY (too low)");
		}
		
		//Calculate human year
		humanYear = initialYear + 10000;
		if(initialMonth == 12 && initialDay > 20) {
			humanYear++;
		}
		
		
		//Determine SABBATH and DAY by INITIAL MONTH
		switch(initialMonth) {
		
		//JANUARY, always YULE
		case 1:
			sabbath = Sabbath.Yule();
			gaiaDay = 11 + initialDay;
			break;
			
		//FEBRUARY, always IMBOLC
		case 2:
			sabbath = Sabbath.Imbolc(leapYear);
			gaiaDay = initialDay;
			break;
		
		//MARCH, could be IMBOLC or OSTARA
		case 3:
			if(initialDay < Sabbath.Ostara().firstDay) {
				sabbath = Sabbath.Imbolc(leapYear);
				gaiaDay = Month.FEBRUARY.length(leapYear) + initialDay;
			}else {
				sabbath = Sabbath.Ostara();
				gaiaDay = initialDay - 20;
			}
			break;
			
		//APRIL, always OSTARA
		case 4:
			sabbath = Sabbath.Ostara();
			gaiaDay = 11 + initialDay;
			break;
		
		//MAY, always BELTANE
		case 5:
			sabbath = Sabbath.Beltane();
			gaiaDay = initialDay;
			break;
			
		//JUNE, could be BELTANE or LITHA
		case 6:
			if(initialDay < Sabbath.Litha().firstDay) {
				sabbath = Sabbath.Beltane();
				gaiaDay = Month.MAY.length(leapYear) + initialDay;
			}else {
				sabbath = Sabbath.Litha();
				gaiaDay = initialDay - 20;
			}
			break;
			
		//JULY, always LITHA
		case 7:
			sabbath = Sabbath.Litha();
			gaiaDay = 10 + initialDay;
			break;
		
		//AUGUST, always LUGHNASADH
		case 8:
			sabbath = Sabbath.Lughnasadh();
			gaiaDay = initialDay;
			break;
		
		//SEPTEMBER, could be LUGHNASADH or MABON
		case 9:
			if(initialDay < Sabbath.Mabon().firstDay) {
				sabbath = Sabbath.Lughnasadh();
				gaiaDay = Month.AUGUST.length(leapYear) + initialDay;
			}else {
				sabbath = Sabbath.Mabon();
				gaiaDay = initialDay - 20;
			}
			break;
		
		//OCTOBER, always MABON
		case 10:
			sabbath = Sabbath.Mabon();
			gaiaDay = 10 + initialDay;
			break;
			
		//NOVEMBER, always SAMHAIN
		case 11:
			sabbath = Sabbath.Samhain();
			gaiaDay = initialDay;
			break;
		
		//DECEMBER, could be SAMHAIN or YULE
		case 12:
			if(initialDay < Sabbath.Yule().firstDay) {
				sabbath = Sabbath.Samhain();
				gaiaDay = Month.NOVEMBER.length(leapYear) + initialDay;
			}else {
				sabbath = Sabbath.Yule();
				gaiaDay = initialDay - 20;
			}
			break;
		
		//DEFAULT
		default:
			break;
		}
		
		GaiaDate gdate = new GaiaDate(gaiaDay, sabbath, humanYear);
		return gdate;
	}

	public static GaiaDate toGaiaDate(int initialDay, Month initialMonth, int initialYear) {
		
		return toGaiaDate(initialDay, initialMonth.getValue(), initialYear);
	}

	public static GaiaDate toGaiaDate(LocalDate date) {
		int initialDay = date.getDayOfMonth();
		int initialMonth = date.getMonthValue();
		int initialYear = date.getYear();
		return toGaiaDate(initialDay, initialMonth, initialYear);
	}

	
	/* TO NORMIE'S LOCALDATE REVERSORS*/
	public static LocalDate toLocalDate(GaiaDate gDate) {
			
			int ISOday = 0;
			Month ISOmonth = null;
			int ISOyear = 0;
			
			int gaiaDay = gDate.getDay();
			Sabbath sabbath = gDate.getSabbath();
			int humanYear = gDate.getYear();
			int limitDay = sabbath.limitDay;
			
			//Calculate ISO year
			ISOyear = humanYear - 10000;
			if(sabbath.equals(Sabbath.Yule()) && gaiaDay < 11) {
				ISOyear--;
			}
			
			
			//Determine the MONTH depending on the SABBATH
			if(sabbath.firstDay == 1) {
				if(gaiaDay <= limitDay) {
					ISOday = gaiaDay;
					ISOmonth = sabbath.firstMonth;
				}else {
					ISOmonth = sabbath.lastMonth;
					ISOday = gaiaDay - sabbath.firstMonth.maxLength();
				}
			}else {
				if(gaiaDay <= limitDay) {
					ISOmonth = sabbath.firstMonth;
					ISOday = ISOmonth.maxLength() - (limitDay - gaiaDay);
				}else {
					ISOmonth = sabbath.lastMonth;
					ISOday = gaiaDay - limitDay;
				}
			}
			
			return LocalDate.of(ISOyear, ISOmonth, ISOday);
	}
	
	public LocalDate toLocalDate() {
		
		return GaiaDate.toLocalDate(this);
		
	}
	
	public static LocalDate toLocalDate(int gaiaDay, Sabbath sab, int humanYear) {
		
		GaiaDate gdate = new GaiaDate(gaiaDay, sab, humanYear);
		return gdate.toLocalDate();
		
	}
}
