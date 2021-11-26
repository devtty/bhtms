# Moderne Softwareentwicklung


## Einsendeaufgabe: Clean Code Development

Denis Renning (BHT 914556)

### Teil A: Cheat Sheet

[CCD Cheat Sheet](./ccd.pdf)

### Teil B: Refactoring

Für die zweite Aufgabe habe ich mir wieder ein altes Spiel-Projekt herausgepickt um es, gemäß der Aufgabenstellung, an einigen Stellen *von verschmutzt auf Clean zu refactoren*. Die Anwendung
ist eine Webanwendung die in regelmäßigem Zeitabstand die Webseite der Niederbarnimer-Eisenbahn nach Verspätungsnachrichten und Abfahrtsdaten der Bahnlinie NEB27 aus der DB-Fahrbahnauskunft zieht und per [Twitter-Account](https://twitter.com/neb27k) (und an mein im Wohnzimmer stehendes [LaMetric](https://www.lametric.com)) über Verspätungen informiert.

Die entsprechenden CCD-Änderungen wurden im Feature-Branch "CCDRefactor" getätigt und sind über den [Pull Request #5 CCD Refactor](https://github.com/devtty/neb27/pull/5) einsehbar.

#### Remove magical numbers

[4902ac1](https://github.com/devtty/neb27/pull/5/commits/4902ac1df7a5821c64827836e6e2ee283d4b5047)

- In der Klasse `CheckChanges` wurden zwei Magical Numbers durch Konstanten ersetzt, die die Anfangs- und Endmarkierungen für die Textabschnitte die auf der NEB-Webseite beinhalten.
- Die Twitter-API versendet Antworten aufgrund der hohen Anzahl von möglichen Anworten blockweise. In den Klassen `CheckTwitterMessages` und `Vbb` existierte daher eine Magical Number mit dem Wert 1, die hier dazu diente nur die Anworten des ersten Blocks zu erhalten. Diese wurde ersetzt durch eine, in die Klasse `Constants` eingeführte Konstante `PAGING_OFFSET`. Da ein Antwort-Block mehrere Nachrichten enthält und nur die erste Nachricht gewünscht wird, erfolgt der Aufruf durch

```java
new Paging(Constants.PAGING_OFFSET).get(0)
```
Theoretisch könnte man nun die `0` ebenfalls als Magical Number betrachten, halte ich persönlich allerdings für eine unnötige Information, da Listenzugriffe mit `get(0)` auf das erste Element eigentlich eindeutig als solche erkennbar sind.
- In der Klasse `Vbb` wird die Fahrplanauskunft (HAFAS) nach Abfahrtszeiten abgefragt (wie bei der LED-Anzeige an der Haltestelle nun mit `HAFAS_QUERY_DEPARTURE_MAX_JOURNEYS` (3) der nächsten Abfahrten)
- Des Weiteren wird ebenfalls in Klasse `Vbb` der Status des REST-Requestes mit einer magical number `== 200` überprüft, die besser durch `Response.Status.OK.getStatusCode()` aus der RESTful Web Services-API ersetzt wird
- Anschließend wurde in der gleichen Klasse noch ein weitere String-Offset und ein Faktor für Zeitberechnungen ersetzt


#### choose descriptive/unambiguous names

[8dd352f](https://github.com/devtty/neb27/pull/5/commits/8dd352f2da1ca70c533c114a00b92cb81bc3b46f)

|Wo|alt|neu|Bemerkung|
|--|---|---|----|
|CheckChanges#checkMainPage|doc|mainPage|
|CheckChanges#checkMainPage|line|messageList|
|checkChanges#checkMainPage|e|message|
|checkChanges#notifyClients|c|change|
|CheckTwitter#execute|last|lastTweetId|
|CheckTwitterMessages#respond|nextService|queryNextServiceAndReturnMessage|
|LaMetric#push|str|messageText|
|LaMetric#push|client|restClient|
|Vbb#nextService|nextService|queryNextServiceAndReturnMessage|
|Vbb#nextService|id|stationName|
|Vbb#nextService|client|restClient|
|Vbb#nextService|rl|locationResponse|
|Vbb#nextService|r|returnMessage|
|Vbb#nextService|extId|alternativeStopId|
|Vbb#calculateLate|late|lateMessage|
|Vbb#calculateLate|date|departureDate|
|Vbb#calculateLate|time|departureTime|
|Vbb#calculateLate|rtDate|rtDate|nicht geändert da der Name gleichlautend mit denen aus dem jaxb-generierten Package (siehe [com.devtty.neb27k.jaxb.Departure](https://github.com/devtty/neb27/blob/2af55a238f5663409585513f8cec8cb2301b4502/src/main/java/com/devtty/neb27k/jaxb/Departure.java#L78))
|Vbb#calculateLate|rtTime|rtTime|analog rtDate
|Vbb#calculateLate|df|dateFormat|
|Vbb#calculateLate|date1|departure|
|Vbb#calculateLate|date2|realtime|

[19773b7](https://github.com/devtty/neb27/pull/5/commits/19773b7e00cc2ca01e6f6f64daabcc1ac6dafc6b)

In diesem Commit wurde die Klasse `Var` (und ihre Variable) in `TwitterContentProxy` umbenannt, da der alte Name nicht sehr beschreibend für die Aufgabe der Klasse (Twitter-Infos zwischen zuspeichern) war. Des Weiteren hätte es in späteren Versionen des JDKs auf Verwechslungen mit dem neu hinzugekommenen Schlüsselwort `var` geben können.


#### meaningful comments and delete unnecessary ones

[059688d](https://github.com/devtty/neb27/pull/5/commits/059688ddb4c6214dcff52a81b29d4807b811c9ae)

In der Klasse CheckChanges existierte eine auskommentierte Methode als nicht mehr benötigte Altlast, diese wurde gelöscht. Zusätzlich wurde der Methode `checkMainPage` mit einem internen Kommentar versehen, der "MainPage" etwas genauer spezifiziert.

In der Klasse CheckTwitterMessage wurde eine alter TODO-Kommentar entfernt, welcher für die Entwicklung angedacht war (Twitter-Mentions sollten automatisch beantwortet werden statt nur mit Sternchen versehen werden)

Die Klassen Constants, GtfsData, TwitterContentProxy und Vbb wurden mit Klassen-Kommentaren versehen um den Zweck zu erläutern. IDEs oder Checkstyle mögen Klassenbeschreibungen ebenfalls :blush:

In der Klasse TwitterContentProxy wurde noch ein interner Entwickler-Kommentar zur Methode `health()` hinzugefügt (ist ja kein *release*-Code)

#### Exceptions catch them fast and early

[61917bd](https://github.com/devtty/neb27/pull/5/commits/61917bda59f5a6378a8714b298b64a6e83b5c79c)

In den Methoden `notifyClients()` und `getTweetsToday()` der Klasse `CheckChanges` wurden Funktionalitäten der Twitter-API genutzt und das Exception-Handling per `throws` einfach an die aufrufende Methode `execute()` weitergeleitet.
Das widersprach natürlich dem Grundsatz diese möglichst früh (und präzise) zu verarbeiten. 


#### better readability for loop
[f95f9d8](https://github.com/devtty/neb27/pull/5/commits/f95f9d88fadd77ff400c03dc95bb860bf6489030)

Hier wurde ein for-loop in eine etwas besser lesbare Form gebracht.


#### extract method example

[147706f](https://github.com/devtty/neb27/pull/5/commits/147706f3bb0c493bb083a7730144cdb3ea27a17a)

In der Methode notifyClients() der Klasse `CheckChanges` wurden zwei Methoden extrahiert (siehe [Refactor-Katalog](https://refactoring.com/catalog/extractFunction.html));

- `boolean isFromTodayAndNoRetweets(Status s)` prüft ob es sich um einen Tweet(Status) von heute handelt und dieser kein Retweet ist. In der alten Form war dieses (innerhalb der Lambda-Expression) nicht eindeutig sichtbar. Ist besser so.
- `Date getStartTimeOfToday()` liefert den aktuellen Tagesanfang. Mit Extraktion wurde gleichzeitig die Ermittlung des Wertes (JDK6 -> JDK8) überarbeitet.




