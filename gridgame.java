/*
Name: Pari Shah
Purpose: To create a logical/spatial game with the theme of space

Sources for Images
1. Picture Name: lvlone1.jpg            Source: http://www.barnabu.co.uk/otherplanets/
2. Picture Name: lvlone2.jpg            Source: http://www.abc.net.au/science/news/enviro/EnviroRepublish_1510005.htm
3. Picture Name: lvlone3.jpg            Source:http://phys.org/news/2012-12-vast-ancient-caverns-mars-captured.html
4. Picture Name: lvlone4.jpg            Source: http://medienwerkstatt-online.de/lws_wissen/vorlagen/showcard.php?id=151&edit=0
5. Picture Name: intropic.jpg           Source: https://www.behance.net/gallery/17859413/BLUE-NEBULA
6. Picture Name: [level backgrounds]    Source: http://www.designtrends.com/graphic-web/backgrounds/space-backgrounds.html
7. Picture Name: lvltwo1.jpg            Source: http://www.weownthesun.com/history.html
8. Picture Name: lvltwo2.jpg            Source: http://pics-about-space.com/cold-blue-supergiant-star?p=1
9. Picture Name: lvltwo3.jpg            Source: http://all-free-download.com/free-icon/download/star_3d_6814876.html
10.Picture Name: lvltwo4.jpg            Source: http://astro.unl.edu/naap/ebs/spectraltype.html
11.Picture Name: lvlthree1.jpg          Source: http://www.smartcompany.com.au/finance/48585-hopes-to-see-nbn-rocket-ahead-after-satellite-launch.html
12.Picture Name: lvlthree2.jpg          Source: https://www.pinterest.com/76roxy/sweet-love/
13.Picture Name: lvlthree3.jpg          Source: http://www.nasa.gov/audience/forstudents/k-4/stories/steps-to-countdown-text.html
14.Picture Name: lvlthree4.jpg          Source: http://www.alibaba.com/showroom/rockets-toy-candy.html
15.Picture Name: win.jpg                Source: http://www.l2so.com/wallpaper/Mountain-surface-of-the-planet-13390
16.Picture Name: girl.jpg               Source: http://www.123rf.com/photo_6750733_little-girl-as-a-traveler-in-space.html
17.Picture Name: boy.jpg                Source: http://www.123rf.com/photo_6750733_little-boy-as-a-traveler-in-space.html
*/
import sun.audio.*;
import java.io.*;
import java.io.FileInputStream.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import javax.swing.event.*;


public class gridgame extends Applet implements ActionListener, ChangeListener
{
    int r = 0;
    Panel p_card;  //to hold all of the screens
    Panel card0, card1, card2, card3, card4, card5, card6, card7, card8;
    CardLayout cdLayout = new CardLayout ();
    int row = 2;
    int col = 2;
    int fuel = 20;
    int fuel2 = 20;
    int fuel3 = 20;
    int lvl = 0;
    JRadioButton gf, bm;
    JTextField age;
    JLabel result;
    JLabel inst, score1, score2, score3, earned;
    JButton a[] = new JButton [row * col];
    JButton b[] = new JButton [row * col];
    JButton c[] = new JButton [row * col];
    JButton enter;
    JTextField text1, text2, text3;
    JButton next1, next2, next3, reset, click, Rate;
    int lvl1[] [] = {{0, 0}, {0, 0}};
    int lvl2[] [] = {{0, 0}, {0, 0}};
    int lvl3[] [] = {{0, 0}, {0, 0}};

    public void init ()
    {
	playMusic ("space");
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	radiobutton ();
	intro ();
	character ();
	instruct ();
	level1 ();
	level2 ();
	level3 ();
	winning ();
	rategame ();
	resize (650, 650);
	setLayout (new BorderLayout ());
	initMenu (); //<---------
	add ("Center", p_card);
    }


    public static void playMusic (String filepath)  // Method to allow music of 1MB size or smaller
    {
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	AudioData MD;
	ContinuousAudioDataStream loop = null;
	try
	{
	    BGM = new AudioStream (new FileInputStream (filepath + ".wav")); //set song
	    MD = BGM.getData (); //get data from song
	    loop = new ContinuousAudioDataStream (MD); //set as loop
	}
	catch (IOException error)  //error
	{
	    System.out.println ("Audio - File not found.");
	}
	MGP.start (loop); //start running loop
    } //end of method playMusic


