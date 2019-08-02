package launch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import core.ConfigReader;
import core.Preferences;
import core.ResponseGenerator;

public class Launch extends JPanel implements KeyListener 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currentMessage;
	//Date and Time stuff
	private Date now;
	private String hour;
	private ResponseGenerator rg = new ResponseGenerator();
	BufferedWriter writer;
	JTextField inputField = new JTextField(){

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public void paintComponent(Graphics g) {
            Graphics2D graphics2d = (Graphics2D) g;
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            super.paintComponent(g);
        }
    };
	public static void main(String[] args)
	{
		//Set up the JFrame
		JFrame f = new JFrame();
		f.getContentPane().add(new Launch());
		f.setResizable(true);
		f.setSize(800, 450);
		//f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setUndecorated(true);
		f.setVisible(true);
		f.setBackground(Color.black);
	}
	
	public Launch()
	{
		//Read config
		ConfigReader.readConfig();
		ConfigReader.readAvatarConfig();
		setLayout(new FlowLayout());
		//Setup InputField
		inputField = new JTextField(100);
		inputField.setVisible(true);
		inputField.setPreferredSize(new Dimension(1400, 40));
		inputField.setBackground(Color.black);
		inputField.setForeground(Color.white);
		inputField.setBorder(null);
		inputField.setHorizontalAlignment(SwingConstants.CENTER);
		inputField.setCaretColor(Color.black);
		add(inputField, BorderLayout.LINE_START);
		inputField.addKeyListener(this);
		//Writer
		try
		{
			writer = new BufferedWriter(new FileWriter(new File(System.getProperty("user.home") + "/line.txt")));
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			rg.init();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		greetUser();
	}
	
	int paints;
	public void paint(Graphics g)
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		Font font = null;
		try
		{
			font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/gotham-rounded-medium.otf"));
			font = font.deriveFont(Font.BOLD, 60);
		} catch (FontFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		g.setFont(font);
		inputField.setFont(font.deriveFont(Font.BOLD, 24));
	    //Draw Message
		g2.setColor(Color.white);
		g2.drawString(currentMessage, getWidth()/2 - g.getFontMetrics(font).stringWidth(currentMessage)/2, getHeight()/2);
	}

	//Set Message
	public void setMessage(String set)
	{
		currentMessage = set;
		currentMessage = currentMessage.replaceAll("%assistantname%", Preferences.assistantName).replaceAll("%username%", Preferences.username);
		String oldString;
		do {
			  oldString = currentMessage;
			  currentMessage = currentMessage.replaceAll("&sad&", "");
			  currentMessage = currentMessage.replaceAll("&angry&", "");
			  currentMessage = currentMessage.replaceAll("&confused&", "");
			  currentMessage = currentMessage.replaceAll("&random&", "");
			} while(!currentMessage.equals(oldString));
		System.out.println(Preferences.assistantName + ": " + currentMessage);
		//Write message to file
		try
		{
			writer.write(currentMessage);
			writer.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Greets the user
	public void greetUser()
	{
		//Sets up the time objects
		now = new Date();
		hour = new SimpleDateFormat("k").format(now);
		//If it's morning, state the morning greeting
		if(Integer.parseInt(hour) >= 5 && Integer.parseInt(hour) < 12)
		{
			setMessage(Preferences.morningGreeting);
		}
		//If it's afternoon, state the afternoon greeting
		if(Integer.parseInt(hour) >= 12 && Integer.parseInt(hour) < 17)
		{
			setMessage(Preferences.afternoonGreeting);
		}
		//If it's evening, state the evening greeting
		if(Integer.parseInt(hour) >= 17 && Integer.parseInt(hour) < 22)
		{
			setMessage(Preferences.eveningGreeting);
		}
		//If it's night, state the night greeting
		if(Integer.parseInt(hour) >= 22 && Integer.parseInt(hour) < 24)
		{
			setMessage(Preferences.nightGreeting);
		}
		//If it's late night, state the late night greeting
		if(Integer.parseInt(hour) >= 24 || (Integer.parseInt(hour) >= 1 && Integer.parseInt(hour) < 5))
		{
			setMessage(Preferences.lateNightGreeting);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			//Send the input to the rg
			try
			{
				setMessage(rg.generate(inputField.getText()));
			} catch (FileNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			inputField.setText("");
			repaint();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
