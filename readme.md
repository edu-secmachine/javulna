# Javulna

## Table of Contents
1. [Introduction](#Introduction)
2. [Building the application](#Build)
3. [Accessing the API via Postman](#Postman)
4. [Exercises](#Exercises)
    1.  [Exercise 1 - Find users of the app and their password](#Exercise_1)
    2. [Exercise 2 - Log into the application](#Exercise_2)
     1. [Exercise 3 - Change another user's password](#Exercise_3)
     1. [Exercise 4 - Buy cheaper](#Exercise_4)
     1. [Exercise 5 - File handling](#Exercise_5)
     1. [Exercise 6 - Serialization vulnerability](#Exercise_6)
     1. [Exercise 7- Xml handling](#Exercise_7)

<a name="Introduction"></a>
## Introduction 

Javulna is an intentionally vulnerable Java application. It is created for educational purposes. It is intended mainly for Java developers.
Javulna is a movie-related application, where you can log in and out, read information about movies, buy movie-related objects, send messages to other users of the application, etc. The functionalities are far from complete or coherent, they just serve the purpose of demonstrating specific vulnerabilities.
This document contains exercises which can be done with Javulna to understand how to exploit and how to fix specific vulnerabilities.

<a name="Build"></a>
## Building the application 

Javulna is a standard Spring Boot application, built with Maven.

You can build the project with:
```mvn clean install```

Than you can run it with 
```java -jar target/javulna-1.0-SNAPSHOT.jar```

This will start an embedded Tomcat, and run the app. If you want to change the port of the embedded Tomcat to 8089 (default is 8080):
```java -jar target/javulna-1.0-SNAPSHOT.jar --server.port=8089```

If you want to debug it:
```java -Xdebug -Xrunjdwp:server=y,transport=dt_socket_address=5005,suspend=n -jar target/javulna-1.0-SNAPSHOT.jar```

Alternatively you can run (and debug) the project from your preferred IDE by simply running the Application.java class.

<a name="Postman"></a>
## Accessing the API via Postman 
Javulna in itself does not contain any user interface (except a default login page and an empty index.html). It is a RESTfull application accepting http requests and responding JSON strings. In the doc folder you can find a Postman collection export. We suggest you to install Postman on your device and import this collection, since it helps you a lot with starting the exercises.

<a name="Exercises"></a>
## Exercises 

<a name="Exercise_1"></a>
### Exercise 1 – Find users of the app and their passwords
**Short Description**
The list of the movies of the application is accessible by all users (including anonymous users too). Find a vulnerability in this service and exploit it, so that you can see all users of the application and their passwords!

**Service endpoint**
On the /rest/users endpoint you can list movies of the database. This endpoint is accessible to anonymous (not logged in) users too.  
*Request Method*: GET  
*URL*: /rest/movie?title=&lt;title&gt;&description=&lt;desc&gt;&genre=&lt;genre&gt;&id=&lt;id&gt; (none of the request parameters are mandatory)  
*Response*: a JSON containg movies which fulfill the search conditions  

**Postman request**
With Postman check the List Movies request in the Javulna collection to see how it works!

**Detailed description**
The service behind this endpoint is vulnerable to one of the most classic exploit of programming. Find the vulnerability, and exploit it so that you can get users and their passwords from the database! (Hint: The table containing the users' data is called APPUSER.)   
When you are done, check the source code (MovieService.findMovie) and fix it.   
Discuss what could have been the developers motivation creating this code!  

<a name="Exercise_2"></a>
### Exercise 2 - log in to the application

**Short Description**
Using the usernames and passwords discovered in the previous exercise log in to the application. There is no hacking involved here, this step is only necessary so that you can continue with the next exercises.

**Service endpoint**
On the /rest/users endpoint you can list movies of the database. This endpoint is accessible to anonymous (not logged in) users too.  
*Request Method*: POST  
*URL*: /login  
*Request body*: username, password fields  
*Response*: a JSON containg the name of the logged in user and a cookie which can be used for subsequent authentication  

**Postman request**
Use the login request in the Javulna collection (Postman will automatically submit the cookie with the following requests)

<a name="Exercise_3"></a>
### Exercise 3 – change another user's password
**Short Description**
The application contains a password change functionality. Abuse it to change another user's password!

**Service endpoint**
*Request Method*: GET  
*URL*: /rest/user/password?user=Yoda&oldPassword=&lt;old_password&gt;&newPassword=&lt;new_password&gt;  
*Response*:  Ok or Not ok  

**Postman request**
Change password

**Detailed description**
The change password service first creates a password-change xml to call a remote password change service with it (in reality the remote service does nothing remotely, just parses the xml and changes the password locally).  
Find a vulnerability within this service!  
This is how the password service creates the xml file:
```java
private String createXml(String name, String newPassword) {
    try {
        String xmlString = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("xml/PasswordChange.xml"), "UTF-8");
        xmlString = xmlString.replaceAll("PWD_TO_REPLACE", newPassword);
        xmlString = xmlString.replaceAll("USERNAME_TO_REPLACE", name);
        return xmlString;
    } catch (IOException ex) {
        throw new RuntimeException(ex);
    }
}
```

The PasswordChange.xml looks like this:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<PasswrodChange>
    <pwd>PWD_TO_REPLACE</pwd>
    <userName>USERNAME_TO_REPLACE</userName> 
</PasswrodChange>
```
After the exploit fix the vulnerability within the code.

<a name="Exercise_4"></a>
### Exercise 4 – Buy cheaper
**Short Description**
You can buy movie-related objects with the application. Each object have a name, a description and a price. Try to by something for cheaper than the original price!

**Service endpoint**
*Request Method*: PUT  
*URL*: /rest/order  
*Body*: a JSON string containing the order  
	
Response: a JSON containing the details of the order and the final price.

**Postman request**
Use the “Buy movie objects” request to place an order and the “List buyable movie objects” request to see what you can buy!	

**Detailed description**
Find a way to buy something for a cheaper price than intended!  
After you found the vulerability, fix the code!

<a name="Exercise_5"></a>
### Exercise 5 – File handling
**Short Description**
The application has a file upload and a file download functionality. Both of them suffer from several vulnerabilities. Find a vulnerability, with which you can read any file from the server's files-system!

**Service endpoint**
FILE UPLOAD  
*Request Method*: POST  
*URL*: /uploadFile  
*Body*: the file to upload with "file" key  
*Response*: A JSON object containig information about the uploaded file  

FILE DOWNLOAD  
*Request Method*: GET  
*URL*: /downloadFile?fileName=&lt;file name&gt;  
*Response*:  The file to be downloaded  

**Postman request**
Upload File  
Donwload File  

**Detailed description**
The application stores uploaded files on the server's file-system. In order for the upload and download functionality to work you first have to set the value of the javulna.filestore.dir property in the application.properties file to some reasonabel value (to a real path which exists on your machine).  
Then try to download a file with the application that is outside of this directory!  
Once you are done fix the found vulnerability!  
What other voulnerabiltites can you spot in the upload file functionality? How would you fix theese?  
<a name="Exercise_6"></a>
### Exercise 6 – Serialization vulnerability

**Short Description**
Find a serialization vulnerability withing the application, and exploit it!

**Service endpoint**
There is no specific endpoint for this exercise.

**Postman request**
all of them applicable

**Detailed description**
The application uses a serialized cookie to do some extra security check. Alas this extra feature actually introduces a serious security bug. Find the cookie and try to find out what is in it! Then modify it to exploit the vulnerability!  
If you feel lost, check the classes: ExtraAuthenticationCheckFilter and CustomAuthenticationSuccessHandler.   
Be aware that the application has a dependency to org.apache.commons-collections4 4.0.  

<a name="Exercise_7"></a>
### Exercise  7 – Xml handling 
**Short Description**
The create movie service accepts xml input as well as JSON. There are two ways to call this service and one of them is vulnerable. Find out which one!

**Service endpoint**

CREATE MOVIE  
  
*Request Method:* POST  
*URL:* /rest/movie  
*Body:*  
An xml in this form:  
```xml
<createMovie>
	<title>Star Wars: The empire strikes back</title>
	<description>m</description>
	<genre>sci-fi</genre>
</createMovie>
```
*Response:* JSON of the created movie  

CREATE MOVIE WTIH REQUEST PARAM  
  
*Request Method:* POST  
*URL:* /rest/movie  
*Body:*  
Key: "inputxml":  
Value:   
```xml
<createMovie>
	<title>Star Wars: The empire strikes back</title>
	<description>m</description>
	<genre>sci-fi</genre>
</createMovie>
```
*Response:* JSON of the created movie  

**Postman request**
Create movie with XML and Create Movie with XML param

**Detailed description**
Once you are logged in you can create movies in the database. You can  create a movie from JSON or form XML. For some reason there are two ways to send an xml: send it in the body of a POST request with Content-type: application/xml, or send it as a request parameter. One of these is vulnerable to a special xml-related attack. Find out which one! Exploit the vulnerability and fix it! Discuss why only one of the two services was vulnerable!

### Exercise  –
**Short Description**

**Service endpoint**

*Request Method:* GET  
*URL:* /rest/  
*Response:*  

**Postman request**

**Detailed description**
