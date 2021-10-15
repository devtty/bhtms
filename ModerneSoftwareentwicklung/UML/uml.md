UML
===

Die Diagramme sind angelehnt an eine (existierende) Software zur Verwaltung eines Logistik-Lagers. 

1. Use-Case-Diagram
-------------------

Im Anwendungsfalldiagramm werden nur die Haupttätigkeiten der 3 Hauptmitarbeiter-Typen dargestellt. 

![Use-Case Diagram](./SW_use.png "Use Case")

2. Komponenten-Diagram
----------------------

Zur Verdeutlichung, die existierende Version nutzt derzeit OpenShift als Cloud-Provider und im System laufen folgende Instanzen:
- Datenbank (Postgres)
- Anwendungsserver (Wildfly)
- AuthProvider (Keycloak)

Im Anwendungsserver läuft eine Webanwendung die mittels einer Reporting-Komponente (jasperreports) Berichte generiert und dem (autorisierten) Nutzer zur Verfügung stellt oder ggf. per Mail zusendet.

![Component-Diagram](./SW_comp.png "Component")

3. Klassendiagramm
----------------------

Das Klassendiagramm stellt die im Prozess der "Auftragserteilung" bzw. "Rechnungslegung" beteiligten Haupt-Klassen dar. Die Ansicht ist etwas vereinfacht, bspw. wurde auf die Darstellung der gekapselten Datenfelder verzichtet. Als Beispiel ist nur Artikel-Klasse mit zwei Datenfeldern vertreten.

![Class Diagram](./SW_class.png "Class")