    public void initMenu ()
    {
	JMenuBar menuBar = new JMenuBar ();
	JMenu menu;
	JMenuItem menuItem;

	menu = new JMenu ("File");
	menuBar.add (menu);

	menuItem = new JMenuItem ("Close");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("Close");
	menu.add (menuItem);

	menu = new JMenu ("Navigate");
	menuBar.add (menu);

	menuItem = new JMenuItem ("Opening");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("card1");
	menu.add (menuItem);

	menuItem = new JMenuItem ("Rules");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("card3");
	menu.add (menuItem);

	menuItem = new JMenuItem ("Play Game");
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("card4");
	menu.add (menuItem);

	add ("North", menuBar);
    }


    public void radiobutton ()
    { //asks for age, and gender before game starts
	card0 = new Panel ();
	card0.setBackground (new Color (0, 0, 204));
	JLabel ins = new JLabel ("Enter your age: ");
	ins.setFont (new Font ("Arial", Font.BOLD, 20));
	age = new JTextField (4);
	Panel p = new Panel ();
	p.add (ins);
	p.add (age);
	gf = new JRadioButton ("Girl?");
	gf.addActionListener (this);
	gf.setActionCommand ("gf");
	gf.setFont (new Font ("Arial", Font.BOLD, 20));
	bm = new JRadioButton ("Boy?");
	bm.addActionListener (this);
	bm.setActionCommand ("bm");
	bm.setFont (new Font ("Arial", Font.BOLD, 20));
	ButtonGroup group = new ButtonGroup ();
	group.add (gf);
	group.add (bm);
	Panel p2 = new Panel (new GridLayout (2, 1));
	p2.add (gf);
	p2.add (bm);
	Panel p3 = new Panel ();
	result = new JLabel ("The result is... ");
	result.setFont (new Font ("Arial", Font.BOLD, 16));
	result.setPreferredSize (new Dimension (350, 350));
	result.setForeground (Color.pink);
	p3.add (result);
	Panel p4 = new Panel ();
	click = new JButton ("Click Here to Continue");
	click.addActionListener (this);
	click.setActionCommand ("card1");
	click.setEnabled (false);
	p4.add (click);
	card0.add (p);
	card0.add (p2);
	card0.add (p3);
	card0.add (p4);
	p_card.add ("0", card0);
    }


    public void intro ()
    {
	//intro screen
	card1 = new Panel ();
	card1.setBackground (Color.black);
	JButton background = new JButton (createImageIcon ("intropic.JPG"));
	background.setPreferredSize (new Dimension (596, 594));
	background.setBorderPainted (false);
	background.setActionCommand ("card2");
	background.addActionListener (this);
	card1.add (background);
	p_card.add ("1", card1);
    }


    public void character ()
    {
	//choose your character
	card2 = new Panel ();
	card2.setBackground (new Color (0, 0, 204));
	JLabel title = new JLabel ("Choose your character!");
	title.setFont (new Font ("Arial", Font.BOLD, 20));
	JLabel girlorboy = new JLabel ("Emily or Tommy?");
	girlorboy.setFont (new Font ("Arial", Font.BOLD, 20));
	JButton girl = new JButton (createImageIcon ("girl.jpg"));
	girl.setActionCommand ("girlname");
	girl.addActionListener (this);
	JButton boy = new JButton (createImageIcon ("boy.jpg"));
	boy.setActionCommand ("boyname");
	boy.addActionListener (this);
	Panel p = new Panel ();
	p.add (title);
	Panel p1 = new Panel ();
	p1.add (girlorboy);
	Panel p2 = new Panel ();
	p2.add (girl);
	p2.add (boy);
	card2.add (p);
	card2.add (p1);
	card2.add (p2);
	p_card.add ("2", card2);
    }


