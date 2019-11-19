# URL Shortening Service Project

## REST Endpoints:

### Shorten url: /urlshortsvc/rest/shortenurl/
Example: http://localhost:8090/urlshortsvc/rest/shortenurl/?url=www.intuit.com

### Redirect to long url: /urlshortsvc/rest/{shortId}
Example: http://localhost:8090/urlshortsvc/rest/b

## Instructions For Running
### Project Root Directory
* urlshortsvc

### Create below text file
* id_url_file.txt
* Update path for text file in java class -
  urlshortsvc/src/main/java/com/urlshorteningservice/bo/FileHandler.java

## Maven Dependencies:
* Jersey: 2.27
* Spring: 4.3.4.RELEASE
* EasyMock: 3.1
* Tomcat: 8.5

### Url Shortening Service Home Page
![Home Page](/WebApplication%20Screens/Home_Page.png)

### Url Shortening Service Result Page
![Result Page](/WebApplication%20Screens/Result_Page.png)
