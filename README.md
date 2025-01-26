# BookReviewHub
### Eine Java Spring Webapp für Buchrezensionen

Mit der Backend-Applikation lassen sich Bücher und ihre Rezensionen speichern.
User können Rezensionen für Bücher schreiben.

## Implementierung
Diese Backend-Anwendung nutzt das MVC-Modell
und hat eine Postgres-DB implementiert, in der die Daten persistiert werden.

Für Tests wird hier Mockito genutzt, und für die saubere Datenanbindung zur DB
wird Hibernate als JPA-Implementierung verwendet.
Über REST-API-Schnittstellen in den Controllern kann die Anwendung mit dem Frontend kommunizieren.
Über einen Aspect (Spring) wird eine Nachricht beim Erstellen einer neuen Review gelogged.


Das Repo befindet sich gerade in der Bearbeitung. Weitere Tests für eine vollständige Abdeckung folgen.


## Tech Stack
- Java 17
- Maven
- Spring
- Mockito
- Hilbernate
- Postgres, Docker-Desktop

## Autorin
Clara Correa


### weitere Ideen zu Umsetzung:

- [ ] Queue: Asynchrone Verarbeitung von Reviews.
- [ ] Streams: Berechnung von Durchschnittsbewertungen und Ermittlung der Top-Bücher. 
- [x] Events: Benachrichtigungen oder Logs bei der Erstellung neuer Reviews.
- [ ] Cache: Performance-Optimierung durch Spring Cache. 
- [ ] Scheduler: Automatische Berechnung und Aktualisierung von Statistiken.