    public void instruct ()
    {
	//instructions screen
	card3 = new Panel ();
	card3.setBackground (new Color (0, 0, 204));
	JLabel title = new JLabel ("Instructions:\n");
	title.setFont (new Font ("Script MT Bold", Font.BOLD, 50));
	title.setForeground (new Color (255, 204, 204));
	inst = new JLabel ("\nYou are on a mission to planet Computela! You are traveling");
	inst.setForeground (new Color (204, 255, 255));
	inst.setFont (new Font ("Arial", Font.BOLD, 14));
	JLabel inst2 = new JLabel ("\nwith a rocketship that may run out of fuel! Solve each mystery word");
	inst2.setForeground (new Color (204, 255, 255));
	inst2.setFont (new Font ("Arial", Font.BOLD, 14));
	JLabel inst3 = new JLabel ("\nusing the 4 pictures provided. Remember, you lose fuel each time");
	inst3.setForeground (new Color (204, 255, 255));
	inst3.setFont (new Font ("Arial", Font.BOLD, 14));
	JLabel inst4 = new JLabel ("\n you answer incorrectly. So be careful! If you successfully find the");
	inst4.setForeground (new Color (204, 255, 255));
	inst4.setFont (new Font ("Arial", Font.BOLD, 14));
	JLabel inst5 = new JLabel ("\n word, you will earn 10 litres of fuel. Each time you fail to guess the word,");
	inst5.setForeground (new Color (204, 255, 255));
	inst5.setFont (new Font ("Arial", Font.BOLD, 14));
	JLabel inst6 = new JLabel ("\nyou will lose 5 litres of fuel.");
	inst6.setForeground (new Color (204, 255, 255));
	inst6.setFont (new Font ("Arial", Font.BOLD, 14));
	JLabel inst7 = new JLabel ("\nYou begin with 20 litres of fuel!");
	inst7.setFont (new Font ("Arial", Font.BOLD, 14));
	inst7.setForeground (new Color (204, 255, 255));
	JLabel inst8 = new JLabel ("\nGood Luck!         ");
	inst8.setForeground (new Color (204, 255, 255));
	inst8.setFont (new Font ("Arial", Font.BOLD, 14));
	Panel p = new Panel ();
	p.add (title);
	Panel p2 = new Panel ();
	JButton back = new JButton ("Back");
	back.setForeground (Color.blue);
	back.setBackground (Color.white);
	back.setActionCommand ("back2");
	back.addActionListener (this);
	Panel p3 = new Panel ();
	JLabel howTo = new JLabel (createImageIcon ("instructions.jpg"));
	howTo.setVerticalAlignment (SwingConstants.CENTER);
	JButton play = new JButton ("PLAY");
	play.setBackground (Color.white);
	play.setForeground (Color.magenta);
	play.setActionCommand ("card4");
	play.addActionListener (this);
	card3.add (p);
	card3.add (inst);
	card3.add (inst2);
	card3.add (inst3);
	card3.add (inst4);
	card3.add (inst5);
	card3.add (inst6);
	card3.add (inst7);
	card3.add (inst8);
	card3.add (howTo);
	p2.add (back);
	p2.add (play);
	card3.add (p2);
	p_card.add ("3", card3);
    }


