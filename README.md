
# bwInf36A3
36. Bundeswettbewerb Informatik, Aufgabe 3: Dreiecke Zählen

## Aufgabe

Janina hat von ihrer Freundin Nadine ein Rätsel
aus dem Internet zugeschickt bekommen: Wie viele
Dreiecke sind in dieser Zeichnung zu sehen?

Nachdem Janina eine Weile Dreiecke gezählt hat,
will sie ihr Ergebnis im Internet nachprüfen. Sie
findet aber keine Lösung, sondern nur weitere
Rätsel dieser Art. Da sie sich nicht jedes Mal erneut
ans Zählen machen will, überlegt sie, ob sich
solche Rätsel mit einem Computer lösen lassen.

### Aufgabenstellung
Versetze dich in Janinas Lage und schreibe ein
Programm, das die Dreiecke in einer Rätsel-Zeichnung
zählt. Eine Zeichnung besteht aus einigen Strecken.
Du kannst davon ausgehen, dass keine zwei Strecken
auf derselben Geraden liegen und dass sich nie mehr
als zwei Strecken im gleichen Punkt schneiden.
In der obigen Zeichnung sind übrigens neun Dreiecke
zu finden.
Wende dein Programm auf die Beispiele an, die du
auf den BwInf-Webseiten findest.


## Ansatz
* Darstellung der Punkte u. Schnittpunkte in einen Graph
* 3-Schrittige Kreisläufe finden

## Todo
- [x] Punkte einlesen
- [x] Schnittpunkte bestimmen
- [ ] Kreisläufe finden
- [ ] Grafische Ausgabe

## Notes

### Punkte einlesen
Funktion `List<Line2D> einlesen(File... files)`
* Iteriert durch Dateien
    * Scanner: 1ste Zeile wird übersprungen
    * Iteriert durch Zeilen:
        * Zeile an " " splitten
        * Liste 2 neue Line2D anhängen
     
### Schnittpunkte bestimmen
`Line2D.intersectsLine()` hilft ungemein
* Eintragen aller Line2D in Hashmap
    * Key: `line.toString()`
    * Value: `new Node(line)`
* Iterieren durch `lines`, Laufvariable: `line`
    * Iterieren durch `lines`, Laufvariable: `vergleich`
        * falls `line == vergleich`: Nächster Durchlauf (Um Selbst-Referenz zu vermeiden)
        * else: `vergleich` zu Intersects von `line`'s Eintrag in der HashMap hinzufügen  
