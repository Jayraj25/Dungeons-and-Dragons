## Project 5 - MVC Dungeon Game

## Overview
The project is about making full MVC working adventure game. This game generates a dungeon in which a player can move.
The dungeon is a type of maze where each location is assigned a type as cave or tunnel
and the treasures are distributed in these caves along with the arrows as weapons. The player moves from start to end and picks up the treasure/arrows if he wants.
The dungeon also has monsters, pits and thieves which increases the difficulty to play the game.

## Features
1. Two types of dungeons can be created i.e. Wrapping and NonWrapping type.
2. In a wrapping dungeon, the edge locations wrap to the other side of the dungeon.
3. A dungeon is represented as a 2d grid and each grid position is assigned a location type.
4. There are two types of locations available i.e. Cave and Tunnel.
5. If a location has 2 entry/exit, then it is assigned as tunnel.
6. If a location has 1, 3, or 4 entry/exit then it is assigned as cave.
7. There are four types of treasures available for the distribution namely Ruby, Diamond, Topaz, Sapphire.
8. These treasures are assigned randomly in the caves based on the amount provided.
9. The player can move based on the available directions at the location.
10. The player can move in **NORTH, SOUTH, EAST, WEST**.
11. The player can pick up the treasure from the current location if the treasure is available.
12. The start and end cave is generated randomly such that the distance between start and end is atleast 5.
13. The dungeon also has functionality of interconnectivity. We define an interconnectivity = 0 when there is exactly one path from every cave in the dungeon to every other cave in the dungeon. Increasing the degree of interconnectivity increases the number of paths between caves.
14. The generated dungeon is shown in the terminal for easy path tracing.
15. Treasures can only be distributed in the caves and not in the tunnels.
16. The dungeon also consists of monsters (Otyugh) and the actual number of monsters if given as command line args.
17. The dungeon also consists pits in which a player can fall and the player lose the game.
18. There is at least one monster at the end of the dungeon in the cave.
19. The dungeon also consists of robber who when encountered by player steals all the treasure from the player.
20. The locations of dungeon can also contain arrows which can be picked by the player to kill the monster.
21. The player starts the game with 3 arrows.
22. The player can shoot the arrow by giving the direction and distance till which the arrow can be shot.
23. A player entering a cave with an Otyugh that has not been slayed will be killed and eaten.
24. When there is a pit nearby the player is notified with a popUp that a pit is nearby.
25. The player can detect two levels of smell:
    a less pungent smell can be detected when there is a single Otyugh 2 positions from the player's current location
    detecting a more pungent smell either means that there is a single Otyugh 1 position from the player's current location or that there are multiple Otyughs within 2 positions from the player's current location
26. The player can identify the smell when the stench is displayed in cave/tunnel.
27. It takes 2 hits to kill an Otyugh. Player has a 50% chance of escaping if the Otyugh if they enter a cave of an injured Otyugh that has been hit by a single crooked arrow.
28. The player wins by reaching the end location. 
29. The player loses by being eaten by an Otyugh or by falling in a pit.
30. The player has two options to make a move i.e. by pressing arrow keys or by using mouse click within nearby locations.
31. The player can shoot by pressing 's' and an arrow key from keyboard.
32. The player can pick treasure by pressing 't' on the keyboard.
33. The player can pick arrow by pressing 'a' on the keyboard.
34. The player can the description by pressing 'd' on the keyboard.
35. The player can start new game or restart same game or exit from the preferences in the menu bar.
36. The player can view the dungeon configurations from settings in menubar.
37. The player can view the details in the side panel of the window.


## How to run

- Download the .jar file from the project result folder
- open terminal
- locate your .jar file from terminal using
```sh
cd %usersDirectory%
java -jar %filename%.jar
```

### How to use the program
Once the jar file executes
- The form window is displayed which asks for the specifications to start the game.
- Once proper specifications are entered click on submit and a new window will be displayed with the start location of the player.
- Now player can move using arrow keys and using mouse click.
- The details of events that are being performed are shown in side panel.
- The details of the game functions to play the game are also in bottom side window.


## Description of Examples
**Run1- Example screenshot**
- This run shows that the player moves through the dungeon by picking up the arrows and treasure.
- The screen shows the locations explored by the player.
- It also shows a robber at current location which steals the treasure from the player.
- The details of actions performed by the player are shown in the side panel.
- The current location details are also shown in the side panel.
- The player's current location is shown with a white bordered square.

## Design Changes
There were many design changes from the original design. A read only model class was created which is used in the view.
An Output panel class was added which was used to display the events of the player and other information for the view.
A few more private and public functions were also added to meet the project requirements.
A new functionality pits and thieves were also added and functions regarding this were added in the model and view to display relevant information.

## Assumptions
- The dungeon is only successfully created if the product of rows and columns is at least 16.
- All edges don't wrap on the other side of the dungeon.
- There is at least one path from every cave to every other cave.
- If pick treasure command/ key pressed is provided then all the treasures are picked in one go. There is no option to pick individual and number of treasures.
- If pick arrow command/key pressed is provided then all the arrow are picked in one go. There is no option to pick how many arrows the player wants to pick.
- For shooting the player will press 's' followed by an arrow key.

## Limitations
- The dungeon creation is failed if row and column less than 4 are provided respectively.
- The dungeon is only created if the product of rows and columns is at least 16.
- If player is in a location with injured monster he/she doesn't know that there is an injured monster.
- If player is in cave with injured monster, the player can not shoot in the same cave.
- The player can shoot the arrow only upto 5 caves.
- If the GUI window is maximized then the orientation of the components is disturbed.
- There is no way to identify that there is a thief nearby.
- Sometimes the pit is in a way such that player can never reach the end.
- There is no way to avoid pit like maybe jump over it.
- If the player enters the cave with half injured otyugh, there is no way to show that there is an injured otyugh there.
- If player press some other key after 's' to shoot other than arrow keys, then the shoot operation is cancelled.

## Citations
https://docs.oracle.com/javase/8/docs/api/javax/swing/Scrollable.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/ScrollPaneConstants.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/BoxLayout.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/ButtonGroup.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/ImageIcon.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/JRadioButton.html
https://docs.oracle.com/javase/8/docs/api/java/awt/BasicStroke.html
https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
https://docs.oracle.com/javase/8/docs/api/java/awt/package-frame.html
https://docs.oracle.com/javase/8/docs/api/javax/swing/package-frame.html
https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiMvvDkx_rzAhUtc98KHQB_BQ4QFnoECAIQAQ&url=https%3A%2F%2Fwww.geeksforgeeks.org%2Fdepth-first-search-or-dfs-for-a-graph%2F&usg=AOvVaw0--wR_s-gu0AuBPL1ks2Md
https://www.baeldung.com/cs/minimum-spanning-vs-shortest-path-trees
https://www.geeksforgeeks.org/enum-in-java/
https://careerkarma.com/blog/java-enum/
https://www.javatpoint.com/java-hashmap
https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

