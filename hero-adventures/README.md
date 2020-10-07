# Sujet Aventurier

This Java8 Project aims to reproduce the movements of the hero on a map.  

*A hero adventures himself in a dangerous world fighting is way through the dark woods.*

### Card
The map is modelled using characters in a text file in UTF-8 format (a sample is available [here](https://github.com/sousacruz/exercicios/blob/master/hero-adventures/src/main/resources/data/card.txt)).  

Example:
```
###    ######    ###
###      ##      ###
##     ##  ##     ##
#      ##  ##      #
##                ##
#####          #####
###### ##  ##  #####
 #     ######     # 
     ########       
    ############    
    ############    
     ########      #
 #     ######     ##
###### ##  ## ######
#####          #####
##                ##
#   ## #    # ##   #
##   ##      ##   ##
###    #    #    ###
###    ######    ###
```

#### Legend
- '#' impenetrable woods 
- ' ' (space character): box where the hero can move

### Moving the hero
The movements of the hero are defined by a file with the following characteristics:
 - encoding: UTF-8
 - First line:
	 - Contains the initial coordinates of the hero in the form "x, y" 
	 - The coordinates (0,0) correspond to the upper left corner of the map
 - Second line: 
	 - Movement of the hero defined as a succession of characters representing the cardinal directions (N, S, E and O) 
	 Each character corresponds to the  displacement of a box

### Interaction with map elements
 - The hero cannot go beyond the edges of the map. 
 - The hero cannot go on the squares occupied by the impenetrable woods.


## Before you begin

In order to execute this project you will need to do.
1.  Clone this repository
2.  Download a sample [card.txt](https://github.com/sousacruz/exercicios/blob/master/hero-adventures/src/main/resources/data/card.txt) file.
3.  Download a sample [input.txt](https://github.com/sousacruz/exercicios/blob/master/hero-adventures/src/main/resources/data/inputs.txt) file.


## Building

To build the app, goto to the project directory and run: `./mvnw install`.

## Running

To build the app, goto to the project target directory and you will be able to find a `jar`  file generated in the previous step. Run the following command:

    java -jar hero-adventures-0.0.1-SNAPSHOT.jar \
	    /tmp/card.txt \
	    /tmp/input.txt 

The program will write console outputs according to each Hero Adventures, for exemplo:

`The hero must finish at (x.y)` where correspond to the final positon of the hero after a given succession of displacements.
