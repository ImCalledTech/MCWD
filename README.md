# MCWD
### About
MCWD, or the Minecraft World Deleter, is a Java program that, as suggested in the name, will mass-delete Minecraft worlds.
This is very useful to speedrunners who generate hundreds of Minecraft worlds for every session (depending on whether they
use multiple instances and how many times they reset) and Minecraft worlds tend to take up a large portion of your hard
drive when lots of unused worlds are just lying there, and just generally clog up the Minecraft world select menu.
### Installation
To install, go to the releases tab of this repository and download the JAR binary in the latest version. Double-click the
JAR file to run the program. Having problems? Try running it from your command prompt, with `java -jar JAR-NAME.jar`.
### Configuration
The options file (`options.json`) should be found in your user's home directory, in the `.imcalledtech` folder and then in
the `MCWD` folder. Fill in the options with your desired options and make sure to stick to the `JSON` formatting. **Make
sure that if there are backslashes, make them double** (i.e. from `\` to `\\`) otherwise the program will reset the
directory to the default minecraft folder. If your path uses forward slashes, there is no need to change anything.