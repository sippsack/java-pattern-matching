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
4. **Kunde** einführen der Visitor-Logik und Löschen der Visitor
5. _OPTIONAL:_ Einführen von Sealed Interface und record semantic
    * **Zeitpunkt** wird record
    * **Tarif** wird Sealed Interface
        * **BusinessTarif** wird non-sealed da hier weitere interessante implementierungen kommen könnten
        * **Privat** Tarif wird final class
        * **Profi** Tarif wird record and enthält den Rabatt
    * bei der Berechnung sind dann bald auch deconstructions möglich ^-^

**Hashes**:

0. 6340978fc4aed73492eb26d0f6aad034fdfbf704
1. 0783574295374438543ec57e26a98ff644df1d4b
2. ffea6e0cf8314bd214f5bb87902326d90653f315
3. 67b84731bf62426c096c38329b2686951c9f1481
4. 88886bf72818732eb5dbd0b2f73c65c3e7b82828
5. 28c38f2e739aa758434ebbc5bb9f1a89ad1ef482