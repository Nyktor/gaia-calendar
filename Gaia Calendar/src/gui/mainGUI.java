package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utils.Eneastra;
import utils.GaiaDate;
import utils.Sabbath;

public class mainGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // i don't know what the fuck is this, Eclipse told me to add it, i won't ask
	

	/* GLOBAL BOXES + button */
	JComboBox<?> dayBox;
	JComboBox<?> sabbathBox;
	JComboBox<?> yearBox;
	JButton resetButton;
	
	/* GLOBAL CONSTANTS */
	GaiaDate date = GaiaDate.today(); //it starts being today
	
	/* fucking 3 panels */
	JPanel top;
	JPanel center;
	JPanel bot;

	/*----------MAIN METHOD----------*/
	public mainGUI(){
						
		/*----------GENERAL FRAME DATA-----------*/
		
		//copied code
	    BufferedImage image = null;
	    try {
	    	image = ImageIO.read(new File("GaiaCalendarIcon.png"));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    this.setIconImage(image);
		
		this.top = topPanel(date);
		this.center = centerPanel(date);
		this.bot = bottomPanel(date);
		
		this.setTitle("Gaian Calendar");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 400);
		this.setLayout(new BorderLayout());
		
		/* ADD TOPCONTAINER AT THE TOP XD*/
		this.add(top, BorderLayout.NORTH);
		
		/* ADDS THE CALENDAR CONTAINER PANEL AT THE CENTER*/
		this.add(center, BorderLayout.CENTER);
		
		/* ADDS THE DATE EDITOR AT THE BOTTOM */
		this.add(bot, BorderLayout.SOUTH);
		
		//THIS AT THE END
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null); //SETS THE FRAME TO THE CENTER
	}
	
	/*----------TOP PANEL METHOD----------*/
	public JPanel topPanel(GaiaDate date) {
		
		this.date = date;
		
		/*------------TOP PANEL: SHOWS CURRENT GAIAN DATE------------*/
		//CURRENT DATE PANEL
		JPanel currentDatePanel = new JPanel();
		
		//SABBATH LABEL
		JLabel sabLabel = new JLabel();
		sabLabel.setText(date.getSabbath().name.toUpperCase());
		sabLabel.setFont(new Font("Arial", Font.BOLD, 30));
		
		//HUMAN ERA YEAR LABEL
		JLabel yearLabel = new JLabel();
		String stringYear = String.valueOf(date.getYear());
		yearLabel.setText(" - "+stringYear+" HE");
		yearLabel.setFont(new Font("Arial", Font.BOLD, 27));
		
		currentDatePanel.add(sabLabel);
		currentDatePanel.add(yearLabel);
		
			//PANEL THAT CONTAINS EACH DAY'S PANELS
		JPanel eneastraPanel = new JPanel();
		eneastraPanel.setBackground(Color.LIGHT_GRAY);
		eneastraPanel.setLayout(new GridLayout(1,9));
		
			//ENEASTRA DAYS PANELS
		JPanel[] eneastraDaysPanels = {
				
				new JPanel(),
				new JPanel(),
				new JPanel(),
				new JPanel(),
				new JPanel(),
				new JPanel(),
				new JPanel(),
				new JPanel(),
				new JPanel(),
				
		};
		
			//AUTO-FILL FIRST ROW OF CELLS WITH DAY NAMES
		for(int i = 0; i < Eneastra.numberOfDays;i++) {
			JLabel eneLabel = new JLabel();
			eneLabel.setText(Eneastra.getDayByIndex(i).firstCapitalName());
			eneLabel.setFont(new Font("Arial", Font.BOLD, 24));
			eneastraDaysPanels[i].add(eneLabel);
		}
		
		/* ADDS EACH DAY PANEL TO THE ENEASTRA PANEL*/
		for(JPanel j : eneastraDaysPanels) {
			eneastraPanel.add(j);
		}
		
		//TOP container
		JPanel topContainer = new JPanel();
		topContainer.setLayout(new GridLayout(2,1));
		
		//ADD IT TO THE GENERAL CONTAINER
		topContainer.add(currentDatePanel);
		topContainer.add(eneastraPanel);
		
		return topContainer;
	}

	/*----------CENTER PANEL METHOD----------*/
	public JPanel centerPanel(GaiaDate date) {
		
		this.date = date;
		
		/*------------CENTER PANEL: CALENDAR GRID------------*/
		GaiaDate firstDayOfSabbath = Sabbath.getFirstDayOfSabbath(date); //CHANGES date to the first day of the Sabbath
		int cuadraditos; //PANELS
		int gridRows; //ROW CONTAINER PANELS
		int gridColumns = Eneastra.numberOfDays; // = 9
		JPanel calendarContainer = new JPanel(); //CALENDAR CONTAINER PANEL
		
		//GET how many cells are there
		ArrayList<String> cells = new ArrayList<String>();
		int startSabbathEneastraDay = firstDayOfSabbath.getEneastraDay().getIndex(); //INDEX OF THE DAY
		int startingEmptyCells;
		cuadraditos = startSabbathEneastraDay; //ADDS EMPTY CUADRADITOS AT THE BEGINNING
		startingEmptyCells = cuadraditos; //
		
		//ADD blank spaces to the ArrayList
		for(int i = 0; i < startingEmptyCells; i++) {
			cells.add("");
		}
		
		//ADD each day to the ArrayList
		int sabbathDays = firstDayOfSabbath.getSabbath().days;
		cuadraditos += sabbathDays; //ADDS DAYS
		for(int i = 1; i <= sabbathDays; i++) {
			cells.add(String.valueOf(i));
		}
		
		//ADD blank spaces at the end IF it's needed 
		if(!(cuadraditos % 9 == 0)) {
			int endEmptyCells;
			endEmptyCells = (gridColumns - (cuadraditos % gridColumns));
			for(int i = 0; i < endEmptyCells; i++) {
				cells.add("");
			}
			cuadraditos += endEmptyCells; //ADDS EMPTY CUADRADITOS AT THE END
		}
		
		gridRows = cuadraditos/gridColumns; //GETS NUMBER OF ROWS NEEDED FOR THAT SABBATH
		
		//CREATE and FILL each square
		JPanel[][] calendar = new JPanel[gridRows][gridColumns]; //ALL CALENDAR PANELS
		JPanel[] rows = new JPanel[gridRows]; //BIG ROWS PANELS
		for(int i = 0; i < gridRows; i++) {
			
			//ROWS PANELS, each containing days
			JPanel tRowPanel = new JPanel();
			rows[i] = tRowPanel;
			rows[i].setLayout(new GridLayout(1,9));
			rows[i].setAlignmentY(JPanel.CENTER_ALIGNMENT);
			for(int j = 0; j < gridColumns; j++) {
				
				//CONFIGURE EACH PANEL
				calendar[i][j] = new JPanel();
				JLabel etiqueta = new JLabel(""+cells.get(i*9+j));
				etiqueta.setFont(new Font("Arial", Font.PLAIN, 58));
				etiqueta.setVerticalAlignment(JLabel.CENTER);
				calendar[i][j].add(etiqueta);
				calendar[i][j].setAlignmentY(JPanel.CENTER_ALIGNMENT);
				
				//GRAY BACKGROUND FOR RESTING DAYS
				if(j == 0 || j == 4 || j == 8) {
					calendar[i][j].setBackground(Color.LIGHT_GRAY);
				}
				
				//RED BACKGROUND FOR CURRENT DAY
				try {
					
					if(Integer.parseInt(etiqueta.getText()) == GaiaDate.today().getDay()
							&& date.getSabbath().equals(GaiaDate.today().getSabbath())
							&& date.getYear() == GaiaDate.today().getYear()) {
							calendar[i][j].setBackground(Color.RED);
						}
					if(Integer.parseInt(etiqueta.getText()) == date.getDay()
							&& date.getSabbath().equals(date.getSabbath())
							&& date.getYear() == date.getYear()) {
							calendar[i][j].setBackground(Color.CYAN);
						}
					
				}catch(Exception e) {};
				
				//ADD PANEL TO ITS ROW
				rows[i].add(calendar[i][j]);
			}
		}
		
		//CONFIGURE calendar container
		calendarContainer.setLayout(new GridLayout(gridRows, gridColumns));
		
		//TOP container
		JPanel topContainer = new JPanel();
		topContainer.setLayout(new GridLayout(2,1));
		
		/* ADDS EACH ROW TO THE CALENDAR CONTAINER PANEL */
		for(JPanel r : rows) {
			calendarContainer.add(r);
		}
		
		return calendarContainer;
		
	}

	/*----------BOTTOM PANEL METHOD----------*/
	public JPanel bottomPanel(GaiaDate date) {
		
		this.date = date;
		
		/*------------BOTTOM PANEL: CHANGES DE DATE------------*/
		JPanel dayContainer = new JPanel();
		JPanel sabContainer = new JPanel();
		JPanel yearContainer = new JPanel();
		
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		
		/* SABBATH Container panel */
		ArrayList<String> arlist = new ArrayList<String>();
		
		for(Sabbath s : Sabbath.sabbathList(date.isLeapYear())) {
			arlist.add(s.firstCapitalName());
		}

		String[] sabbaths = arlist.toArray(new String[arlist.size()]);
		JLabel sabEditLabel = new JLabel();
		sabEditLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		sabEditLabel.setText("Sabbath: ");
		
		sabbathBox = new JComboBox<Object>(sabbaths);
		sabbathBox.setSelectedIndex(date.getSabbath().id - 1);
		sabbathBox.addActionListener(this);
		sabbathBox.setFont(new Font("Arial", Font.BOLD, 20));
		
		sabbathBox.setRenderer(dlcr);
		
		sabContainer.add(sabEditLabel);
		sabContainer.add(sabbathBox);
		
		
		/* DAY Container panel */
		String[] days = new String[date.getSabbath().days];
		
		for(int i = 0; i < date.getSabbath().days; i++) {
			days[i] = String.valueOf(i+1);
		}
		
		JLabel dayEditLabel = new JLabel();
		dayEditLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		dayEditLabel.setText("Day: ");

		dayBox = new JComboBox<Object>(days);
		dayBox.setSelectedIndex(date.getDay() -1);
		dayBox.addActionListener(this);
		dayBox.setFont(new Font("Arial", Font.BOLD, 18));
		
		dayBox.setRenderer(dlcr);
		
			//SUBPANEL THAT CONTAINS THE LABEL AND THE BOX
		JPanel dayRightPanel = new JPanel();
		dayRightPanel.setLayout(new GridLayout(1,2));
		dayRightPanel.add(dayEditLabel);
		dayRightPanel.add(dayBox);
		
			//RESET BUTTON
		resetButton = new JButton();
		JLabel resetText = new JLabel();
		resetText.setText("Today");
		resetText.setFont(new Font("Arial", Font.BOLD, 18));
		
		resetButton.add(resetText);
		resetButton.addActionListener(this);
		
		
			//THE FINAL CONTAINER DO DO DOO DOOO, DO DO DO DO DOOO
		dayContainer.setLayout(new BorderLayout());
		
		dayContainer.add(resetButton, BorderLayout.WEST);
		dayContainer.add(dayRightPanel, BorderLayout.EAST);
		
		
		
		/* YEAR Container panel */
		String[] years = new String[100000];
		
		for(int i = 0; i < years.length; i++) {
			years[i] = String.valueOf(i);
		}
		
		JLabel yearEditLabel = new JLabel();
		yearEditLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		yearEditLabel.setText("Year (Human Era): ");
		
		yearBox = new JComboBox<Object>(years);
		yearBox.setSelectedIndex(date.getYear());
		yearBox.setEditable(true);
		yearBox.addActionListener(this);
		yearBox.setFont(new Font("Arial", Font.BOLD, 18));
		
		yearBox.setRenderer(dlcr);
		
			//SUBPANEL THAT CONTAINS THE LABEL AND THE BOX
		JPanel yearLeftPanel = new JPanel();
		yearLeftPanel.setLayout(new GridLayout(1,2));
		yearLeftPanel.add(yearEditLabel);
		yearLeftPanel.add(yearBox);

			//THE FINAL CONTAINER DO DO DOO DOOO, DO DO DO DO DOOO
		yearContainer.setLayout(new BorderLayout());
		yearContainer.add(yearLeftPanel, BorderLayout.WEST);
		
		
		//BOTTOM Container
		JPanel botContainer = new JPanel();
		botContainer.setLayout(new GridLayout(1,3));
		
			//ADD ALL CONTAINERS TO THE BOTTOM
		botContainer.add(dayContainer);
		botContainer.add(sabContainer);
		botContainer.add(yearContainer);
		
		//System.out.println(date.getEneastraDay().firstCapitalName());
		
		return botContainer;
	}

	/*----------UPDATE THE GUI METHOD----------*/
	private void updateGUI() {
		
		this.remove(top);
		this.remove(center);
		this.remove(bot);
		
		this.top = topPanel(date);
		this.center = centerPanel(date);
		this.bot = bottomPanel(date);
		
		this.add(top, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(bot, BorderLayout.SOUTH);
				
		this.setVisible(true);
		this.pack();
		
		this.revalidate();
		
	}

	/*----------LISTENER METHOD----------*/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//SABBATH BOX LISTENER
		if(e.getSource()==sabbathBox) {

			Sabbath selected = Sabbath.getSabbathByName(sabbathBox.getSelectedItem().toString(), date.isLeapYear());
			if(this.date.getDay() > selected.days) {
				this.date.setDay(selected.days);
			}
			this.date.setSabbath(selected);
			
			updateGUI();
		}
		
		//DAY BOX LISTENER
		if(e.getSource()==dayBox) {

			int selected = Integer.parseInt(dayBox.getSelectedItem().toString());
			this.date.setDay(selected);
			
			updateGUI();
		}
		
		//RESET BUTTON BOX LISTENER
		if(e.getSource()==resetButton) {

			this.date = GaiaDate.today();
			
			updateGUI();
		}
		
		//YEAR BOX LISTENER
		if(e.getSource()==yearBox) {
			try {
				
				int selected = yearBox.getSelectedIndex();
				
				this.date.setYear(selected);
				
			}catch(Exception exc) {
				
				JOptionPane.showMessageDialog(null, "Error: Invalid year, you fucking moron", "ERROR", JOptionPane.WARNING_MESSAGE);
				
			}
			
			updateGUI();
		}
		
	}

}
