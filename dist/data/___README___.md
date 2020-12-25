# Data Files
In this directory are various types of file's.  The extensions typically describe the use and purpose of the file.

## Extensions
* info - Information Files
	- typically static data that will rarely change in between reboots
* idx - Index Files
	- Typically related to Map, Mob, or Object files.
	- (Currently) There is only one IDX file - Zone.idx
	- Will contain references to other related files
* map - Map Files 
	- Will be related to room inter-connectivity and association; the larger associations (big picture) will be handled via the *.idx file but there is room for some internal connectivity within the map file.
* mob - Mob Files
	- Referenced in the `Zone.idx` file and should contain information related to the Zone the mobs will be found.
	- Will contain data related to mobs within an Zone

## Directories
These directories are typically the containers for the sub-data referenced in the *.idx files.

* maps - Map Directory: will contain any and all file(s) associated with a Zone referenced in Zone.idx

## File Names (Standard)
These files are statically referenced within the Server.  Changing the name will cause the server to not start; therefore, edit the contents but not the Name or Extension

* Zone - Zone File	
	- Directory: `data` root 
	- File: Zone.idx 
	- (As of Now) Can be edited at any time but reflecting the change will require a reboot.
	- Used for World, Region, and/or Area association and connectivity; each entry will be singularly related to a specific [Area].map file in the `maps` directory.
* Mob - Mob File(s)
	- Directory: `data/maps`
	- File(s): [Area].mob
	- (As of Now) Can be edited at any time but reflecting the change will require a reboot.
	- 
* Object 