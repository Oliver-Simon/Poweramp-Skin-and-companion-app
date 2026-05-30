# Bericht: Implementierung & Optimierung "Centered Labels" Layout

Dieses Dokument beschreibt die technischen Änderungen und Lösungen, die im Rahmen der Entwicklung des zentrierten Layouts für die Poweramp Skin durchgeführt wurden.

## 1. Behebung des System-Absturzes (Nubia/RedMagic Fix)
*   **Problem:** Die Verwendung des Befehls `layout_consumeSpace="vertical"` in Kombination mit String-Werten führte auf dem Nubia NX721J zu einer `UnsupportedOperationException` (Can't convert value to integer).
*   **Lösung:** Die manuelle Platzreservierung durch `consumeSpace` wurde durch die stabilen SDK-Standard-Anker (`Alt_TopAABounds`) ersetzt. Dies ermöglicht eine stabile Positionierung ohne Typ-Konvertierungsfehler im Ressourcen-Linker.

## 2. Zentrierung & Vertikales Stacking
*   **Problem:** Überlagerung von Titel, Interpret und Album-Info in einer Zeile oder fehlerhafte Links-Ausrichtung.
*   **Lösung:** 
    *   **Titel:** Mittig zentriert (`center_horizontal`) und am oberen Rand des reservierten Bereichs verankert.
    *   **Artist/Album:** Vertikal unter dem Titel verankert (`layout_attachTop="@id/title"`).
    *   **Next Track Info:** Die Fußzeilen-Info (Mini-Meta) wurde explizit horizontal zentriert.

## 3. Korrektur der Album-Listenansicht
*   **Problem:** Album-Cover in der Bibliothek wurden als abgeschnittene Rechtecke angezeigt.
*   **Lösung:** Der `Alt_TopListWidget` wurde so konfiguriert, dass er auf den projektspezifischen `ListSubstyle` verweist, wodurch das quadratische Format der Cover in Listenansichten wiederhergestellt wurde.

## 4. Optisches Fine-Tuning (Abstände)
*   **Label-Abstand:** Der gesamte Textblock wurde um `12dp` nach unten verschoben, um den Abstand zum Album-Art zu optimieren.
*   **Button-Abstand:** Die Shuffle- und Repeat-Buttons wurden um `-15dp` nach oben gezogen, um den Leerraum zum Text zu minimieren.
*   **Wave-SeekBar:** Das gesamte `TopWaveseekLayout` wurde ebenfalls nach oben verschoben, um ein kompakteres Gesamtbild zu erzeugen.

## 5. Skin-Einstellungen & Text-Hintergründe
*   **Problem:** Die Hintergrund-Optionen (Outline/Solid) funktionierten im zentrierten Modus nicht korrekt.
*   **Lösung:** Die Stile `SampleSkin_TextBackground_*` wurden aktualisiert, um die neuen zentrierten Indirektions-Attribute (`Alt_ItemTrackTitle_scene_aa_style`, etc.) zu unterstützen.

---
**Datum:** 27. Mai 2026
**Projekt:** Poweramp Skin Sample (Centered Labels Implementation)
