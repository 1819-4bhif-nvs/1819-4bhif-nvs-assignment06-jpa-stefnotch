# Stefan Brandmair - Krankenhaus

Dieses Programm befasst sich mit der Verwaltung eines Krankenhauses. Derzeit befasst es sich nur mit Patienten (Patient), Doktoren (Doctor) und Behandlungen (Treatment)

### Klassendiagramm

![CLD](C:\Users\Stefnotch\Documents\GitHub\School\1819-4bhif-nvs-assignment06-jpa-stefnotch\krankenhaus_jpa\CLD.png)



### Entitaetendiagramm

![ERD](C:\Users\Stefnotch\Documents\GitHub\School\1819-4bhif-nvs-assignment06-jpa-stefnotch\krankenhaus_jpa\ERD.png)





# Notes for myself



## Starting derby

```
mkdir db
cd db

D:\opt\derby\bin\startNetworkServer -noSecurityManager
```

## Starting h2

```
Seems to not be required?
```



# Angabe

Übungsbeispiel mit individueller Aufgabenstellung zum Thema JPA

# Aufgabenstellung

- Jakarta EE Applikation
- Erstellen Sie (oder erweitern Sie) Ihr individuelles Datenmodell mit mindestens zwei abgeleiteten Klassen und insgesamt ca. fünf Tabellen.
- Erstellen Sie eine CRUD-Applikation (restful services) für die vererbten Klassen. Weiters ausgehend von Ihrem individuellem Thema ev. noch weitere notwendige Endpunkte.
- Ein Datum muss in Ihrem REST-Endpoint
- Verwenden Sie eine DerbyDb sowie eine H2
- Erstellen Systemtests auf Basis JavaSE.
