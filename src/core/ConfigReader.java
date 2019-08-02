package core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConfigReader {

	//List of config options
	public static List<String> configOptionsList;
	public static List<String> avatarConfigOptionsList;
	
	public static void readConfig()
	{
		try {
			configOptionsList = Files.readAllLines(Paths.get("config.txt"), StandardCharsets.UTF_8);
			//Reads the config file
			for(int i = 0; i < configOptionsList.size(); i++)
			{
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("username"))
				{
					Preferences.username = configOptionsList.get(i).split(";")[1];
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("assistantname"))
				{
					Preferences.assistantName = configOptionsList.get(i).split(";")[1];
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("SaveUnansweredQuestions"))
				{
					Preferences.saveUnansweredQuestions = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1].toLowerCase());
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Border"))
				{
					Preferences.showBorder = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1].toLowerCase());
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("GenerateLog"))
				{
					Preferences.generateLog = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1]);
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Scroll"))
				{
					Preferences.scroll = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1]);
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Width"))
				{
					Preferences.width = Integer.parseInt(configOptionsList.get(i).split(";")[1]);
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Height"))
				{
					Preferences.height = Integer.parseInt(configOptionsList.get(i).split(";")[1]);
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("Fullscreen"))
				{
					Preferences.fullscreen = Boolean.parseBoolean(configOptionsList.get(i).split(";")[1]);
				}
				if(configOptionsList.get(i).split(";")[0].equalsIgnoreCase("ScrollSpeed"))
				{
					Preferences.scrollSpeed = Integer.parseInt(configOptionsList.get(i).split(";")[1]);
				}
			}
		} catch (IOException e) {
			Preferences.missingConfig = true;
			e.printStackTrace();
		}
	}
	
	public static  void readAvatarConfig()
	{
		try {
			avatarConfigOptionsList = Files.readAllLines(Paths.get("avatars/" + Preferences.assistantName + "/avatar.txt"), StandardCharsets.UTF_8);
			//Reads the config file
			for(int i = 0; i < avatarConfigOptionsList.size(); i++)
			{
				if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("morningGreeting"))
				{
					Preferences.morningGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", Preferences.username).replaceAll("%assistantName%", Preferences.assistantName);
				}
				if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("afterNoonGreeting"))
				{
					Preferences.afternoonGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", Preferences.username).replaceAll("%assistantName%", Preferences.assistantName);
				}
				if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("eveningGreeting"))
				{
					Preferences.eveningGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", Preferences.username).replaceAll("%assistantName%", Preferences.assistantName);
				}
				if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("nightGreeting"))
				{
					Preferences.nightGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", Preferences.username).replaceAll("%assistantName%", Preferences.assistantName);
				}
				if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("lateNightGreeting"))
				{
					Preferences.lateNightGreeting = avatarConfigOptionsList.get(i).split(";")[1].replaceAll("%username%", Preferences.username).replaceAll("%assistantName%", Preferences.assistantName);
				}
				if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("UseCleverbot"))
				{
					Preferences.useCleverbot = Boolean.parseBoolean(avatarConfigOptionsList.get(i).split(";")[1].toLowerCase()); 
				}
				if(avatarConfigOptionsList.get(i).split(";")[0].equalsIgnoreCase("UseOnlyCleverbot"))
				{
					Preferences.useOnlyCleverbot = Boolean.parseBoolean(avatarConfigOptionsList.get(i).split(";")[1].toLowerCase()); 
				}
			}
		} catch (IOException e) {
			Preferences.missingAvatarConfig = true;
			e.printStackTrace();
		}
	}
}
