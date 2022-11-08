# TableDuplication
## About The Project
This is a demo project to remove duplicate elements in two tables.

## Built With
The front-end uses React framework
The back-end uses Spring Boot framework

## Prerequisites
To run the project, the following files must be provided:
- Client
1. Install Nodejs,npm, and React framework
2. an .env file must be provided in the client folder with the following variables:
`REACT_APP_API_URL=YOUR_BACKEND_URL:PORT`

- Server
1. Install Java JDK 11
2. an application.properties must be provided in the server/src/main/resources/ 
```
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

mybatis.type-aliases-package=ca.wonderfish.server.domain
mybatis.mapper-locations=classpath*:/mapper/*Mapper.xml

placeKeyApi=YOUR_PLACEKEY_API_KEY
PlaceKeyUrl=https://api.placekey.io/v1/placekey

```

## Installation And Run
### Without Docker
To run the front-end:
1. go into client folder: `cd client`
2. start the application: `npm run start`

To run the back-end:
1. go into server folder: `cd server`
2. use Maven to package the application to a jar file
3. go into the target folder
4. run `java -jar server-0.0.1-SNAPSHOT.jar`

### With Docker
To run the front-end:
1. go into client folder: `cd client`
2. build docker image: `docker build -t real-estate-front .`
3. Run the container: `docker run --rm --name real-estate-front -p 80:80 -d real-estate-front`

To run the back-end:
1. go into server folder: `cd server`
2. build docker image: `docker build -t real-estate-backend .`
3. Run the container: `docker run --rm --name real-estate-backend -p 8080:8080 -d real-estate-backend`

## Contact
Please contact robinyu9840@gmail.com if you have any questions, thanks.