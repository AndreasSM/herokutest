Enkel guide til å deploye til Heroku
====================================

Dette er en liten guide til å komme igang med Heroku. Her vil vi starte med et temmelig tomt prosjekt, konfigurere det til å fungere på Heroku og så peke videre til hvor man kan øve seg på mer avanserte ting.

Kjøre prosjektet lokalt
-----------------------

Dette må være installert:
###
* git
* JDK 1.8
* Maven 3


Slik går man i gang:
###
* Klon prosjektet `git clone https://github.com/AndreasSM/herokutest.git`
* Gå inn i mappen og kjør `mvn clean install`
* Kjør prosjektet: `java -jar target/herokutest-1.0-SNAPSHOT-jar-with-dependencies.jar`

Skal nå kunne få opp en "Hello world!" melding på http://localhost:8080
Nettsiden ligger i src/main/resources/webapp/index.html og kan naturligvis fritt endres

Få prosjektet til å kjøre på Heroku
-----------------------------------

For at prosjektet skal kunne kjøre i Heroku må man legge til en fil som sier hvilken oppstartskommando Heroku skal kjøre for å starte applikasjonen. Lag en til ved siden av pom.xml som heter "Procfile", og legg til innholdet: `web: java -jar target/herokutest-1.0-SNAPSHOT-jar-with-dependencies.jar`

I tillegg trenger Heroku å kunne sette hvilken port applikasjonen skal kjøre på. Skriv om "WebServer.java" til å hente inn miljøvariabel for portnummer i funksjonen "getPort". 
`PORT` er en miljøvariabel som settes i Heroku. 


```java
if (System.getenv("PORT") != null) {
    return Integer.parseInt(System.getenv("PORT"));
}
```

Nå er applikasjonen klar til å legges over på Heroku. Det gjøres slik:
###
* Lag konto hos Heroku (https://www.heroku.com/)
* Trykk på "create app" knappen og gi den et navn
* Installer "Heroku CLI" (lenke fra nettsiden du kommer til)
* Etter installasjonen, åpne kommandolinjeverktøy og kjør følgende kommandoer
    * `Heroku login`
    * `git add .`
    * `git commit -m "Lagt inn støtte for Heroku"`
    * `heroku git:remote -a [*app navn*]`
    * `git push heroku master`
    
Dersom du skulle oppleve problemer med den siste kommadoen (at du f.eks har installert en manager for git passord) så kan du komme rundt det ved å fylle inn brukernavn og passord manuelt. Brukernavn skal være blankt, mens for å få tak i passordet må du kjøre `Heroku login` etterfulgt av `heroku auth:token`. Svaret her er da passordet.

Gratulerer! Du skal kunne gå inn på https://[*app navn*].herokuapp.com/ og se innholdet i din index.html-fil.

Videre arbeid
-------------
For videre arbeid (Oppsett av database + større applikasjon) så henvises det til https://github.com/steria/openright-simple-server-seed
Her kan dere lese i README.md hvordan applikasjonen fungerer og få noen forslag til oppgaver.
Om man ønsker kjøre lokalt trenger man PostgreSQL installert.

Alternativt kan en jobbe videre med eget prosjekt =)