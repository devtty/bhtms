# Moderne Softwareentwicklung


## Einsendeaufgabe: Continous Integration

Denis Renning (BHT 914556)

### Aufgabenstellung

Bringen sie ein CI System wie Jenkins mit etwas Logik beweisbar zum laufen.

### Und wieder NEB27

Für diese Aufgabe habe ich mir wieder das bereits aus den vorherigen bekannte NEB27 Projekt herausgenommen, dessen CI Builds ursprünglich mittels Travis-CI erledigt wurden. Die entsprechende Konfiguration findet man [hier](https://github.com/devtty/neb27/blob/master/.travis.yml). Zusammengefasst überwachte TravisCI das Repository auf Änderungen und baute mittels Maven und Java8 das Webprojekt, im Anschluss wurden noch die Fahrpläne vom Berliner Verkehrsverbund heruntergeladen und im zu deployenden Paket entpackt. Danach wurde das Ganze in einer OpenShift-Instanz bereitgestellt. Leider ist das bereits Jahre her und seit dem führte Github die Workflows ein und die Unterstützung für TravisCI änderte sich auch ein wenig (bspw. war es mir im Rahmen dieser Aufgabe nicht mehr möglich einen kostenlosen Zugriff auf TravisCI zu ekommen). Als Alternative nun die Beschreibung für die Erzeugung des WebArchives mit Hilfe eine lokalen Jenkins-Installation

### Jenkins

Den Jenkins-Server habe ich frisch als heruntergeladen (generic-war) und mittels 'java -jar jenkins' gestartet und installiert. Nachdem der Server startete mussten erst einmal in den Einstellungen (zu den Hilfsprogrammen) das JDK und die Maven-Installation eingestellt werden. Sowohl JDK als auch Maven waren bereits vorhanden.

![JDK settings](./jenkins_jdk.png)
![Maven settings](./jenkins_mvn.png)

Danach musste das Projekt (als Freestyle-Projekt) erstellt werden.
![Create Element](./jenkins_createElement.png)

In den Allgemeinen Einstellungen wurde ein Name vergeben.

![general](./jenkins_general.png)

Dann wurde der Pfad zum git-Repostory eingestellt. Credentials müssen nicht angegeben werden, da auch ein anonymes auschecken des Projektes möglich ist.
![git conf](./jenkins_git.png)

Empfehlenswert (zumindest bei kleineren Projekten) ist es immer mit einem sauberen Arbeitsplatz zu beginnen und die Projektquellen komplett neu herunterzuladen.  
![clean workspace](./jenkins_cleanwork.png)

Die zeitliche Abfrage der Quellen emuliert in etwas das Verhalten der alten TravisCI-Builds bei etwaigen Änderungen am Quellcode automatisch einen Build anzustoßen.
![time settings](./jenkins_time.png)

Als Maven-Ziel wird dann die Anwendung zusammengebaut
![goal](./jenkins_goal.png)

und das entstehende WAR-Paket für später (und zum Download) aufgehoben
![post build](./jenkins_pb.png)

Nach Abspeichern der Konfiguration und einem Klick auf den "Jetzt bauen"-Knopf ist kurze Zeit später das Projekt fertig für den Download.
![finish](./jenkins_finish.png)