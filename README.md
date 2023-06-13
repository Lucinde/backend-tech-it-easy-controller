# backend-tech-it-easy-controller

## Inleiding
Je bent net begonnen als developer bij een bedrijf genaamd Tech It Easy, dat TV's verkoopt. Tijdens de cursus Spring Boot ga jij een backend applicatie programmeren voor het bedrijf. De winkel heeft een inventaris van televisies die moet worden bijgehouden. Na iedere les gaan we deze applicatie een stukje verder uitbouwen door middel van de huiswerkopdrachten. Zo krijg je stap-voor-stap meer ervaring in het bouwen van een backend applicatie. Aan het einde van de cursus zullen we een werkende Tech It Easy backend applicatie hebben!

## Opdrachtbeschrijving
Je gaat via de Spring Boot Initialzr een project aanmaken, waarbij je gebruikmaakt van de volgende dependency:
- Spring Web

Gedurende de cursus Spring Boot zal het langzamerhand duidelijk worden waarom je deze dependency nodig hebt. Voor nu mag je dit gewoon van ons aannemen!

## Randvoorwaarden
De opdracht moet voldoen aan de volgende voorwaarden:

- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `controllers`;
- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `exceptions`;
- Het project bevat een `TelevisionsController`;
- Het project bevat een `ExceptionController`;
- Het project bevat een `RecordNotFoundException`;
- De **TelevisionsController** bevat:
    - een GET-request voor alle televisies
    - een GET-request voor 1 televisie
    - een POST-request voor 1 televisie
    - een PUT-request voor 1 televisie
    - een DELETE-request voor 1 televisie
- De **ExceptionController** bevat:
    - een exceptionhandler
- De **RecordNotFoundException** bevat:
    - een default exception
    - een exception met message

### Belangrijk
Bij het inleveren van deze opdracht is de applicatie nog niet geheel functioneel, omdat we hier meer kennis over andere technieken voor nodig hebben die later in de cursus aan bod komt.

Je mag de volgende ResponseEntiteiten als return waardes gebruiken:
- `ResponseEntiteit.ok("television")`
- `ResponseEntiteit.created(null).body("television")`
- `ResponseEntiteit.noContent().build()`

## Stappenplan
_Let op_: het is uitdagender om jouw eigen stappenplan te maken. Mocht je niet zo goed weten waar je moet beginnen, kun je onderstaand stappenplan volgen:

1. Zet een nieuw project op via Spring Boot Initialzr genaamd TechItEasy.
2. Maak een nieuwe map aan in je project genaamd controllers.
3. Maak een nieuwe map aan in je project genaamd exceptions.
4. Maak een nieuwe klasse aan in de map controllers voor de TelevisionController.
5. Voorzie de klasse van de juiste annotatie.
6. Maak in de klasse de (in de randvoorwaarden) gevraagde requests aan met de juiste response entities.
7. Maak een nieuwe klasse aan in de map controllers voor de ExceptionController.
8. Voorzie de klasse van de juiste annotatie.
9. Maak in de klasse een exceptionHandler met de juiste notatie en response entitiy.
10. Maak een nieuwe klasse aan in de map exceptions.
11. Extent de klasse met de RunTimeException.
12. Vul de klasse aan aan de hand van het voorbeeld in hoofdstuk 5.11 van de cursus Spring Boot.

## Bonus opdracht
We returnen nu bij een aantal RequestMappings de String "television". Uiteindelijk is het de bedoeling dat we data uit een database gaan returnen.
Voor nu is dat nog onbekend terrein, maar we kunnen wel doen alsof we een database hebben.
- Maak in je controller class een Lijst van Strings genaamd "televisionDataBase", dit gaat dienen als onze database.
- Zorg dat je GET requests de juiste data returnen uit de lijst. (Gebruik de "id" PathVariable als index van de lijst)
- Zorg dat je POST request een nieuwe string aan de lijst toevoegd. (denk bijvoorbeeld aan een merknaam of typenaam van een tv)
- Zorg dat je PUT request de string op positie x in de "database" wijzigt. (gebruik hier de index van de lijst)
- Zorg dat je DELETE request de string op positie x in de "database" verwijderd. (verwijderen kun je het best nabootsen door de waarde naar null wijzigen, net als bij PUT)
- Maak een extra methode in je ExceptionController class waarin je de IndexOutOfBoundsException opvangt. (Hiermee kunnen we een bericht sturen naar de gebruiker als deze een GET-request doet met een id dat groter is dan de lengte van de database-lijst.)

