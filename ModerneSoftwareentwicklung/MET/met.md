# Moderne Softwareentwicklung


## Einsendeaufgabe: Metriken

Denis Renning (BHT 914556)


Für diese Einsendeaufgabe hole ich mal ein älteres Migrationsprojekt raus, welches ich vor einigen Jahren mal vergaß weiter nachzuverfolgen. Die Quellen finden Sie unter https://github.com/devtty/store . Es handelt sich um ein JavaEE-Projekt (JPA/JSF/JDK8) bei dem der Build-Prozess per Apache Maven anläuft. Ebenso kann eine Maven-typische auch eine Dokumentation erzeugt werden. Der Build-Prozess ist so definiert dass die Dokumentation lokal erzeugt werden kann und ggf. auf die Seite http://store.devtty.de/ hochgeladen wird. Dort finden Sie im Bereich Project Reports die Auswertungen zu:


### CPD
![CPD](./cpd.png "CPD")

### PMD
![PMD](./pmd.png "PMD")

### SureFire
![SureFire](./surefire.png "CPD")

### FindBugs
![FindBugs](./findbugs.png "Findbugs")

### l10n Status
![l10n](./l10n.png "CPD")

### SonarCloud
Des Weiteren ist per Webhook auch Sonarqube per Sonarcloud eingebunden. Die entsprechenden Ergebnisse finden Sie unter 

https://sonarcloud.io/summary/overall?id=devtty_store
![SonarCloud](./SonarCloud.png "SonarCloud")
