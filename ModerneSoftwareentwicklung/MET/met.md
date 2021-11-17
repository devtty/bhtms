Metriken
===

Die Diagramme sind angelehnt an eine (existierende) Software zur Verwaltung eines Logistik-Lagers. 

1. Use-Case-Diagram
-------------------

Im Anwendungsfalldiagramm werden nur die Haupttätigkeiten der 3 Hauptmitarbeiter-Typen dargestellt. 

![Use-Case Diagram](./.png "Use Case")

2. Komponenten-Diagram
----------------------

Zur Verdeutlichung, die existierende Version nutzt derzeit OpenShift als Cloud-Provider und im System laufen folgende Instanzen:
- Datenbank (Postgres)
- Anwendungsserver (Wildfly)
- AuthProvider (Keycloak)

Im Anwendungsserver läuft eine Webanwendung die mittels einer Reporting-Komponente (jasperreports) Berichte generiert und dem (autorisierten) Nutzer zur Verfügung stellt oder ggf. per Mail zusendet.



3. Klassendiagramm
----------------------

Das Klassendiagramm stellt die im Prozess der "Auftragserteilung" bzw. "Rechnungslegung" beteiligten Haupt-Klassen dar. Die Ansicht ist etwas vereinfacht, bspw. wurde auf die Darstellung der gekapselten Datenfelder verzichtet. Als Beispiel ist nur Artikel-Klasse mit zwei Datenfeldern vertreten.

![Class Diagram](./SW_class.png "Class")

#Moderne Softwareentwicklung


##Einsendeaufgabe: Metriken

Denis Renning (BHT 914556)


Für diese Einsendeaufgabe hole ich mal ein älteres Migrationsprojekt raus, welches ich vor einigen Jahren mal vergaß weiter nachzuverfolgen. Die Quellen finden Sie unter https://github.com/devtty/store . Es handelt sich um ein JavaEE-Projekt (JPA/JSF/JDK8) bei dem der Build-Prozess per Apache Maven anläuft. Ebenso kann eine Maven-typische auch eine Dokumentation erzeugt werden. Der Build-Prozess ist so definiert dass die Dokumentation lokal erzeugt werden kann und ggf. auf die Seite http://store.devtty.de/ hochgeladen wird. Dort finden Sie im Bereich Project Reports die Auswertungen zu:


###CPD
![CPD](./cpd.png "CPD")

###PMD
![PMD](./pmd.png "PMD")

###SureFire
![SureFire](./surefire.png "CPD")

###FindBugs
![FindBugs](./findbugs.png "Findbugs")

###l10n Status
![l10n](./l10n.png "CPD")

###SonarCloud
Des Weiteren ist per Webhook auch Sonarqube per Sonarcloud eingebunden. Die entsprechenden Ergebnisse finden Sie unter 

https://sonarcloud.io/summary/overall?id=devtty_store
![SonarCloud](./SonarCloud.png "SonarCloud")
