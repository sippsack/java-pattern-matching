# Demoprojekt für Pattern Matching mit Java

Es gibt verschiedene Packages:

## de.sippsack.badtelefon.v0

* Ausgangsbeispiel, Klon von
* einfacher Telefontarif-Rechner
* Logik nur in der Klasse Kunde, viele Code-Smells
* Pseudo-Enumeration mit Integer-Konstanten in Tarif
* keine wirkliche Domänenstruktur, Berechnungslogik komplett außerhalb

## de.sippsack.badtelefon.v1

* eigene Domänentypen für die Tarifarten
* Geschäftslogik in Domänenklassen (Strategie-Muster)
* Einsatz der Schablonenmethode zur Vermeidung von Redundanzen

## de.sippsack.badtelefon.v2

* Lösungsansatz mit dem **Visitor-Pattern**
* bisheriger Ansatz in OOP, wenn man die Klassen selbst nicht ändern konnte und/oder man alternative Vorgehensweisen (
  zweiter Visitor) umsetzen muss