    public void level1 ()
    {
	//the actual game (Level 1)
	card4 = new Panel ();
	card4.setBackground (new Color (0, 0, 204));
	JLabel title = new JLabel (createImageIcon ("levelone.jpg"));
	//code for grid
	Panel g = new Panel (new GridLayout (row, col));
	for (int i = 0 ; i < a.length ; i++)
	{
	    a [i] = new JButton (" ");
	    a [i].addActionListener (this);
	    a [i].setActionCommand ("a" + i);
	    g.add (a [i]);
	    a [i].setPreferredSize (new Dimension (200, 200));
	}
	JButton alpha[] = new JButton [26];
	text1 = new JTextField (40);
	Panel h = new Panel (new GridLayout (2, 13));
	for (int i = 0 ; i < alpha.length ; i++)
	{
	    alpha [i] = new JButton ("" + (char) (i + 97));
	    alpha [i].addActionListener (this);
	    alpha [i].setActionCommand ("b" + (char) (i + 97));
	    alpha [i].setForeground (Color.blue);
	    alpha [i].setBackground (Color.white);
	    h.add (alpha [i]);
	}
	JButton back = new JButton ("Backspace");
	back.setActionCommand ("*");
	back.addActionListener (this);
	enter = new JButton ("Enter");
	enter.setForeground (Color.red);
	enter.setActionCommand ("enter1");
	enter.addActionListener (this);
	next1 = new JButton ("Level 2");
	next1.setActionCommand ("card5");
	next1.addActionListener (this);
	next1.setEnabled (false);
	score1 = new JLabel ("Score: " + fuel + " litres of fuel");
	score1.setForeground (new Color (204, 255, 255));
	score1.setFont (new Font ("Arial", Font.BOLD, 16));
	Panel p = new Panel ();
	p.add (title);
	Panel p1 = new Panel ();
	p1.add (g);
	Panel p2 = new Panel ();
	p2.add (text1);
	Panel p3 = new Panel ();
	p3.add (h);
	Panel p4 = new Panel ();
	p4.add (back);
	p4.add (enter);
	Panel p5 = new Panel ();
	p5.add (next1);
	Panel p6 = new Panel ();
	p6.add (score1);
	card4.add (p);
	card4.add (p1);
	card4.add (p2);
	card4.add (p3);
	card4.add (p4);
	card4.add (p5);
	card4.add (p6);
	p_card.add ("4", card4);
    }


    public void level2 ()
    {
	//Level 2
	card5 = new Panel ();
	card5.setBackground (new Color (0, 0, 204));
	JLabel title = new JLabel (createImageIcon ("leveltwo.jpg"));
	Panel j = new Panel (new GridLayout (row, col));
	for (int i = 0 ; i < b.length ; i++)
	{
	    b [i] = new JButton (" ");
	    b [i].addActionListener (this);
	    b [i].setActionCommand ("c" + i);
	    j.add (b [i]);
	    b [i].setPreferredSize (new Dimension (200, 200));
	}
	JButton alpha[] = new JButton [26];
	text2 = new JTextField (40);
	Panel k = new Panel (new GridLayout (2, 13));
	for (int i = 0 ; i < alpha.length ; i++)
	{
	    alpha [i] = new JButton ("" + (char) (i + 97));
	    alpha [i].addActionListener (this);
	    alpha [i].setActionCommand ("d" + (char) (i + 97));
	    alpha [i].setForeground (Color.blue);
	    alpha [i].setBackground (Color.white);
	    k.add (alpha [i]);
	}
	JButton back = new JButton ("Backspace");
	back.setActionCommand ("**");
	back.addActionListener (this);
	enter = new JButton ("Enter");
	enter.setForeground (Color.red);
	enter.setActionCommand ("enter2");
	enter.addActionListener (this);
	next2 = new JButton ("Level 3");
	score2 = new JLabel ("Score: " + fuel2 + " litres of fuel");
	score2.setFont (new Font ("Arial", Font.BOLD, 16));
	score2.setForeground (new Color (204, 255, 255));
	next2.setEnabled (false);
	next2.setActionCommand ("card6");
	next2.addActionListener (this);
	Panel p = new Panel ();
	p.add (title);
	Panel p1 = new Panel ();
	p1.add (j);
	Panel p2 = new Panel ();
	p2.add (text2);
	Panel p3 = new Panel ();
	p3.add (k);
	Panel p4 = new Panel ();
	p4.add (back);
	p4.add (enter);
	Panel p5 = new Panel ();
	p5.add (next2);
	Panel p6 = new Panel ();
	p6.add (score2);
	card5.add (p);
	card5.add (p1);
	card5.add (p2);
	card5.add (p3);
	card5.add (p4);
	card5.add (p5);
	card5.add (p6);
	p_card.add ("5", card5);
    }


