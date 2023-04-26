# RPG Battle
Back-end for RPG battle application using Java, Spring Boot and H2 database.

---

![Cover: API REST Java Spring Boot](/assets/images/cover.png)

### Environment:
* Java 17 SDK (required)
* IntelliJ IDEA Community Edition (suggested)
* H2 Database (automatically created when application is started)

### How to access the back-end:
* Swagger: http://localhost:8080/swagger-ui/index.html
* H2 database: http://localhost:8080/h2

### How to run the back-end application:
* Clone the source code to your local workspace
* Open the project in your IDE
* Load the Maven dependencies
* Setup the startup settings to point out to "com.avanade.rpgbattle.RpgbattleApplication" class
* Run the application in your IDE
* *Note: If it is the first time you are running the application, please also follow the steps mentioned in the section "How to load initial settings in the H2 database"*

### How to play the game:
* POST /api/battles
* POST /api/battles/{id}/initiative
* POST /api/dices/throw
* POST /api/battles/{id}/attack
* POST /api/battles/{id}/defense
* POST /api/battles/{id}/damage
* **TODO** ...

### How to load initial settings in the H2 database: 
The following SQL Scripts should be executed only once in the database in order to preload the initial characters:

* Heroes:
```` sql
INSERT INTO HEROES (NAME, DESCRIPTION, HEALTH_POINTS, STRENGTH_POINTS, DEFENSE_POINTS, AGILITY_POINTS, DICE_QUANTITY, DICE_FACES, CREATED_AT, IS_SYSTEM_GENERATED)
VALUES ('Warrior', '', 20, 7, 5, 6, 1, 12, CURRENT_TIMESTAMP, 1);

INSERT INTO HEROES (NAME, DESCRIPTION, HEALTH_POINTS, STRENGTH_POINTS, DEFENSE_POINTS, AGILITY_POINTS, DICE_QUANTITY, DICE_FACES, CREATED_AT, IS_SYSTEM_GENERATED)
VALUES ('Barbarian', '', 21, 10, 2, 5, 2, 8, CURRENT_TIMESTAMP, 1);

INSERT INTO HEROES (NAME, DESCRIPTION, HEALTH_POINTS, STRENGTH_POINTS, DEFENSE_POINTS, AGILITY_POINTS, DICE_QUANTITY, DICE_FACES, CREATED_AT, IS_SYSTEM_GENERATED)
VALUES ('Knight', '', 26, 6, 8, 3, 2, 6, CURRENT_TIMESTAMP, 1);
````

* Monsters:
```` sql
INSERT INTO MONSTERS (NAME, DESCRIPTION, HEALTH_POINTS, STRENGTH_POINTS, DEFENSE_POINTS, AGILITY_POINTS, DICE_QUANTITY, DICE_FACES, CREATED_AT, IS_SYSTEM_GENERATED) 
VALUES ('Orc', '', 42, 7, 1, 2, 3, 4, CURRENT_TIMESTAMP, 1);

INSERT INTO MONSTERS (NAME, DESCRIPTION, HEALTH_POINTS, STRENGTH_POINTS, DEFENSE_POINTS, AGILITY_POINTS, DICE_QUANTITY, DICE_FACES, CREATED_AT, IS_SYSTEM_GENERATED) 
VALUES ('Giant', '', 34, 10, 4, 4, 2, 6, CURRENT_TIMESTAMP, 1);

INSERT INTO MONSTERS (NAME, DESCRIPTION, HEALTH_POINTS, STRENGTH_POINTS, DEFENSE_POINTS, AGILITY_POINTS, DICE_QUANTITY, DICE_FACES, CREATED_AT, IS_SYSTEM_GENERATED) 
VALUES ('Werewolf', '', 34, 7, 4, 7, 2, 4, CURRENT_TIMESTAMP, 1);
````
