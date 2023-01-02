package utils;

import java.time.Month;

public final class Sabbath {
	
	/*----------------------------------------------------------------------------------*/
	public int id;  //IDENTIFIER NUMBER, 1 TO 8
	
	public int days; //NUMBER OF DAYS OF THE SABBATH
	
	public int firstDay; //FIRST DAY (num) OF THE SABBATH IN THE LOCALDATE CALENDAR
	
	public Month firstMonth; // BEGINNING MONTH OF THE SABBATH IN THE LOCALDATE CALENDAR
	
	public int lastDay; //LAST DAY (num) OF THE SABBATH IN THE LOCALDATE CALENDAR
	
	public Month lastMonth; //ENDING MONTH OF THE SABBATH IN THE LOCALDATE CALENDAR
	
	public int limitDay; //DAY EQUIVALENT TO THE SECOND MONTH'S FIRST DAY
	
	public String name; //NAME OF THE SABBATH IN A STRING, FANCY
	
	public SabEnum sabEnum; //CORRESPONDING ENUM, MIGHT BE USEFUL
	/*----------------------------------------------------------------------------------*/
	
	
	/* CONSTRUCTORS */
	public Sabbath() {
		this.id = 0;
		this.days = 0;
		this.firstDay = 0;
		this.firstMonth = null;
		this.lastDay = 0;
		this.lastMonth = null;
		this.limitDay = 0;
		this.name = "";
		this.sabEnum = null;
	}
	
	public Sabbath(int id, int days, int startDay, Month startMonth, int lastDay, Month lastMonth, int limitDay, String name, SabEnum sEnum) {
		this.id = id;
		this.days = days;
		this.firstDay = startDay;
		this.firstMonth = startMonth;
		this.lastDay = lastDay;
		this.lastMonth = lastMonth;
		this.limitDay = limitDay;
		this.name = name;
		this.sabEnum = sEnum;
	}

	
	/* LIST OF SABBATHS */
	public static final Sabbath Yule(){
		
		//YULE: 21 DECEMBER to 31 JANUARY
		final int id = 1;
		final int days = 42;
		final int firstDay = 21;
		final Month firstMonth = Month.DECEMBER;
		final int lastDay = 31;
		final Month lastMonth = Month.JANUARY;
		final String name = "Yule";
		final SabEnum senum = SabEnum.YULE;
		final int limitDay = 11;
		return new Sabbath(id, days, firstDay, firstMonth, lastDay, lastMonth, limitDay, name, senum);
	}
	
	public static final Sabbath Imbolc(boolean leapYear){
		
		//IMBOLC: 1 FEBRUARY to 20 MARCH
		final int id = 2;
		final int firstDay = 1;
		final Month firstMonth = Month.FEBRUARY;
		final int lastDay = 20;
		final Month lastMonth = Month.MARCH;
		final String name = "Imbolc";
		final int days; 
		final int limitDay;
		final SabEnum senum = SabEnum.IMBOLC;
		
		if(leapYear) {
				days = 49;
				limitDay = 30;
			}else {
				days = 48;
				limitDay = 29;
			};
		return new Sabbath(id, days, firstDay, firstMonth, lastDay, lastMonth, limitDay, name, senum);
	}
	
	public static final Sabbath Ostara(){

		//OSTARA: 21 MARCH to 30 APRIL
		final int id = 3;
		final int days = 41;
		final int firstDay = 21;
		final Month firstMonth = Month.MARCH;
		final int lastDay = 30;
		final Month lastMonth = Month.APRIL;
		final String name = "Ostara";
		final SabEnum senum = SabEnum.OSTARA;
		final int limitDay = 10;
		return new Sabbath(id, days, firstDay, firstMonth, lastDay, lastMonth, limitDay, name, senum);

	}

	public static final Sabbath Beltane(){
		
		//BELTANE: 1 MAY to 20 JUNE
		final int id = 4;
		final int days = 51;
		final int firstDay = 1;
		final Month firstMonth = Month.MAY;
		final int lastDay = 20;
		final Month lastMonth = Month.JUNE;
		final String name = "Beltane";
		final SabEnum senum = SabEnum.BELTANE;
		final int limitDay = 31;
		return new Sabbath(id, days, firstDay, firstMonth, lastDay, lastMonth, limitDay, name, senum);

	}

	public static final Sabbath Litha() {

		//LITHA: 21 JUNE to 31 JULY
		final int id = 5;
		final int days = 41;
		final int firstDay = 21;
		final Month firstMonth = Month.JUNE;
		final int lastDay = 31;
		final Month lastMonth = Month.JULY;
		final String name = "Litha";
		final SabEnum senum = SabEnum.LITHA;
		final int limitDay = 10;
		return new Sabbath(id, days, firstDay, firstMonth, lastDay, lastMonth, limitDay, name, senum);
		
	}

