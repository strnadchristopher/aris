package core;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ResponseGenerator {
	
	//Array lists
	private List<String> inputsList;
	private List<String> answersList;
	private List<String> confusedList;
	private List<String> inputParts;
	//Levenshtein Logic
	private int lowestDistance = 100;
	private String matchedInput;
	private int lowestStringLine;
	private String currentString;
	private String currentSlice;
	//Getting question stuff
	private Random random = new Random();
	//Desktop object
	private Desktop desktop;
	//Music Directory
	private ArrayList<File> musicFiles;
	/*
	 * Alright so, basically, if the question has to do with some kind of fun fact, then i 
	 * go to google for it, hopefully, and it gives you the answer. If it's just some cute question
	 * then it goes through a list of questions and answers, and if the question matches one of the
	 * questions in questions.txt, then it gets its matching answer on the same line in answers.txt
	 * this serves the dual purpose of being easy for me to work on, as well as being super
	 * customizable for anybody using the software.
	 * tl;dr this makes the ai answer your questions.
	 */
	
	//Inits the Generator
	public void init() throws IOException
	{
		//Sets up array lists
		inputsList = Files.readAllLines(Paths.get("q&a/inputs.txt"), StandardCharsets.UTF_8);
		answersList = Files.readAllLines(Paths.get("q&a/responses.txt"), StandardCharsets.UTF_8);
		confusedList = Files.readAllLines(Paths.get("q&a/confused.txt"), StandardCharsets.UTF_8);
		//Create Desktop object
		desktop = Desktop.getDesktop();
		//Music files stuff
		musicFiles = new ArrayList<File>();
		System.out.println("Loaded");
	}
	
	//Gets all the music files in the music directory
	public void listMusicFiles(String directoryName, ArrayList<File> files)
	{
		//Set up music directory
		File musicDir = new File(directoryName);
		File[] musicList = musicDir.listFiles();
		for (File file : musicList) 
		{    
			if (file.isFile()) 
			{
				if(file.getName().endsWith(".mp3") || file.getName().endsWith(".wav") || 
						file.getName().endsWith(".wma") || file.getName().endsWith(".amr") || 
						file.getName().endsWith(".flac") || file.getName().endsWith(".aac") || 
						file.getName().endsWith(".m4a") || file.getName().endsWith(".ac3") || 
						file.getName().endsWith(".3gp") || file.getName().endsWith(".3g2"))
				{
					musicFiles.add(file);
					files.add(file);
				}	
			} 
			else if (file.isDirectory()) 
			{
				listMusicFiles(file.getAbsolutePath(), files);
			}
		}
	}
	
	//Generates Response
	public String generate(String input) throws FileNotFoundException
	{
		//Set input to lowercase
		input = input.toLowerCase();
		//Specific Commands
		//Exits
		if(input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit"))
		{
			System.exit(0);
		}
		//Write an email
		if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "email"))
		{
			try {
				desktop.mail(new URI("mailto:" + input.substring(6)));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
			return "Launching email client...";
		}
		//If user is requesting a search
		if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "look up") 
				|| org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "google")
				|| org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "what is")
				|| org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "what is a")
				|| org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "what was"))
		{
			String wikiURL = null;
			String google = "http://www.google.com/search?q=wikipedia";
			String search = input.replaceAll("look up ", "").replaceAll("google ", "")
							.replaceAll("please ", "").replaceAll("what is a ", "")
							.replaceAll("what is ", "").replaceAll("what was ", "")
							.replaceAll("[-+^]*", "").split(",.")[input.split(",.").length - 1]
							.replaceAll(" ", "+");
			String charset = "UTF-8";
			String userAgent = "Hello (+http://google.com)"; // Change this to your company's name and bot homepage!

			Elements links = null;
			try
			{
				links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
			} catch (UnsupportedEncodingException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			wikiURL = links.get(0).absUrl("href");
			System.out.println("URL: " + wikiURL);
			//Gets the answer from wikipedia
			Document wikiPage = null;
			try
			{
				wikiPage = Jsoup.connect(wikiURL).get();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Sorry, I don't know that......yet.";
			}
			Elements ps = wikiPage.select("p");
			String body = ps.text();
			String answer = body.substring(0, body.indexOf(". ") + 1);
			System.out.println("Answer: " + answer);
			
			if(answer == null || answer == "" || answer == " ")
			{
				return "Sorry I don't know that......yet.";
			}
			return answer;
		}
		//Gets the weather
		if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(input, "weather"))
		{
			try {
				desktop.browse(new URI("https://www.google.com/#q=" + "weather"));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
			return "Here it is!";
		}
		
		//THIS CALCULATES THE CLOSEST INPUT LINE TO THE INPUT
				//Parse the input in case it has multiple parts, separated by commas.
				inputParts = Arrays.asList(input.replaceAll(",.?!", ",").split(","));
				for(int p = 0; p < inputParts.size(); p++){
					//Resets matched input
					//Resets lowest distance
					matchedInput = inputsList.get(0);
					lowestDistance = StringUtils.getLevenshteinDistance(inputParts.get(p), matchedInput);
					//Levenshtein Loop, gets the closest question to input, if the ai didn't ask a question, should detect if its an answer
					for(int i = 0; i < inputsList.size(); i++){
						currentString = inputsList.get(i);
						for(int s = 0; s < currentString.split("/").length; s++)
						{	
							currentSlice = currentString.split("/")[s];
							if(StringUtils.getLevenshteinDistance(inputParts.get(p), currentSlice) < lowestDistance){
								matchedInput = currentSlice;
								lowestStringLine = i;
								lowestDistance = StringUtils.getLevenshteinDistance(inputParts.get(p), currentSlice);
							}
						}
					}
				}
		//Checks input list for response.
		//Sets the response
		if(lowestDistance < 8)
		{
			return answersList.get(lowestStringLine).split("/")[random.nextInt(answersList.get(lowestStringLine).split("/").length)];
		}
		
		 //If all else fails, this returns an error
		 return confusedList.get(random.nextInt(confusedList.size()));
	}
	
	//Gets a random question to ask
	public String getQuestion() 
	{
		return inputsList.get(random.nextInt(inputsList.size()));
	}
}