    public void level3 ()
    {
	//Level 3
	card6 = new Panel ();
	card6.setBackground (new Color (0, 0, 204));
	JLabel title = new JLabel (createImageIcon ("levelthree.jpg"));
	Panel l = new Panel (new GridLayout (row, col));
	for (int i = 0 ; i < b.length ; i++)
	{
	    c [i] = new JButton (" ");
	    c [i].addActionListener (this);
	    c [i].setActionCommand ("e" + i);
	    l.add (c [i]);
	    c [i].setPreferredSize (new Dimension (200, 200));
	}
	JButton alpha[] = new JButton [26];
	text3 = new JTextField (40);
	Panel m = new Panel (new GridLayout (2, 13));
	for (int i = 0 ; i < alpha.length ; i++)
	{
	    alpha [i] = new JButton ("" + (char) (i + 97));
	    alpha [i].addActionListener (this);
	    alpha [i].setActionCommand ("f" + (char) (i + 97));
	    alpha [i].setForeground (Color.blue);
	    alpha [i].setBackground (Color.white);
	    m.add (alpha [i]);
	}
	enter = new JButton ("Enter");
	enter.setForeground (Color.red);
	enter.setActionCommand ("enter3");
	enter.addActionListener (this);
	JButton back = new JButton ("Backspace");
	back.setActionCommand ("***");
	back.addActionListener (this);
	next3 = new JButton ("NEXT");
	next3.setActionCommand ("card7");
	next3.addActionListener (this);
	next3.setEnabled (false);
	score3 = new JLabel ("Score: " + fuel3 + " litres of fuel");
	score3.setForeground (new Color (204, 255, 255));
	score3.setFont (new Font ("Arial", Font.BOLD, 16));
	Panel p = new Panel ();
	p.add (title);
	Panel p1 = new Panel ();
	p1.add (l);
	Panel p2 = new Panel ();
	p2.add (text3);
	Panel p3 = new Panel ();
	p3.add (m);
	Panel p4 = new Panel ();
	p4.add (back);
	p4.add (enter);
	Panel p5 = new Panel ();
	p5.add (next3);
	Panel p6 = new Panel ();
	p6.add (score3);
	card6.add (p);
	card6.add (p1);
	card6.add (p2);
	card6.add (p3);
	card6.add (p4);
	card6.add (p5);
	card6.add (p6);
	p_card.add ("6", card6);
    }


    public void winning ()
    {
	//winning screen
	card7 = new Panel ();
	card7.setBackground (new Color (0, 0, 204));
	JLabel win = new JLabel (createImageIcon ("win.jpg"));
	win.setVerticalAlignment (SwingConstants.CENTER);
	earned = new JLabel ("You earned " + fuel3 + " litres of fuel in total!");
	earned.setForeground (Color.cyan);
	earned.setFont (new Font ("Script MT Bold", Font.BOLD, 40));
	reset = new JButton ("Reset and Play Again?");
	reset.setActionCommand ("reset");
	reset.addActionListener (this);
	reset.setBackground (Color.white);
	Rate = new JButton ("Rate this game!");
	Rate.addActionListener (this);
	Rate.setActionCommand ("card8");
	card7.add (win);
	Panel p = new Panel ();
	p.add (earned);
	Panel p1 = new Panel ();
	p1.add (Rate);
	p1.add (reset);
	card7.add (p);
	card7.add (p1);
	p_card.add ("7", card7);

    }


    public void rategame ()
    { //JSlider allows user to rate the game
	card8 = new Panel ();
	card8.setBackground (new Color (0, 0, 204));
	Panel p = new Panel ();
	Panel p1 = new Panel ();
	Panel p2 = new Panel ();
	Panel p3 = new Panel ();
	JLabel rate = new JLabel ("On a scale of 1 to 5, how would you rate this game?");
	rate.setFont (new Font ("Arial", Font.BOLD, 20));
	JSlider red = new JSlider (JSlider.HORIZONTAL, 0, 5, 0);
	red.addChangeListener (this);
	red.setMajorTickSpacing (1);
	red.setMinorTickSpacing (0);
	red.setPaintTicks (true);
	red.setPaintLabels (true);
	JLabel thanks = new JLabel ("Thank you!");
	thanks.setFont (new Font ("Arial", Font.BOLD, 20));
	JButton goback = new JButton ("Go back");
	goback.setActionCommand ("card7");
	goback.addActionListener (this);
	p.add (rate);
	p1.add (red);
	p2.add (thanks);
	p3.add (goback);
	card8.add (p);
	card8.add (p1);
	card8.add (p2);
	card8.add (p3);
	p_card.add ("8", card8);
    }


