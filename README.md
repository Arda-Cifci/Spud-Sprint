# Project Spud Sprint
A video game where you, a potato, traverse through a 2D maze dodging enemies and collecting keys in order to open each level’s door and escape the maze.  

## Build 
### Base Requirements
At least Java JDK 14 installed.  The newest Java JDK version can be downloaded [here.](https://www.oracle.com/java/technologies/downloads/)

Apache Maven installed. Apache Maven can be downloaded [here.](https://maven.apache.org/download.cgi)

Access to your computer’s terminal or an IDE of your choice that is able to run code via commands or it’s terminal option. 
The IDE Visual Studio Code can be downloaded [here.](https://code.visualstudio.com/download) 

### Building the Game 
1.  After pulling this repository, navigate to the folder’s location in your terminal.

2.  Afterwards, move the directory into the “spudsprint” folder.  

3.  In this directory write “mvn clean install” (no quotation marks) in the terminal to install the game.

Note : If your IDE has other ways to install an apache maven project and you are more comfortable/experienced with that, then use that instead. 

## Run 
### Method 1 : mvn exec:java
To run the game, navigate your terminal directory to the “spudsprint” folder and write “mvn exec:java” (no quotation marks). This will run the game **after building**.

### Method 2 : java -jar target/spudsprint-1.0-SNAPSHOT.jar
Alternatively, you can navigate your terminal directory to the “spudsprint” folder and write “java -jar target/spudsprint-1.0-SNAPSHOT.jar”       (no quotation marks) to run the game **after building**.

The JAR file can also be found in the Artifacts folder in the repository if it is missing, make sure to place it inside the generated target folder **after building** or it won't run properly.  

### Method 3 : IDE Run
If your IDE has a run code option built-in, you may use that instead. This option may contain errors when trying to run, if you do have error, use methods 1 or 2 instead.

## Test
To test the game, navigate your terminal to the “spudsprint” folder and write 

“mvn test” (no quotation marks).  

This will run the game’s implemented tests.

Note: If your IDE has an option to run a maven program's tests another way, you may use that instead.  

## Javadocs
By building the game using “mvn clean install”, a target folder should have been generated.  Inside this folder, a folder called apidocs should contain the generated Javadoc files.  

You can also write “mvn javadoc:javadoc” (no quotation marks) in the spudsprint terminal directory to generate the javadocs if the apidocs folder is missing.  

Alternatively, you can find the Javadoc files in the Artifacts folder in the repository.

## Tutorial Demo Video
A video demo/tutorial of this game can be found on:

[https://www.youtube.com/watch?v=w_iGEttZCK4](https://www.youtube.com/watch?v=w_iGEttZCK4)

(If the video doesn't work contact Arda through E-mail at arda_cifci@sfu.ca)

## Github Command reference
git clone REPO  
git add DIRECTORY  
git commit -m "MESSAGE"  
git push REMOTE BRANCH  
git rm FILE --cache -rf  
Git Cheat Sheet - https://www.atlassian.com/git/tutorials/atlassian-git-cheatsheet  

## Authors
Arda Cifci - arda_cifci@sfu.ca  
Nayeong Lee - nla74@sfu.ca  
Ellie Neufeld - ena31@sfu.ca  
Cole Thacker - cole_thacker@sfu.ca  
  
