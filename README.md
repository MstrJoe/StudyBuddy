# Installatiehandleiding:


Clone de repo van de onderstaande url door de volgende command te runnen in je terminal:

```bash
git clone git@github.com:MstrJoe/StudyBuddy.git
```

De repo bestaat uit 2 folders, `backend` en `frontend`.

## Database

Voor de backend heb je een postgres database nodig.
Installeer hiervoor een postgres server. Voor Mac zou je hiervoor [Postgres.app](https://postgresapp.com) kunnen gebruiken.

Zodra de postgres server runt, moet er een database aangemaakt worden. Het makkelijkst is om hiervoor een database gui tool te gebruiken, bijvoorbeeld [Table Plus](https://tableplus.com)

Maak een nieuwe connectie aan. Standaard draait postgres op host `127.0.0.1` en port `5432`. In de meeste gevallen zijn zowel de username als password `postgres`, maar als je anders hebt ingesteld vul je hier je zelf gekozen username en wachtwoord.

Maak in de database gui tool een database aan genaamd `studybuddy`.

Screenshot

In de backend folder zit een file met de filename `database.sql` met initiele data. Deze moet geimporteerd worden in de zojuist aangemaakte database.

Kies `File -> Import -> Import from SQL` en kies daar het `database.sql` bestand.

Je zou nu een database moeten hebben met initiële data.

Mocht je username of wachtwoord afwijken van de standaard moet deze gewijzigd worden in het bestand [backend/src/main/resources/application.properties](backend/src/main/resources/application.properties).

Verander hiervoor de regels:

```
spring.datasource.url = jdbc:postgresql://localhost:5432/studybuddy
spring.datasource.username = postgres
spring.datasource.password = postgres
```

## Backend

Zorg dat je Java geinstalleerd hebt https://www.java.com/en/download/manual.jsp.

Download en installeer IntelliJ.

Open de backend map in IntelliJ. Druk op `ctrl+r` om de applicatie te starten. De applicatie zou nu moeten draaien op [http://localhost:8080](http://localhost:8080)

## Frontend

Voor de frontend moet Node.js geinstalleerd zijn. Download node van [de website](https://nodejs.org/en/) en installeer deze. Dit installeert ook gelijk npm, de package manager van node.

Download en installeer Webstorm.

Open de frontend folder met Webstorm. Installeer de dependencies door de volgende command te runnen in je terminal

```
npm install
```

Run de dev server door de volgende command te runnen.

```
npm run dev
```

De server zou moeten draaien op [http://localhost:3000](http://localhost:3000)

## Applicatie

Open de frontend applicatie door in je browser naar [http://localhost:3000](http://localhost:3000) te gaan.

Inloggen kan met 2 testgebruikers:

- Als student: email `student@studybuddy.nl` en wachtwoord `studybuddy`
- Als leraar: email `teacher@studybuddy.nl` en wachtwoord `studybuddy`

## Endpoints

Hieronder staan alle endpoints. Een mooi overzicht van de endpoints zijn ook beschikbaar als swagger documentatie op [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

- GET /users/me - Returned de huidig ingelogde users