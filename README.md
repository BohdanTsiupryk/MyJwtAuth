# MyJwtAuth
### Test task with jwt auth

Start app:
```
build: mvn install
run: java -jar target/OrilTestTask.jar
```

# Application properties
Just click to see [application properties file](src/main/resources/application.yml) 

[DB script](src/main/resources/usr.sql) - script for migration (written for **Oracle** db)

Default port `8080`

In **application.yml** you can change credential to DB and JWT properties `expirationTime(in seconds, default 3600)` and `secret`

Also you can change property `spring.jpa.hibernate.ddl-auto` to `create` for auto generate table  


# REST API
## Authorization:
* ### /registration
   * method `POST` 
   * body 
        ```
            {
              "login": "mail@mail.com",
              "password": "password",
              "firstName": "Jhon",
              "lastName": "Snow"
            }
        ```
   * response `{"token":"your.jwt.token"}`
---
 * ### /login
   * method `POST` 
   * body 
        ```
            {
              "login": "mail@mail.com",
              "password": "password"
            }
        ```
   * response `{"token":"your.jwt.token"}`
---
 * ### /logout
   * method `POST` 
   * headers `Authorization: Bearer your.jwt.token`
   * response `{"token":"your.jwt.token"}`
---
## User CRUD:
All actions needs jwt token 
 * ### /user - get all user
   * method `GET` 
   * headers `Authorization: Bearer your.jwt.token`
   * response 
    ```
        {
            "id": 1,
            "email": "mail1@mail.com",
            "firstName": "Ivan",
            "lastName": "Rak",
            "createdAt": "2020-10-22T02:18:44.141+00:00"
        },
        {
            "id": 2,
            "email": "mail2@mail.com",
            "firstName": "Bohdan",
            "lastName": "Hamster",
            "createdAt": "2020-10-22T02:18:44.141+00:00"
        }
   ```
---
 * ### /user/1 - get user by id
   * method `GET` 
   * headers `Authorization: Bearer your.jwt.token`
   * response 
    ```
        {
            "id": 1,
            "email": "mail1@mail.com",
            "firstName": "Ivan",
            "lastName": "Rak",
            "createdAt": "2020-10-22T02:18:44.141+00:00"
        }
   ```
---
 * ### /user/1 - delete user by id
   * method `DELETE` 
   * headers `Authorization: Bearer your.jwt.token`
   * response `204
---
 * ### /user - create new user
   * method `POST` 
   * headers `Authorization: Bearer your.jwt.token`
   * body 
   ```
       {
           "email": "mail2@mail.com",
           "password": "password",
           "firstName": "Bohdan",
           "lastName": "Hamster"
       }
   ```
   * response 
   ```
       {
           "id": 2,
           "email": "admin2@mail.com",
           "firstName": "Bohdan",
           "lastName": "Tsiupryk",
           "createdAt": "2020-10-22T02:18:44.141+00:00"
       }
   ```
---
 * ### /user/2 - change user info
   * method `PUT` 
   * headers `Authorization: Bearer your.jwt.token`
   * body 
   ```
       {
           "email": "mail3@mail.com",
           "password": "password2",
           "firstName": "Bohdan",
           "lastName": "Hamster"
       }
   ```
   * response 
   ```
       {
           "id": 2,
           "email": "mail3@mail.com",
           "firstName": "Bohdan",
           "lastName": "Hamster",
           "createdAt": "2020-10-22T02:18:44.141+00:00"
       }
   ```