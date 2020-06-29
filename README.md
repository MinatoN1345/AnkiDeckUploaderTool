# AnkiDeckUploaderTool
A tool which allows decks to be added a lot faster.

# Why:
When adding front and back cards to a deck, it felt tedious going to and fro copying and pasting and pressing submit and this was only the first three cards; I had at least 200 more to do. So I thought, hmm I am a Computer Scientist surely there is a way to automate this.

# What:
Simply uses x-path locating of the email and password fields, as well as locating the fields for the front and back card information and any buttons that need to be clicked.

# Look out:
This will work as long as the Anki web source code does not change drastically and thus change the xpath location of certain elements.

# Setup:

ChromeDriver: 

This just needed to be downloaded respective to your platform and note the path to it's download location, to be used later in the code.
Source: https://chromedriver.chromium.org/downloads


Selenium:

I use Jetbrains IntelliJ (2020.1), I went to File -> Project Structure -> Modules -> + (located at bottom) -> Library -> From maven
and paste in the link from the following site, under the Maven tab.

https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.141.59

Hopefully the libraries are bundled in with the repo and this will work without these steps. 
