# Demoprojekt für Pattern Matching mit Java

Es gibt zwei Beispiele:
* Implementierung einer LinkedList mit Pattern Matching
* Implementierung eines Telefontarif-Rechners mit Patter Matching

## LinkedList

Implementierung einer LinkedList mit algebraischen Datentypen in Java. Inspiriert durch [LinkedList-Beispiel]( https://github.com/MBoegers/TreeExperiments/tree/main/SingleLinkedListJFP/src/main/java/io/github/mboegers/list/jfp) von [Merlin Bögershausen](https://github.com/MBoegers).

### Aufgabenstellung

Die LinkedList ist einmal objektorientiert (Package de.sippsack.list.oo) und einmal funktional (Package de.sippsack.list.fp) implementiert. Bei der funktionalen Programmierung müssen die Funktionen `head`, `tail` und `contains` noch implementiert werden.

## BadTelefon

Es gibt verschiedene Packages:

### de.sippsack.badtelefon.v0

* Ausgangsbeispiel
* einfacher Telefontarif-Rechner
* Logik nur in der Klasse Kunde, viele Code-Smells
* Pseudo-Enumeration mit Integer-Konstanten in Tarif
* keine wirkliche Domänenstruktur, Berechnungslogik komplett außerhalb

### de.sippsack.badtelefon.v1

* ein Refactoring von v0: objektorientierter Lösungsansatz
* eigene Domänentypen für die Tarifarten
* Geschäftslogik in Domänenklassen (Strategie-Muster)
* Einsatz der Schablonenmethode zur Vermeidung von Redundanzen

### de.sippsack.badtelefon.v2

* Lösungsansatz mit dem **Visitor-Pattern**
* bisheriger Ansatz in OOP, wenn man die Klassen selbst nicht ändern konnte und/oder wenn man parallel alternative Algorithmen (
  zweiter Visitor) umsetzen muss
* Achtung: hier haben wir eine Art generischer Visitor statt überladene visit-Methode, wie beim GoF-Buch beschrieben

### de.sippsack.badtelefon.v3

* Ausgangslage für Implementierung des Pattern Matching
* Umbau in eine Sealed Class Hierarchie, Records, Pattern Matching for switch

### Lösungsschritte

Wir könnten von verschiedenen Stellen (v0, v1 oder v2) beginnen. Hier starten wir aufgrund des besseren roten Fadens mit v2. Dabei werden wir den Visitor durch Pattern Matching ersetzen, dabei switch-Expressions, Pattern Matching for switch mit Type Patterns (Pattern Matching for instanceof), Sealed Classes und Records.

1. Im PreisBerechnungsVisitor bauen wir zunächst die boolschen Ausrücke in den ifs und ifs/elses um in Pattern Matching for instanceof (Type Pattern).
2. Anschließend wandeln wir das if/else in ein klassiches switch-Statement um, bitte hier noch nicht das automatische Refactoring (Quick Fix) in IntelliJ oder Eclipse verwenden, da das gleich eine zu moderne Form der switch-Expression mit Arrow-Notation erzeugt (wir wollen erstmal klassische cases mit Doppelpunkt und break)
3. Umbau des Switches in switch-Expression mit Arrow-Syntax (kompakter) und yield statt break (bei mehrzeiligen case-Blöcken) und und Zuweisung des Switch-Resultats (gebuehr += switch(..))
4. Visitor-Klasse auflösen (Inhalte ziehen um nach Kunde), dazu den switch in Kunde.account() inlinen (oder manuell kopieren), Visitor löschen und die accept()-Methode in Tarif entfernen
5. Umbau der Tarif-Klassenhierarchie zu Sealed Classes, dabei probieren wir verschiedene Varianten aus, gegenüber v0 haben die Tarife teilweise auch Zustände: BusinessTarif bekommt einen Zustand (VIP-Kunde), sowie Logik und ist erweiterbar (non-sealed), PrivatTarif bekommt Zustand/Logik (Rabattmöglichkeit) und ist nicht erweiterbar, ProfiTarif enthält gar keinen Zustand oder Verhalten (macht inhaltlich gar keinen richtigen Sinn, geht aber trotzdem), dafür nutzen wir auch teilweise Records
6. Umwandeln der Immutable-Klasse Zeitpunkt (auch eine Art Value Object) in ein Record (Quick-Fix/Refactoring in der IDE nutzen), Löschen von equals/hashCode und den gettern