## Bonus bonus
- Maak in de ExceptionController geen methode die IndexOUtOfBoundsException afvangt, maar zorg dat je door het throwen van RecordNotFoundException in je controller geen foutmeldingen krijgt als je in postman een verkeerde input geeft (bijvoorbeeld id 5 terwijl je maar 2 items in je lijst hebt)
- Maak een nieuwe exeption met een passende naam die je opgooit als de gebruiker een tv wil posten waarvan de naam langer is dan 20 letters. Pas deze exception toe in de POST mapping en in de ExceptionHandler. Geef ook een passende message en HTTPSTATUS mee in de controller/ExceptionHandler.

-----------

# Part 2
# Opdrachtbeschrijving Model/Repository

## Inleiding

Je bent net begonnen als developer bij een bedrijf genaamd Tech It Easy, dat TV's verkoopt. Tijdens de cursus Spring Boot ga jij een backend applicatie voor hen programmeren. De winkel heeft een inventaris van televisies die moet worden bijgehouden. Na iedere les gaan we deze applicatie een stukje verder uitbouwen in de vorm van huiswerk. Zo krijgen we stap-voor-stap meer ervaring in het bouwen van een backend applicatie. Aan het einde van de cursus zullen we een werkende Tech It Easy backend hebben!

## Recap van vorige opdracht

Je hebt de vorige les een begin gemaakt voor de applicatie voor je werkgever TechItEasy. Je hebt een controller aangemaakt met verschillende endpoints. Deze kunnen aangeroepen worden via een frontend. Jij maakt hiervoor gebruik van Postman. Omdat we niet weten of alles bestaat wat de gebruiker opvraagt heb je ook een exception controller gemaakt en een RecordNotFound exception. Na een nieuwe les Spring boot ben je klaar voor de volgende stap van het bouwen van je applicatie.

## Randvoorwaarden

- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `models`;
- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `repositories`;
- Het project bevat een `Television` in de map `models`;
- Het project bevat een `TelevisionRepository`;
- Het project bevat de volgende dependencies:
  - `Spring Web`
  - `Spring Data Jpa`
  - `PostgreSQL Driver`
- De **Application.properties** bevat de properties uit hoofdstuk 9.2 van de cursus Spring Boot(met aangepaste namen)
- De **TelevisionRepository** extends JpaRepository met de juiste parameters
- De **Television** bevat:
  -de attributen:
  - Long id (incl. annotaties id en generatedValue)
  - String type
  - String brand
  - String name
  - Double price
  - Double availableSize
  - Double refreshRate
  - String screenType
  - String screenQuality
  - Boolean smartTv
  - Boolean wifi
  - Boolean voiceControl
  - Boolean hdr
  - Boolean bluetooth
  - Boolean ambiLight
  - Integer originalStock
  - Integer sold
  - default constructor
  - constructor
  - alle getters en setters

### Belangrijk
- Laat de _application.properties_ overeenkomen met de gegevens van `PgAdmin`
- Je mag de volgende ResponseEntiteiten als return waardes gebruiken:
  - `ResponseEntiteit.ok("television")`
  - `ResponseEntiteit.created(null).body("television")`
  - `ResponseEntiteit.noContent().build()`
- Let er op dat je database-password niet hetzelfde password is dat je gebruikt voor je email, facebook of andere applicatie. Aangezien deze applicatie niet gedeployed hoeft te worden, kun je een makkelijk password kiezen als "password" of "banaan".

