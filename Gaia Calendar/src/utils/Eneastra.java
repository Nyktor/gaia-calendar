package utils;

public enum Eneastra {
	
	SUNGAIA(0, true),
	MERGAIA(1, false),
	VENGAIA(2, false),
	MARGAIA(3, false),
	JUPGAIA(4, true),
	SATGAIA(5, false),
	URGAIA(6, false),
	NEPGAIA(7, false),
	MOONGAIA(8, true);
	
	private int index;
	private boolean restDay;
	public static final int numberOfDays = 9;
	public static final Eneastra firstDay = Eneastra.SUNGAIA;
	
	private Eneastra(int index, boolean restDay) {
		this.index = index;
		this.restDay = restDay;
	}
	

	public int getIndex() {
		return index;
	}

	public boolean isRestDay() {
		return restDay;
	}
	
	public String firstCapitalName() {
		String inicial = this.toString().substring(0, 1);
		String word = inicial+this.toString().substring(1).toLowerCase();
		return word;
	}
	
	public static Eneastra[] eneastraDayList() {
		Eneastra[] list = {
				SUNGAIA,
				MERGAIA,
				VENGAIA,
				MARGAIA,
				JUPGAIA,
				SATGAIA,
				URGAIA,
				NEPGAIA,
				MOONGAIA
		};
		return list;
	}
	
	public static Eneastra getDayByIndex(int id) {
		for (Eneastra e : Eneastra.eneastraDayList()) {
			if(e.getIndex() == id) {
				return e;
			}
		}
		return null;
	}
	
}
