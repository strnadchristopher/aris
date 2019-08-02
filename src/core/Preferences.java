package core;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Preferences
{
		//Config reader and settings and stuff
		public static String username = "Christopher";
		public static String assistantName = "Aris";
		public static String morningGreeting = "It's morning";
		public static String afternoonGreeting = "It's noon";
		public static String eveningGreeting = "It's night time";
		public static String nightGreeting = "It's night";
		public static String lateNightGreeting = "It's late night";
		public static boolean saveUnansweredQuestions = false;
		public static boolean useCleverbot;
		public static boolean useOnlyCleverbot;
		public static boolean missingConfig;
		public static boolean missingAvatarConfig;
		public static boolean generateLog;
		public static boolean scroll;
		public static int width;
		public static int height;
		public static boolean fullscreen;
		public static int scrollSpeed;
		public static boolean showBorder;
		public static Color backgroundColor;
		public static Color typingAreaColor;
		public static Color fontColor;
		public static String musicDir;
		
		//List of config options
		private List<String> configOptionsList;
		private List<String> avatarConfigOptionsList;
		
		public void readConfig()
		{
			try {
				configOptionsList = Files.readAllLines(Paths.get("config.txt"), StandardCharsets.UTF_8);
				//Reads the config file
				for(int i = 0; i < configOptionsList.size(); i++)
				{
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("username"))
					{
						username = configOptionsList.get(i).split(";")[1];
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("assistantname"))
					{
						assistantName = configOptionsList.get(i).split(";")[1];
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("SaveUnansweredQuestions"))
					{
						saveUnansweredQuestions = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1].toLowerCase());
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Border"))
					{
						showBorder = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1].toLowerCase());
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("BackgroundColor"))
					{
						backgroundColor = Color.decode(configOptionsList.get(i).split(";")[1].toLowerCase());
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("TypingAreaColor"))
					{
						typingAreaColor = Color.decode(configOptionsList.get(i).split(";")[1].toLowerCase());
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("FontColor"))
					{
						fontColor = Color.decode(configOptionsList.get(i).split(";")[1].toLowerCase());
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("MusicDir"))
					{
						musicDir = configOptionsList.get(i).split(";")[1];
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("GenerateLog"))
					{
						generateLog = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1]);
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Scroll"))
					{
						scroll = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1]);
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Width"))
					{
						width = Integer.parseInt(configOptionsList.get(i).split(";")[1]);
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Height"))
					{
						height = Integer.parseInt(configOptionsList.get(i).split(";")[1]);
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Fullscreen"))
					{
						fullscreen = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1]);
					}
					if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("ScrollSpeed"))
					{
					}
				}
			} catch (IOException e) {
				missingConfig = true;
				e.printStackTrace();
			}
		}
		
		public void readAvatarConfig()
		{
			try {
				avatarConfigOptionsList = Files.readAllLines(Paths.get("avatars/" + assistantName + "/avatar.txt"), StandardCharsets.UTF_8);
				//Reads the config file
				for(int i = 0; i < avatarConfigOptionsList.size(); i++)
				{
					if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("morningGreeting"))
					{
						morningGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", username).replaceAll("%assistantName%", assistantName);
					}
					if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("afterNoonGreeting"))
					{
						afternoonGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", username).replaceAll("%assistantName%", assistantName);
					}
					if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("eveningGreeting"))
					{
						eveningGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", username).replaceAll("%assistantName%", assistantName);
					}
					if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("nightGreeting"))
					{
						nightGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", username).replaceAll("%assistantName%", assistantName);
					}
					if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("lateNightGreeting"))
					{
						lateNightGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", username).replaceAll("%assistantName%", assistantName);
					}
				}
			} catch (IOException e) {
				missingAvatarConfig = true;
				e.printStackTrace();
			}
		}
}