## Stappenplan
_Let op_: het is uitdagender om jouw eigen stappenplan te maken. Mocht je niet zo goed weten waar je moet beginnen, kun je onderstaand stappenplan volgen:

1. Voeg de benodigde dependencies toe aan je _POM.xml_ en laat `Maven` deze installeren
2. Voeg aan de _application.properties_ de benodigde properties toe
3. Maak een nieuwe database aan in `PgAdmin` (zorg dat je _application.properties_ overeenkomt met de naam, password en user van de database)
4. Maak een nieuwe map aan in je project voor `models`
5. Maak een nieuwe klasse genaamd `Television`
6. Voeg de annotatie **@Entity** toe aan de klasse
7. Voeg de juiste _attributen_ toe aan de klasse
8. Voeg de beide _constructors_ toe aan de klasse
9. Voeg alle _getters & setters_ toe aan de klasse
10. Maak een nieuwe map aan in je project voor `repositories`
11. Voeg aan deze map een nieuwe klasse toe genaamd `TelevisionRepository`
12. Extend de _repository_ met de `JpaRepository` en geef de juiste attributen mee. (het type van het model en het type van het ID)

-------------

# Part 3
# Opdrachtbeschrijving

## Inleiding
Je bent net begonnen als developer bij een bedrijf genaamd Tech It Easy, dat TV's verkoopt. Tijdens de cursus Spring Boot ga jij een backend applicatie programmeren voor het bedrijf. De winkel heeft een inventaris van televisies die moet worden bijgehouden. Na iedere les gaan we deze applicatie een stukje verder uitbouwen door middel van de huiswerkopdrachten. Zo krijg je stap-voor-stap meer ervaring in het bouwen van een backend applicatie. Aan het einde van de cursus zullen we een werkende Tech It Easy backend applicatie hebben!

## Recap van vorige opdracht
Je hebt in de vorige les de applicatie voor je werkgever TechItEasy uitgebreid met een `Model` en een `Repository`. Een `Model` is een _Klasse_ in je project en bevat _variabelen_ en _constructors_, en geven de mogelijkheid om meerdere objecten aan te maken in je database van deze _Klasse_. De `Repository` is verlengd met de `JpaRepository`, daardoor kan je de standaard find-functies uitvoeren in de `Service` die we in de komende les toevoegen aan het project. Naast de standaard _query's_ die al staan in de `JpaRepository` kunnen we later eventueel nog _query's_ schrijven in de `Repository` voor wat moeilijkere _find_ functies. Maar hoe gaaf zou het zijn als het project zou kunnen draaien? Goed nieuws: na deze les werkt het project en geeft het de juiste response als we requests doen via `Postman`.

## Randvoorwaarden
De opdracht moet voldoen aan de volgende voorwaarden:
- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `services`;
- De map `services` bevat een _klasse_ genaamd `TelevisionService`;
- De `TelevisionService` bevat:
  - de juiste _annotatie_
  - een _private_ variabele `TelevisionRepository`
  - de connectie van de `Service` en de `Repository` door middel van een _autowired_
  - een _functie_ voor het ophalen van alle `Televisions`
  - een _functie_ voor het ophalen van 1 `Television`
  - een _functie_ voor het opslaan van 1 `Television`
  - een _functie_ voor het verwijderen van 1 `Television`
  - een _functie_ voor het updaten van 1 `Television`
- De `Controller` is door middel van een _autowired_ gelinkt aan de `Service`;
- Het project bevat, op de juiste plaats in de map-structuur, een map genaamd `Dtos`;
- De map `dtos` bevat een `TelevisionDto` en een `TelevisionInputDto`;
- De _requestMappings_ in de `Controller` worden aangepast zodat deze de juiste response doorgeven via de `Service`;
- De `Service` maakt gebruik van de gegevens die we via de `Controller` doorkrijgen van de `Dtos`;
- Het project bevat de _validator_ dependency uit paragraaf 8.1 van de cursus Spring Boot in Edhub.

### Belangrijk
- Na deze les moeten de _requestMappings_ in de controller de juiste responses geven.
- Na deze les is de applicatie voor het eerst functioneel.

