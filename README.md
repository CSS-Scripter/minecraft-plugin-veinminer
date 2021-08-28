# VeinMiner
A spigot plugin for minecraft 1.17

## Installation
Simply move the veinminer jar into the plugins folder.

### Compiling
Copy the directory and execute the command ``mvn package``. Once it's done you should find the jar file in the target folder.

## Commands
/veinminer - Main command

/veinminer whitelist - Displays all whitelist commands

/veinminer whitelist help - Displays all whitelist commands

/veinminer whitelist list - Shows the current whitelist

/veinminer whitelist add [MATERIAL] - Add's a material to the whitelist

/veinminer whitelist remove [MATERIAL] - Removes a material from the whitelist

## Permissions

veinminer.whitelist - Access to /veinminer whitelist and all subcommands

## Config 

Inside the configuration file there are currently 2 variables:
- blockWhitelist - This is a list of all vein mineable blocks
- veinMineLimit - This is the max amount of blocks that can be vein mined at once