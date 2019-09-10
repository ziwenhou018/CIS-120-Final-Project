=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: 85120400
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Dynamic Dispatch/Subtyping/Inheritance
  	Because that there are a bunch of entities in the game, each with their own
  	coordinates and size, I decided to make different classes for them. What
  	made this essential was that each entity had various methods and variables.
  	For example, Moveable Objects also had a velocity but Tiles and Coins don't.
  	The Player moves differently than a Robot. These things required me to create
  	subclasses so that I could use dynamic dyspatch and inheritance to code more
  	efficiently. I chose to use an abstract class instead of an interface because
  	every entity has a location and size and the methods to get and set those
  	are all the same.

  2. Arrays
  	All I do with a 2D array is to initialize the starting board. It would be
  	tedious to create a class for the hundreds of entities in my game. Thus, I
  	could use a switch case and for loop to iterate through the array, making
  	classes for my entities based on what type of entity they were.

  3. Collections
  	Collections were used to store entities inside Sets so that I could easily
  	check collisions. I used Sets because the ordering really doesn't matter.
  	I didn't need the index of any particular element in the collection either.
  	I was able to iterate through each collection, checking for collisions and
  	acting based on that. Originally, I was going to make a gun that could
  	kill enemies on the screen (which I would have stored in another Collection)
  	but I scrapped that idea.
  	
  4. File IO
  	I will use File IO to be able to allow the user to leave the game and have their
  	progress saved in a file. My IntegerScanner will read the values written in a file
  	and load them up the next time the user plays the game.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
	Game - This is the frame to run my game. This class also takes in an
		array and initializes the map.
	GameCourt - This is where all the game code is. I have almost everything
		here, from creating new classes to managing every tick and movement, to
		storing game variables to reading static image files so that multiple
		classes can use them to controlling listeners to essentially creating
		the game and shop.
	Entity - This is an abstract class used to model everything in the game.
		Each instance of the class has a location and size.
	MoveableObj - This is an abstract class used to model every moving thing
		in the game. These instances also have a velocity and a method of moving.
	Player - This is a class for the user, with variables like health, jumping
		power and number of bullets. A lot of variables in player are called
		in GameCourt to access the situation (ex. player is alive, player has
		gun, etc.).
	Coin - This is a class for the game's currency. The GameCourt calls this class
		and creates coins based on where they are in the Game's array.
	Tile - This is a class that holds the information of the blocks to stand on in
		the GameCourt. These are impassible. There's also two types of tiles:
		wood and cloud, each with their own look.
	Robot - This is a class for the neutral enemies in the game. They can either move
		or stand still and shoot.
	Bullet - This is a class to represent the projectiles shot from guns. Both the
		player and robots can shoot bullets. Unfortunately, after writing up more code
		I started getting these Illegal State exceptions. I had to remove the player's
		ability to use a gun, though I only commented those lines of code and not 
		deleted them.
	Bowser - He is the final boss and moves randomly and unfortunately cannot shoot
		because of what I said above. Thus, he moves randomly and the player must touch
		him in order to do damage.
	IntegerScanner - This is supposed to be the class that reads a file that represents
		the current state of the game. When the player leaves, the file is saved so that
		the state of the game can be read again once the game is loaded up.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  	Trying to code collisions and movement was tricky. 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  	Most of my code is in GameCourt but I think it is fine. Almost all the
  	variables are private. I would refactor the movement section of the code.
  	I also wish I had more time to work on my project. I wish I was able to
  	implement the bullet system for the player and bowser. It's just a prototype 
  	so it doesn't have the best levels. But it has potential!

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
	JavaDocs