Het is belangrijk om goed te begrijpen hoe de lagen in onze multi-tier application samenwerken:
- De `Controller` ontvangt een verzoek op een _endpoint_, als er _variable_ worden meegeven aan dit verzoek komen die binnen via de `TelevisionInputDto` ->
- De `Controller` geeft aan de hand van het verzoek en eventuele meegekregen _variable_ dit door aan de `Service` ->
- De `Service` spreekt aan de hand van de geschreven functie de `Repository` aan met de juiste _find_ functie/query ->
- De `Repository` gaat aan de hand van het bijbehorende `Model` zoeken naar de juiste gegevens en stuurt deze terug naar de `Service` ->
- De `Service` past de logica toe uit de functie en geeft de response aan de hand van de `TelevisionDto` terug aan de `Controller`
  (De controller wordt vanuit de _front-end_ of vanuit _Postman_ aangesproken, en geeft ook het antwoord weer terug.)

## Stappenplan
_Let op_: het is uitdagender om jouw eigen stappenplan te maken. Mocht je niet zo goed weten waar je moet beginnen, kun je onderstaand stappenplan volgen:

1. Voeg de benodigde dependencies toe aan je _POM.xml_ en laat `Maven` deze installeren.
2. Maak een map genaamd `services` aan in de map-structuur.
3. Maak een _klasse_ genaamd `TelevisionService` aan in de map `Services`.
4. Voeg de `Service` _annotatie_ boven de _klasse_ toe.
5. Maak in de `TelevisionService` een _private_ variabele aan voor de `TelevisionRepository`.
6. Voeg een `@Autowired` toe om de `Repository` in de `TelevisionService` te kunnen gebruiken.
7. Maak in de `TelevisionService` de functie _getTelevisions_ aan de hand van het voorbeeld in paragraaf 6.2 van de cursus Spring Boot cursus in Edhub.
8. Doe dit ook voor de _getTelevision_, _saveTelevision_, _updateTelevision_ en de _deleteTelevision_.
9. Maak in de `TelevisionController` een `@Autowired` om de `Service` te kunnen gebruiken in de `Controller`.
10. Maak in het project een map aan genaamd `dtos`.
11. Maak in deze map een klasse aan genaamd `TelevisionDto`.
12. Definieer in deze klasse alle variabele die een `Television` object bevat (dezelfde als in het model gedefinieerd staan).
13. Maak daaronder een `public` `static` functie aan genaamd _fromTelevision_. Deze heeft als returnwaarde `TelevisionDto` en als attribuut `Television television`.
14. Maak in deze functie een `var dto = new TelevisionDto();`.
15. Voeg in de functie voor iedere _variable_, die je boven deze functie hebt gedeclareerd een toewijzing naar de dto variable (bijvoorbeeld: `dto.name = television.getName();` ).
16. Return als laatste stap in deze functie het _dto_ object en sluit de functie af.
17. Maak een klasse aan in de map `dtos` genaamd `TelevisionInputDto`.
18. Definieer ook in deze klasse alle variabelen die een `Television` object bevat.
19. Maak een `public` functie genaamd `toTelevision()` met returnwaarde `Television`.
20. Maak in deze functie een `var television = new Television();`.
21. Voeg in de functie voor iedere _variable_, die je boven deze functie hebt gedeclareerd, een toewijzing naar de television variabele (bijvoorbeeld: `television.setName(name);` ).
22. Pas de `RequestMappings` in de `TelevisionController`, zodat de controller het verzoek doet aan de _servicelaag_ en het response ook ontvangt van de _servicelaag_ (dit is omdat we de `Controller` eigenlijk zo dom mogelijk willen houden).
23. Pas de _servicelaag_ zo aan dat deze gebruikmaakt van de gegevens die de _controllerlaag_ doorgeeft. De `Service` moet hier dan de logica aan toepassen om met gebruik van de `Dtos` een _returnwaarde_ terug te kunnen sturen naar de controller.