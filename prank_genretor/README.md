# Prank Generator

This project is about sending pranks to a group of email addresses. We will use a mail robot to do that.

## Getting Started

It is better to use a mock mock server to test the programm first. That is why we put "localhost" as smtp adress server in the config file. But if you want directly be connected to another server, you just have to change this field with, for example "smtp.heig-vd.ch" for the server mail of our school (HEIG-VD).
So you can clone our project named "prank generator" and open it with an IDE like IntelliJ for example. Just run the MailRobot.java.

### Prerequisites

For the mock mock server that simulates a server smtp on your host machine, go to the tweakers-dev/mockMock on github to get it. Then clone it and execute the MockMock-1.4.0.one-jar.jar like "java -jar MockMock-1.4.0.one-jar.jar -p 2525 -h 8282" if you want the serevr listens on the 2525 port number (host 8282) that is the same we use in our config file.
Don't close the terminal if you want the server mock mock keeps running.

## Files Description
Here we are going to present you the different classes used by the MailRobot to send the pranks.

- config.properties: specifies the smtp server address/smtp port/number of groups/people per group/delimiter (for the messages contained in messages.utf8)
- messages.utf8: contains the different messages that will be sent (subject with body)
- victims.utf8: contains the email addresses that can receive or send the pranks
- Person.java: represents a victim by his email address
- Prank.java: represents the prank by the message, the group of victims and the sender (random in the victims too)
- Parser.java: contains the tools to read or parse the config files
- ISmtpClient.java: is the interface of the client smtp that bind it to implement a "sendMessage" method
- SmtpClient.java: contains all the actions a normal client smtp can do. It means building the mail fields and send it (conection/disconection is made by the MailRobot.java that use the SmtpClient.java just to send the message).
- PrankGenerator.java: this class is used to generate in a random way a message, some receivers and a sender by choosing those elements in the messages.utf8 and victims.utf8 files depending the config.properties file
- MailRobot.java: main class that launch the programm by reading the config files, generating the pranks, connecting to the smtp server, sending the pranks and disconecting. It uses, by the way, all the classes we describe here as tools
## Running the tests
If you runned the MailRobot.java, then you can check your mock mock server interface by your browser with the search: localhost:8282.
Then you will see there are two pranks sent. That is because we specify in the config.properties file that we want 2 groups of six people to send different pranks by choosing it with a random method.

## Authors

Julien Brêchet and Adrien Marco

## Notes
If you want to change the config.properties file by adding somme victims or groups, do not forget to add the victims or groups in the consequent file beacause the programm needs to choose random victims it does not have yet and he does it until finding something.
And we have still some encoding problems with the caracters like "é, à, ü".


