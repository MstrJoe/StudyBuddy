# Installatiehandleiding:

###Wat is er nodig om deze applicatie te runnen?

#####2 besturingssystemen:
- IntelliJ
- Webstorm

#####Database software:
- Postgres
- TablePlus

#####overige software:
- Java
- Node.js 

#####Broncode

Broncode van de applicatie.

## Broncode downloaden

Download de code van de volgende link: [https://github.com/MstrJoe/StudyBuddy](https://github.com/MstrJoe/StudyBuddy)

- Klik op de groene knop `Code` en vervolgens op `download zip.`. 
 
Zet het zipbestaand in een gewenste map naar keuze en klik dubbel op het bestand.   
De zip zal uitgepakt worden en de inhoud zal zichtbaar zijn.
Deze inhoud bestaat uit het volgende:

Een map genaamd `StudyBuddy-main` met de volgende inhoud:  
	
- map broncode genaamd `Backend`
- map broncode genaamd `Frontend`
- de `readme` (deze installatiehandleiding)
- een database bestand genaamd `studybuddy.sql`


## Backend voorbereiden


Zorg dat je `Java` geinstalleerd hebt. 
Java al geinstaleerd op je computer? dan kan je deze stappen overslaan en doorgaan naar de installatie van `IntelliJ`.

1. Download het Java intallatie bestand. [https://www.java.com/en/download/](https://www.java.com/en/download/)
2. Open het bestand door er op dubbel te klikken.
3. Loop de installatie door en java zal geinstalleerd zijn.

Download en installeer `IntelliJ`

1. Het programma is te downloaden van de volgende link:
[https://www.jetbrains.com/idea/download/#section=mac](https://www.jetbrains.com/idea/download/#section=mac)
2. Kies het juiste besturingssysteem voor jouw computer en download het bestand.
3. Open het bestand op de computer door er dubbel op te klikken.
4. Loop de installatiewizard door en de software zal geinstalleerd zijn.

Hulp nodig bij het doorlopen van de installatie?
gebruik hiervoor deze link: [https://www.jetbrains.com/help/idea/installation-guide.html#standalone](https://www.jetbrains.com/help/idea/installation-guide.html#standalone)

Open IntelliJ en selecteer Open. 

Open vervolgens de broncode map `backend` die te vinden is in de map `StudyBuddy-main` die je eerder hebt gedownload.

Het back-end gedeelte staat nu klaar.



## Frontend voorbereiden

Voor de frontend moet `Node.js` geinstalleerd zijn.  
Node is te downloaden van de volgende link:[https://nodejs.org/en/download/](https://nodejs.org/en/download/) 

1. Klik op het voor jouw juiste besturingssysteem en het bestand zal gedownload worden.
2. Open het bestand door er dubbel op te klikken.
3. Loop de installatie wizard door en Node zal geinstalleerd zijn.
(Dit installeert ook gelijk npm, de package manager van node.)

Download en installeer `Webstorm`

1. Het programma is te downloaden van de volgende link:
[https://www.jetbrains.com/webstorm/download/#section=mac](https://www.jetbrains.com/webstorm/download/#section=mac)
2. Kies het juiste besturingssysteem voor jouw computer en download het bestand.
3. Open het bestand op de computer door er dubbel op te klikken.
4. Loop de installatiewizard door en de software zal geinstalleerd zijn.

Hulp nodig bij het doorlopen van de installatie?
gebruik hiervoor deze link: [https://www.jetbrains.com/help/idea/installation-guide.html#standalone](https://www.jetbrains.com/help/idea/installation-guide.html#standalone)

Open `Webstorm` en selecteer `Open`.  
Open vervolgens de broncode map `frontend` die te vinden is in de map `StudyBuddy-main` die je eerder hebt gedownload. 

Webstorm zal de code openen en hierna neem je de volgende stap:

Installeer de benodigde dependencies door de volgende command te runnen in je terminal (Deze zijn nodig om de code te kunnen runnen). 

Open de terminal (onderin) en type daarin de volgende zin:

```
npm install
```
Klik hierna op enter en het programma zal zijn werk doen.

Start de server(website) door de volgende command line te typen in de terminal en op enter te drukken.

```
npm run dev
```

De server zou nu gestart moeten zijn en zou moeten draaien op [http://localhost:3000](http://localhost:3000)

Het front-end gedeelte staat nu klaar.

## Database

Voor de backend heb je een `Postgres` database nodig.  
Installeer hiervoor een Postgres server.

Het makkelijkste voor Macbook is de `Postgres-App` versie, hier te downloaden:  
[https://postgresapp.com/downloads.html](https://postgresapp.com/downloads.html)

Anders de normale Postgres variant te downloaden vanaf hier:
[https://www.postgresql.org/download/](https://www.postgresql.org/download/)

Download en installeer de software en start deze op.

**Ter info:** Standaard draait Postgres op host `127.0.0.1` en port `5432`. In de meeste gevallen zijn zowel de username als password standaard `postgres`, maar als je anders hebt ingesteld vul je hier je zelf gekozen username en wachtwoord.

Mocht je username of wachtwoord van Postgres afwijken van de 'standaard', dan moet deze gewijzigd worden in het bestand in de backend code: klik hiervoor wanneer de backend code open staat in IntelliJ op deze link [backend/src/main/resources/application.properties](backend/src/main/resources/application.properties).

Verander hiervoor deze regels zodat ze de juiste log-in gegevens bevatten:

```
spring.datasource.url = jdbc:postgresql://localhost:5432/studybuddy
spring.datasource.username = postgres
spring.datasource.password = postgres
```
We gaan verder:

Maak binnen `Postgres` een nieuwe server met de naam `studybuddy` aan. 

**In de Postgres-App gaat dat als volgt:** (voor mac gebruikers)

* open de Postgres app.
* Klik op het plusje `+`
* Geef de server een naam
* Klik vervolgens op 'Create Server'

De server zou nu aangemaakt moeten zijn.

**In de normale PostgreSQL variant gaat dat als volgt:**  

* Open PostgreSQL
* Klik rechter-muisknop op `Servers`
* Kies voor `Register -> Server`
* Geef de server de naam 'studybuddy'
* Ga naar het tabblad connection
* vul als Host name/address in: `127.0.0.1` en controleer of de Port op `5432` staat.
* Vul bij wachtwoord `postgres` in of als je een eigen wachtwoord heb aangemaakt vul je deze in.
* klik op save

De server zou nu aangemaakt moeten zijn.

Nu moeten we de Test-Data toevoegen aan onze server.
Het makkelijkst hiervoor is om een database gui tool te gebruiken, bijvoorbeeld [TablePlus](https://tableplus.com)

Download en installeer `TablePlus`.

In de backend folder zit een file met de filename `database.sql` met initiele data. Deze moet geimporteerd worden in de zojuist aangemaakte database.

* Open `TablePlus`
* Klik dubbel op de aangemaakte server, die zou weergeven moeten worden.
* Nu het programma geopend is in de server klik je in de menubalk op `connection`.
* Kies nu voor `Open a database` en kies voor de database `studybuddy` 

De database zal nu geopend zijn en wordt weergeven.  
Om de Test data toe te voegen doen we nog een laatste stap. 

* Kies `File -> Import -> Import from SQL` en kies daar het `database.sql` bestand uit de gedownloade map `StudyBuddy-main`.

Je zou nu een database moeten hebben met initiële data.


## Applicatie opstarten

Als alle bovenstaande stappen gelukt zijn kunnen we de applicatie opstarten.

Binnen `IntelliJ` kun je rechtsboven op de 'play' knop klikken om de backend te starten of met de shortcut `ctrl+r`.
De applicatie zou nu moeten draaien op [http://localhost:8080](http://localhost:8080)

Open de frontend applicatie door in je browser naar [http://localhost:3000](http://localhost:3000) te gaan.

Inloggen kan met 2 testgebruikers:

- Als student: email `student@studybuddy.nl` en wachtwoord `studybuddy`
- Als leraar: email `teacher@studybuddy.nl` en wachtwoord `studybuddy`

Nu alles draait wens ik je veel plezier met het ontdekken van de applicatie.

## Endpoints

Hieronder een overzicht van alle endpoints.  

Een mooi overzicht van de endpoints is ook beschikbaar als swagger documentatie op [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/) wanneer de applicatie draait.

- GET /users/me - Returned de huidig ingelogde users