    public void actionPerformed (ActionEvent e)
    { //characters
	if (e.getActionCommand ().equals ("girlname"))
	{
	    inst.setText ("You chose EMILY! You are on a mission to planet Computela! You are traveling");
	    cdLayout.show (p_card, "3");
	}
	else if (e.getActionCommand ().equals ("boyname"))
	{
	    inst.setText ("You chose TOMMY! You are on a mission to planet Computela! You are traveling");
	    cdLayout.show (p_card, "3");
	}
	//"enter" buttons for each level
	if (e.getActionCommand ().equals ("enter1"))
	{
	    String txt = text1.getText ();
	    if (txt.equals ("planet") || txt.equals ("planets"))
	    {
		JOptionPane.showMessageDialog (null, "You got it! Great work! Click on the next level.",
			"Correct", JOptionPane.INFORMATION_MESSAGE);
		next1.setEnabled (true);
		fuel += 10;
		fuel2 = fuel;
	    }
	    else if (fuel <= 0)
	    {
		score1.setText ("Score: " + fuel + " litres of fuel");
		JOptionPane.showMessageDialog (null, "You ran out of fuel! It is GAME OVER.", "GAME OVER", JOptionPane.ERROR_MESSAGE);
		enter.setEnabled (false);
		System.exit (0);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "You are incorrect! Try again.", "Incorrect", JOptionPane.ERROR_MESSAGE);
		fuel -= 5;
		fuel2 = fuel;
	    }
	    score1.setText ("Score: " + fuel + " litres of fuel");
	}
	else if (e.getActionCommand ().equals ("enter2"))
	{
	    String txt = text2.getText ();
	    if (txt.equals ("star") || txt.equals ("stars"))
	    {
		JOptionPane.showMessageDialog (null, "You got it! Great work! Click on the next level.",
			"Correct", JOptionPane.INFORMATION_MESSAGE);
		next2.setEnabled (true);
		fuel2 += 10;
		fuel3 = fuel2;
	    }
	    else if (fuel2 <= 0)
	    {
		score2.setText ("Score: " + fuel2 + " litres of fuel");
		JOptionPane.showMessageDialog (null, "You ran out of fuel! It is GAME OVER.", "GAME OVER", JOptionPane.ERROR_MESSAGE);
		enter.setEnabled (false);
		System.exit (0);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "You are incorrect! Try again.", "Incorrect", JOptionPane.ERROR_MESSAGE);
		fuel2 -= 5;
		fuel3 = fuel2;
	    }
	    score2.setText ("Score: " + fuel2 + " litres of fuel");
	}
	else if (e.getActionCommand ().equals ("enter3"))
	{
	    String txt = text3.getText ();
	    if (txt.equals ("rocket") || txt.equals ("rockets"))
	    {
		JOptionPane.showMessageDialog (null, "You got it! Great work! Click on the next level.",
			"Correct", JOptionPane.INFORMATION_MESSAGE);
		next3.setEnabled (true);
		fuel3 += 10;
	    }
	    else if (fuel3 <= 0)
	    {
		score3.setText ("Score: " + fuel3 + " litres of fuel");
		JOptionPane.showMessageDialog (null, "You ran out of fuel! It is GAME OVER.", "GAME OVER", JOptionPane.ERROR_MESSAGE);
		enter.setEnabled (false);
		System.exit (0);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "You are incorrect! Try again.", "Incorrect", JOptionPane.ERROR_MESSAGE);
		fuel3 -= 5;
	    }
	    score3.setText ("Score: " + fuel3 + " litres of fuel");
	}
	//moves between screens
	else if (e.getActionCommand ().equals ("card0"))
	    cdLayout.show (p_card, "0");
	else if (e.getActionCommand ().equals ("card1"))
	    cdLayout.show (p_card, "1");
	else if (e.getActionCommand ().equals ("card2") || e.getActionCommand ().equals ("back2"))
	    cdLayout.show (p_card, "2");
	else if (e.getActionCommand ().equals ("card3"))
	    cdLayout.show (p_card, "3");
	else if (e.getActionCommand ().equals ("card4"))
	{
	    score1.setText ("Score: " + fuel + " litres of fuel");
	    cdLayout.show (p_card, "4");
	}
	else if (e.getActionCommand ().equals ("card5"))
	{
	    score2.setText ("Score: " + fuel2 + " litres of fuel");
	    cdLayout.show (p_card, "5");
	}
	else if (e.getActionCommand ().equals ("card6"))
	{
	    score3.setText ("Score: " + fuel3 + " litres of fuel");
	    cdLayout.show (p_card, "6");
	}
	else if (e.getActionCommand ().equals ("card7"))
	{
	    earned.setText ("You earned " + fuel3 + " litres of fuel in total!");
	    cdLayout.show (p_card, "7");
	}
	else if (e.getActionCommand ().equals ("card8"))
	    cdLayout.show (p_card, "8");
	else if (e.getActionCommand ().equals ("Close"))
	    System.exit (0);
	else if (e.getActionCommand ().equals ("reset"))
	{
	    text1.setText ("");
	    fuel = 20;
	    next1.setEnabled (false);
	    next2.setEnabled (false);
	    next3.setEnabled (false);
	    age.setText ("");
	    result.setText ("The result is...");
	    click.setEnabled (false);
	    text2.setText ("");
	    fuel2 = 20;
	    text3.setText ("");
	    fuel3 = 20;
	    cdLayout.show (p_card, "0");
	}
	else if (e.getActionCommand ().equals ("*"))
	    text1.setText (text1.getText ().substring (0, text1.getText ().length () - 1));
	else if (e.getActionCommand ().equals ("**"))
	    text2.setText (text2.getText ().substring (0, text2.getText ().length () - 1));
	else if (e.getActionCommand ().equals ("***"))
	    text3.setText (text3.getText ().substring (0, text3.getText ().length () - 1));
	else
	{
	    char which = e.getActionCommand ().charAt (0);
	    /*a = level 1 pics, b = level 1 alphabet
	      c = level 2 pics, d = level 2 alphabet
	      e = level 3 pics, f = level 3 alphabet
	    */
	    if (which == 'a')
	    { //which button clicked on the grid
		int n = Integer.parseInt (e.getActionCommand ().substring (1, e.getActionCommand ().length ()));
		a [n].setIcon (createImageIcon ("lvlone" + (n + 1) + ".jpg"));
		int x = n / row;
		int y = n % row;
		showStatus ("(" + x + ", " + y + ")");
		if (lvl1 [x] [y] == 0)
		    lvl1 [x] [y] = 1;
		else
		    lvl1 [x] [y] = 0;
	    }
	    else if (which == 'b')
	    {
		text1.setText (text1.getText () + e.getActionCommand ().charAt (1));
	    }
	    else if (which == 'c')
	    {
		int m = Integer.parseInt (e.getActionCommand ().substring (1, e.getActionCommand ().length ()));
		b [m].setIcon (createImageIcon ("lvltwo" + (m + 1) + ".jpg"));
	    }
	    else if (which == 'd')
	    {
		text2.setText (text2.getText () + e.getActionCommand ().charAt (1));
	    }
	    else if (which == 'e')
	    {
		int o = Integer.parseInt (e.getActionCommand ().substring (1, e.getActionCommand ().length ()));
		c [o].setIcon (createImageIcon ("lvlthree" + (o + 1) + ".jpg"));
	    }
	    else if (which == 'f')
		text3.setText (text3.getText () + e.getActionCommand ().charAt (1));
	}
	//the radio button
	try
	{
	    if (e.getActionCommand ().equals ("gf"))
	    {
		int ageA = Integer.parseInt (age.getText ());
		result.setText ("You may play!");
		click.setEnabled (true);
	    }
	    else if (e.getActionCommand ().equals ("bm"))
	    {
		int ageA = Integer.parseInt (age.getText ());
		result.setText ("You may play!");
		click.setEnabled (true);
	    }
	}
	catch (Exception ee)
	{
	    result.setText ("Enter your age first. Re-select radio button.");
	}

    } //end of AP method


    /** Listen to the slider. */
    public void stateChanged (ChangeEvent e)
    {
	JSlider source = (JSlider) e.getSource ();
	if (!source.getValueIsAdjusting ())
	{
	    r = (int) source.getValue ();
	    showStatus (r + "");
	}
    }



    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = gridgame.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}


	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}


