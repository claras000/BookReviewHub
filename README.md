# BookReviewHub
## Eine Java Spring Webapp für Buchrezensionen

Mit der der Backend Applikation lassen sich Bücher und ihre Rezensionen speichern.
User können Rezensionen für Bücher schreiben.

## Implementierung

Diese Backend Anwendung nutzt das MVC Model
und hat eine Postgres DB implementiert in denen die Daten persisiert werden.

Für Tests wird hier Mockito genutzt und für die saubere Datenanbindung zur DB
wird Hilbernate als JPA-Implentierung genutzt.
Über REST API Schnitstellen in den Contollern kann die Anwendung mit dem Frontend kommunizieren.

Das Repo befindet sich gerade in der Bearbeitung. Weitere Tests für vollständige Abdeckung folgen.

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

- Queue: Asynchrone Verarbeitung von Reviews.
- Streams: Berechnung von Durchschnittsbewertungen und Ermittlung der Top-Bücher.
- Events: Benachrichtigungen oder Logs bei der Erstellung neuer Reviews.
- Cache: Performance-Optimierung durch Spring Cache.
- Scheduler: Automatische Berechnung und Aktualisierung von Statistiken.