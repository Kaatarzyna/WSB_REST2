## Zadanie

Bazując na przykładach omawianych na wykładzie, napisz własną aplikację webową, która będzie na 2 poziomie dojrzałości Richardsona.

Zasób w aplikacji - dowolny obiekt, jaki wymyślisz (niech będzie inny niż omawiane do tej pory w przykładach, czyli filmy, obrazy, piosenki odpadają). Zasób powinien mieć id i kilka innych pól (pełna dowolność).

1. Stwórz klasę reprezentującą Twój zasób. Zasób powinien mieć pole z id oraz kilka innych pól. Przydadzą się również gettery, settery i konstruktory (można skorzystać z adnotacji Lomboka).

2. Stwórz repozytorium, które będzie implementowało operacje na Twoim zasobie. W repozytorium powinna się także znaleźć lista "domyślnych" obiektów - tak jak w omawianych przykładach.

3. Stwórz kontroler, który będzie obsługiwał requesty HTTP manipulujące Twoim zasobem. Kontroler powinien zwracać stosowne statusy odpowiedzi (200, 201, 404, ..).

4. Stwórz plik `.http`, w którym będzie przykładowe żądanie HTTP dla każdej z zaimplementowanych w kontrolerze metod.


### Wspierane metody (każda za 2pkt):

- wyświetlenie listy zasobów (`GET /zasób`)
- wyświetlenie jednego zasobu (`GET /zasób/{id}`)
- dodanie nowego zasobu (`POST /zasób`, dane zasobu w ciele żądania)
- edycja istniejącego zasobu (`PUT /zasób/{id}`, dane zasobu w ciele żądania)
- usunięcie zasobu (`DELETE /zasób/{id}`)


### Za co będą ucinane punkty

- braku testu, czyli brak przykładowego requestu dla metody w pliku `.http` (*-1pkt od metody!!*)

### Gdzie wrzucamy rozwiązanie

Kod powinien się znaleźć na GitHubie. Na Teamsach w wiadomości prywatnej proszę wysłać do mnie link do repo.

