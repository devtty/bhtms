# Moderne Softwareentwicklung


## Einsendeaufgabe: AOP & MS

Denis Renning (BHT 914556)

### Aufgabenstellung

Entweder:
A) Bringen sie AspectJ zum Laufen! Denken sie sich selbst einen interessanten kleinen eigenen (!) AOP Anwendungsfall aus, den sie mit AspectJ ausprobieren und kurz beschreiben (incl. z.B. IDE Screenshot). (Dies sollte in weniger als 30 Minuten zu schaffen sein!)
Bonuspunkte gibt es wie immer für alles Mögliche was darüber hinausgeht.

oder

B) Spielen sie mit Microservices! (auch mit den API Tools wie swagger, kong, Dropwizard, etc.). Bauen sie selbst einen kleinen mit Nano-Funktionen ;-)
Gerne auch auf Docker Deployen und "irgendwie" kommunizieren lassen...

oder

C) Wenn sie AWS Affin sind, erstellen sie einen einfachen Lambda Code / Service der etwas einfaches tut.
Z.B. Datei aus S3 lesen und Wörter / Zeichen zählen und in zweite Datei schreiben...
Hier gibt es viele Web Beispiele (die aber bitte nicht 1:1 kopieren! ;-)



### Und (schon) wieder NEB27

Dieses Mal möchte ich dass aus den vorherigen Aufgaben bekannte Projekt nutzen um eine Lösung die irgendwo zwischen Aufgabenstellung B und C angesiedelt ist bereit zustellen. Bei dem Referenzprojekt (NEB27) handelte es sich bisher um eine (monolithische) JavaEE7-Webanwendung welche nun teilweise einen Rewrite a la Microservices erhält. (die Twitterfunktion lass ich aber unter den Tisch fallen :smile: ). Bei der Umsetzung habe ich mich für [Quarkus](https://quarkus.io) für die Anwendungen und [RedHat OpenShift](https://www.redhat.com/de/technologies/cloud-computing/openshift) als Plattform entschieden.

Als erstes wurde die Anwendung in zwei Teile (Services/Dienste) geteilt, jeder sehr einfach gehalten und mit eigener Zuständigkeit (Single Reponsibility).

 - [neb27q](https://github.com/devtty/bhtms/tree/main/ModerneSoftwareentwicklung/MS/neb27q) - Service der notwendige Daten bei der Bahn abfragt
 - [neb27t](https://github.com/devtty/bhtms/tree/main/ModerneSoftwareentwicklung/MS/neb27t) - Service der Daten an IoT-Endpunkt weitergibt

Im Aufbau entspricht jeder Teil in etwa dem Quarkus-Beispielprojekt (Maven-Archetyp) bei dem eine Klasse für die Funktionalität sorgt. Neben der Möglichkeit den Blueprint für den Microservice über Maven-Archetypes zu erzeugen besteht auch die Möglichkeit des Downloads über [https://code.quarkus.io/](https://code.quarkus.io/). Für neb27q bzw. neb27t werden noch die Erweiterungen "quarkus-resteasy" und "quarkus-openshift" benötigt um notwendige Kommunikation zwischen den Diensten zu gewährleisten. Des Weiteren benötigt neb27t die Erweiterung "quarkus-scheduler" da es sich um einen zeitbasierten Dienst handelt. Die Erweiterungen können mittels

''' bash
./mvnw quarkus:add-extension -Dextensions="quarkus-resteasy, ...
'''

dem jeweiligen Projekt hinzugefügt werden. 

![import](./ms_1import.png)

![Hello Micro](./ms_2helloms.png)

![Build and Push](./ms_3buildpush.png)

![Next one please](./ms_4next.png)


