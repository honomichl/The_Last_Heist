# The Last Heist

## Popis hry
The Last Heist je textová adventura (interaktivní hra) napsaná v jazyce Java.
Hráč se ujímá role zloděje Robina, jehož cílem je vloupat si do přísně střežené vily pana Huberta.
Hlavním úkolem není jen krádež cenností, ale především nalezení klíčové složky s důkazy, které mohou očistit Robinova otce.
Hra vyžaduje strategické rozhodování a opatrný postup.

## Ovládání hry
Hra se ovládá zadáváním textových příkazů do konzole. Mezi základní dostupné příkazy patří:

* **jdi [místnost]**: Přesune postavu do sousední lokace (např. jdi kuchyne).
* **seber [předmět]**: Vloží předmět z místnosti do tvého inventáře.
* **prozkoumej [předmět]**: Zobrazí detailní popis předmětu a jeho vlastnosti.
* **batoh**: Vypíše aktuální obsah tvého inventáře a zbývající kapacitu.
* **napoveda**: Zobrazí seznam všech dostupných příkazů.
* **konec**: Okamžitě ukončí rozehranou hru.

## Herní mechaniky
* **Systém hluku (Noise Level):** Každý pohyb a akce generuje hluk. Pokud celkový hluk překročí maximální limit,
*  pan Hubert se probudí a hra končí neúspěchem.
* **Správa inventáře:** Batoh má omezenou kapacitu slotů. Hráč musí prioritizovat mezi úkolovými předměty a lupem, který mu vydělá peníze.
* **Ekonomika a specialisté:** Předměty mají náhodně generovanou hodnotu.
*  Na konci hry se ze získané částky odečítají podíly pro komplice (Peter a vybraný Hacker).
* **Skryté předměty:** Některé předměty nejsou na první pohled vidět a hráč je musí v místnostech aktivně hledat.

## Jak hru spustit
1. **Požadavky:** Nainstalované Java Development Kit (JDK) verze 17 nebo novější.
2. **Klonování:** Stáhněte si tento repozitář nebo jej naklonujte pomocí příkazu git clone.
3. **Import:** Otevřete projekt ve vašem oblíbeném IDE (doporučeno IntelliJ IDEA).
4. **Spuštění:** Spusťte hlavní třídu `MainGame` (případně `Main`), která inicializuje herní svět a spustí uživatelské rozhraní.

## Použité technologie a knihovny
* **Java SDK 17+**: Základní vývojové prostředí.
* **GSON**: Knihovny použité pro parsování herních dat (místnosti, předměty) ze souborů JSON.
* **JUnit 5**: Framework použitý pro unit testování logiky inventáře a mechaniky generování hodnoty lupu.

# Autor: Filip Honomichl, C2b