	public static final Sabbath Lughnasadh(){
		
		//LUGHNASADH: 1 AUGUST to 20 SEPTEMBER
		final int id = 6;
		final int days = 51;
		final int firstDay = 1;
		final Month firstMonth = Month.AUGUST;
		final int lastDay = 20;
		final Month lastMonth = Month.SEPTEMBER;
		final String name = "Lughnasadh";
		final SabEnum senum = SabEnum.LUGHNASADH;
		final int limitDay = 31;
		return new Sabbath(id, days, firstDay, firstMonth, lastDay, lastMonth, limitDay, name, senum);

	}
	
	public static final Sabbath Mabon() {

		//MABON: 21 SEPTEMBER to 31 OCTOBER
		final int id = 7;
		final int days = 41;
		final int firstDay = 21;
		final Month firstMonth = Month.SEPTEMBER;
		final int lastDay = 31;
		final Month lastMonth = Month.OCTOBER;
		final String name = "Mabon";
		final SabEnum senum = SabEnum.MABON;
		final int limitDay = 10;
		return new Sabbath(id, days, firstDay, firstMonth, lastDay, lastMonth, limitDay, name, senum);

	}

	public static final Sabbath Samhain(){

		//SAMHAIN: 1 NOVEMBER to 20 DECEMBER
		final int id = 8;
		final int days = 50;
		final int firstDay = 1;
		final Month firstMonth = Month.NOVEMBER;
		final int lastDay = 20;
		final Month lastMonth = Month.DECEMBER;
		final String name = "Samhain";
		final SabEnum senum = SabEnum.SAMHAIN;
		final int limitDay = 30;
		return new Sabbath(id, days, firstDay, firstMonth, lastDay, lastMonth, limitDay, name, senum);
		
	}

	
	
	//LIST with ALL eight sabbaths
	public static final Sabbath[] sabbathList(boolean leapYear){
		Sabbath[] list = {
			Sabbath.Yule(),
			Sabbath.Imbolc(leapYear),
			Sabbath.Ostara(),
			Sabbath.Beltane(),
			Sabbath.Litha(),
			Sabbath.Lughnasadh(),
			Sabbath.Mabon(),
			Sabbath.Samhain()
		};
		return list;
	}
	
	//GET a SABBATH from its ID
	public static Sabbath getSabbathById(int id, boolean leapYear) {
		for(Sabbath s : sabbathList(leapYear)) {
			if(s.id == id) {
				return s;
			}
		}
		
		return null;
	}
	
	//GET a SABBATH from its NAME
	public static Sabbath getSabbathByName(String name, boolean leapYear) {
		for(Sabbath s : sabbathList(leapYear)) {
			if(s.name.equalsIgnoreCase(name)) {
				return s;
			}
		}
		
		return null;
	}
	
	//RETURN the GDATE of the FIRST DAY of the SABBATH
	public static GaiaDate getFirstDayOfSabbath(GaiaDate gDate) {
		GaiaDate firstDay = new GaiaDate();
		firstDay.setDay(1);
		firstDay.setSabbath(gDate.getSabbath());
		firstDay.setYear(gDate.getYear());
		return firstDay;
	}
	
	//RETURNS if they are the 2 same sabbaths or not
	public boolean equals(Sabbath sab) {
		if(this.id == sab.id) {
			return true;
		}else {
			return false;
		}
	}
	
	//RETURNS sabbath name
	public String firstCapitalName() {
		String inicial = this.name.substring(0, 1);
		String word = inicial+this.name.substring(1).toLowerCase();
		return word;
	}
	
	public enum SabEnum{
		YULE,
		IMBOLC,
		OSTARA,
		BELTANE,
		LITHA,
		LUGHNASADH,
		MABON,
		SAMHAIN
	}
	
	//IDK IF THIS IS USEFUL XD
	/*private static final Sabbath getSabByEnum(SabEnum senum, boolean leapYear) {
		switch(senum) {
		case BELTANE:
			return Sabbath.Beltane();
		case IMBOLC:
			return Sabbath.Imbolc(leapYear);
		case LITHA:
			return Sabbath.Litha();
		case LUGHNASADH:
			return Sabbath.Lughnasadh();
		case MABON:
			return Sabbath.Mabon();
		case OSTARA:
			return Sabbath.Ostara();
		case SAMHAIN:
			return Sabbath.Samhain();
		case YULE:
			return Sabbath.Yule();
		default:
			return null;
		}
	}*/
	
}
