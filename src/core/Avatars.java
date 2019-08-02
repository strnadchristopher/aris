package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Avatars
{
	BufferedImage[] emotions;
	public Avatars()
	{
		//Inits avatars
		emotions = new BufferedImage[4];
		//Happy
		try {
			emotions[0] = ImageIO.read(new File("avatars/" + Preferences.assistantName + "/happy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Sad
		try {
			emotions[1] = ImageIO.read(new File("avatars/" + Preferences.assistantName + "/sad.png"));
		} catch (IOException e) {
			e.printStackTrace();
			emotions[1] = emotions[0];
		}

		//Angry
		try {
			emotions[2] = ImageIO.read(new File("avatars/" + Preferences.assistantName + "/angry.png"));
		} catch (IOException e) {
			e.printStackTrace();
			emotions[2] = emotions[0];
		}
				
		//Confused
		try {
			emotions[3] = ImageIO.read(new File("avatars/" + Preferences.assistantName + "/confused.png"));
		} catch (IOException e) {
			e.printStackTrace();
			emotions[3] = emotions[0];
		}
	}
	
	public BufferedImage getAvatar(int id)
	{
		return emotions[id];
	}
}
