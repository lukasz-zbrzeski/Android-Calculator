# Android Calculator

Prosta aplikacja kalkulatora na Androida z dwoma trybami pracy: **prostym** i **naukowym**. Projekt został napisany w **Javie** z użyciem klasycznego Android SDK oraz biblioteki **exp4j** do parsowania i obliczania wyrażeń matematycznych. Aplikacja uruchamia się od ekranu startowego, z którego można wybrać odpowiedni tryb kalkulatora. 

## Opis projektu

Aplikacja umożliwia wykonywanie podstawowych działań matematycznych, a w trybie naukowym oferuje także zestaw funkcji rozszerzonych, takich jak operacje trygonometryczne, logarytmy, pierwiastki czy potęgowanie. Projekt został przygotowany jako natywna aplikacja Android i wykorzystuje komponenty Material Design.

## Funkcje

### Tryb prosty
- dodawanie, odejmowanie, mnożenie i dzielenie
- obsługa nawiasów
- liczby dziesiętne
- zmiana znaku liczby (`+/-`)
- czyszczenie całości (`AC`)
- usuwanie ostatniego fragmentu wpisu (`C`)

### Tryb naukowy
- `sin`
- `cos`
- `tan`
- `ln`
- `log`
- `sqrt`
- potęgowanie `x^y`
- kwadrat liczby `x^2`
- procenty `%`

### Dodatkowo
- ekran startowy z wyborem trybu: **Simple** lub **Advanced**
- okno **About** z informacją o autorze
- zachowanie wpisanego działania przy przejściu między ekranami
- obsługa błędnych działań z komunikatem `Error` i komunikatami `Toast`
- formatowanie wyników: liczby całkowite bez części dziesiętnej, liczby niecałkowite do **4 miejsc po przecinku**

## Technologie

- **Java**
- **Android SDK**
- **Material Components**
- **ConstraintLayout**
- **exp4j** – biblioteka do obliczania wyrażeń matematycznych

## Uruchomienie projektu

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/lukasz-zbrzeski/Android-Calculator.git
   ```
2. Otwórz projekt w Android Studio.
3. Poczekaj na synchronizację Gradle.
4. Uruchom aplikację na emulatorze lub urządzeniu z Androidem 14 lub nowszym.
