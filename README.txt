"OneMind"
© Copyright Christopher Strnad 2016

////////////////////////////////////////////////////////////ABOUT////////////////////////////////////////////////////////////

Ayyo bby, welcome to "OneMind", the world's first and best fully customizable personal assistant  (or as refered to from here on out, "PA" . (I hope, I honestly have no idea if this is the first time this sort've thing has been done, but if it is, that's pretty cool!) Everyone's heard of 'Siri' and 'Cortana', but who wants to use those completley non-customizable, botnet-y, pieces of garbage anyway. With OneMind you can change just about everything it does to fit your needs and desires. Here's a list of the things you can do in this version of OneMind:

1. Change the name your PA calls you
2. Change the name of your PA 
3. Change the avatar of your PA
4. Have 4 avatars (if you want more let me know) which are really more like avatar states of emotion, like you can have one for being sad, one for being happy and so on and so forth.
5. Change the 'banter' of the PA. You know how you'll tell Siri to tell you a joke and she'll tell you one you've definately already heard? Well now, not only can you customize every single response your PA gives you, it's extremely easy. 
6. Change your PA's greeting messages, each of which are dependent on the time of day.
7. Change the color of the ui
8. Change the orientation and size of the ui to fit any usage scenario you could want

And more to come soon!

If you're not already sold on this application, then I really don't think you're gonna like it.

Bummer.

For those of you who ARE sold, however, continue reading to learn how to do all the things i listed above. (even though it's really self explanatory if you actually open the config file but like whatever)

/////////////////////////////////////////////////////////CONFIGURING/////////////////////////////////////////////////////////

Here's the shortest way of explaining how to do stuff:

/////////////////////////////////////OPTIONS/////////////////////////////////////////////////////////////////////////////////
The config file (config.txt) holds all the basic settings, the left side is the setting and the right side is the value, the settings and values are NOT case sensitive, (although they must have a colon (:) between them) everything can be set to whatever you want but any of the settings with the value of true or false have to be true or false, putting anything else will break the whole thing.

/////////////////////////////////////BANTER//////////////////////////////////////////////////////////////////////////////////
To change the inputs and their responses, go to the q&a folder and open up inputs.txt and responses.txt. The way these work is that the inputs in inputs.txt trigger the responses in responses.txt, according to line number in the file. So if your input when read by the response generator (which, by the way, allows you to make typos and still understand what you're saying) matches a line in the inputs.txt, it'll output the cooresponding response.

//////////////////////////////////INPUT SLICING///////////////////////////////////////////////////////
Another cool thing about the inputs and responses system is what I call "input slicing", this may sound like a dumb buzzword, because it is, but basically it allows you to put several different inputs on one line and, provided you separate them with a forward slash (/) they will all trigger the same response. If you put these slashes in a line of the response file, it will randomly choose one of the responses you put on the line when that line is picked at the response line.

//////////////////////////////////AVATARS////////////////////////////////////////////////////////////////////////////////////
This is a huge part of the "OneMind" experience. Do you want your avatar to be a cute dog or something? Then read closely friend.

In the folder named "avatars" you will see some folders with names on them. Create a folder with the name of whatever you want your PA to be called and open it up. This is where you'll need to place your pictures (MAKE SURE THEY ARE PNG FILES). Anything square that's about 400x400 pixels or higher should do the trick. You can have just one, or up to four (for now) images in here. Know that each one will represent a different emotion, so choose wisely. Or don't. It's fully customizable not fully CHRIStomizable.

(i am extremely tired)

Anyway, rename each of your files to the names listed below.

happy.png
sad.png
angry.png
confused.png

If you can't change the ".png" at the end of the file, go into your windows explorer (the thing you're using to look at all your folders and files) folder options and uncheck "hide extensions for known file types", then try again.

Once you've done this, launch the program and see if everything's working correctly. If you're using four images, they'll change with what your PA has to say.