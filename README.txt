WARNING: Need to press tab 3 times once the game starts in order to gain control of the slider. I used JTextFields to output the score, 
	level and lives left in the GUI. You need to tab 3 times to get out of the boxes and put control on the frame. I don't know why
	this happens.

Edits made on 4/19/16:
	Added the ability for the ball to bounce around the frame
	Improved the bounds of the sliding bar (so that it stops at the edge of the frame on the left and right side)
	Implemented the levels and gameplay mechanics (to an extent. I couldn't get it to go past level two)


In theory, this game was supposed to have four levels. The player advances through each level by surviving for a pre-determined amount of
time. After beating the fourth level, the player has beaten the game. 
The background image for each level as well as the sliding bar are all images that I either made or downloaded and then imported (extra credit). 

The unique part about each level is that the gravity constant changes on each one. The final level is tricky because
each time you lose a life and the ball resets, the gravity constant is reset to a random value within a pre-set range.

level one is earth, level two the moon, level three some random alien planet I found online, and level four is the Cromulon dimension.

Gameplay: press tab three times to gain control of the slider, and then try to keep the ball up.

Acknowledgements:
I modeled the mechanics of the bar movement from the Super Giorgio sample code from lecture. 
I modeled the setup of my second timer for the levels from this page on stack overflow:
	http://stackoverflow.com/questions/2258066/java-run-a-function-after-a-specific-number-of-seconds
Credit to Rick and Morty and Adult Swim for the invention and picture of the Cromulon dimension for level four (even though it's never 
reached in the game, the image is still stored in the code and in the graphics folder) 
	
