# Demoprojekt für Pattern Matching mit Java

Es gibt verschiedene Packages:

## de.sippsack.badtelefon.v0

* Ausgangsbeispiel, Klon von
* einfacher Telefontarif-Rechner
* Logik nur in der Klasse Kunde, viele Code-Smells
* Pseudo-Enumeration mit Integer-Konstanten in Tarif
* keine wirkliche Domänenstruktur, Berechnungslogik komplett außerhalb

## de.sippsack.badtelefon.v1

_Erster Refactoringschritt_

* eigene Domänentypen für die Tarifarten
* Geschäftslogik in Domänenklassen (Strategie-Muster)
* Einsatz der Schablonenmethode zur Vermeidung von Redundanzen

## de.sippsack.badtelefon.v2

_raus, verwirrt glaube ich nur_

* ähnlich zu v1
* Domänenklassen sind in Enum-Konstanten aufgegangen
* Geschäftslogik ist auch in diesen Domänenklassen

## de.sippsack.badtelefon.v3

_raus, ich finde 3.1 hier besser. Damit können wir direkt mit IntelliJ Type Pattern einführen_

* Lösungsansatz mit dem **Visitor-Pattern**
* bisheriger Ansatz in OOP, wenn man die Klassen selbst nicht ändern konnte und/oder man alternative Vorgehensweisen (
  zweiter Visitor) umsetzen muss

## de.sippsack.badtelefon.v31

* Lösungsansatz mit dem **Visitor-Pattern** ähnlich wie v3
* allerdings **generischer Visitor mit Typparameter**
* Einsatz von instanceof und Typecasts

## de.sippsack.badtelefon.v4

* Lösung mit dem **Pattern Matching**
* konsequente Nutzung moderner Ansätze (Switch Expression, Sealed Classes, Records)
* Domänenstruktur in Sealed Classes, Geschäftslogik in Klasse Kunde via Pattern Matching for switch

## de.sippsack.badtelefon.v5

* Lösung mit dem **Pattern Matching** ähnlich wie v4
* Versuch zu einem feingranulareren Domänen-Modell für bessere Les-, Wart- und Wiederverwendbarkeit

## Schritte zur Lösung

1. **PreisBerechnungsVisitor** instanceof & cast durch Type Patterns ersetzen
2. **PreisBerechnungsVisitor** If Kaskade durch switch (old-schoole) ersetzten
    * {} sind notwendig damit die variablen sich nicht abschatten
    * default Branch auch ;)
3. **PreisBerechnungsVisitor** Old-Style-Switch durch Switch Expression ersetzten
    * addition hoch ziehen
    * yield bei multiline
    * explicites null handling