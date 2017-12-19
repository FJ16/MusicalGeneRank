# MusicalGeneRank - 23AndMe HW

Use given API and demo info to calculate and display the genetically musical score.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

You may need to install the Eclipse IDE(J2EE version), JAVA 8 and download the Apache Tomcat 9 Server Files for further setup.

```
Eclipse download link: https://www.eclipse.org/downloads/
Apache Tomcat download Link: http://tomcat.apache.org/download-90.cgi 
```

### Setup

After downloading the project files, you may need to setup the development enviroment as follow steps to run the web app:

1. Open Eclipse and click File menu, then select Import.
2. Please select the General -> Existing Projects into Workplace and then browse the Project folder.
3. To setup tomcat server, you may need to click the Servers tag here:(There should be no server, but you can click it to define a new one)
  ![Ins1](https://github.com/jjtpc1205/Source_Images/blob/master/23AndMe_HW_Instructions_2.png)
4. Then define a new server, please choose Tomcat v9.0 Server:
  ![Ins2](https://github.com/jjtpc1205/Source_Images/blob/master/23AndMe_HW_Instructions_1.png)
5. Double click the server name:
  ![Ins3](https://github.com/jjtpc1205/Source_Images/blob/master/23AndMe_HW_Instructions_4.png)
6. At this page, switch server location to "Use Tomcat Installation" and browse the Tomcat folder you downloaded.
(you can also change the port number if you want to, the default port number is 8080 for localhost)
  ![Ins4](https://github.com/jjtpc1205/Source_Images/blob/master/23AndeMe_HW_Instructions_6.png)
7. Right click the server name and select the properties, and then click the switch location, then click OK.
8. Right click the server name again to select "Add and Remove", choose the project and add it into Configured List.
9. Right click the server and choose "start".

## Running the app

1. Type "http://localhost:8080/MusicalGeneRank/" into your browser address bar and go to the page.
2. Click the "Let's go' button to see the score.

### Implementation Discussion

In the front-end development, I used native javascript to make ajax call for retrieving the musical score of current user. In the back-end development, I used java servlet to handle the http request and response. And, for the detail design, i applied the builder pattern on the Record Class, the reason why i used this pattern is to make sure the app has good scalability. Since for this homework, we just need to determine a genetically score for potential musician, but in the future, we may need to use same user record to calculate other kind of scores, maybe for football player or basketball palyer. So, in that situation we have to put lots of data into single user record, then the Record instantiation will be crazy. For possible next steps, I can use the factory pattern to generate multiple JSON parse functions(To extract the necessary data and filter out unwanted data) and the corresponding algorithms for different purposes.
