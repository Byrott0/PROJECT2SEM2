package org.example.project2sem2.Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchEngineTest {

    private SearchEngine searchEngine;

    @BeforeEach
    void setUp() {
        searchEngine = new SearchEngine();
    }

    @Test
    void testValidSearchQuery() {
        // Test een geldige zoekopdracht
        assertTrue(searchEngine.search("java").size() > 0);
    }

    @Test
    void testEmptySearchQuery() {
        assertTrue(searchEngine.search("").isEmpty());
    }

    @Test
    void testSearchWithSpecialCharacters() {
        assertTrue(searchEngine.search("@#$%").isEmpty());
    }

    // Multiple Condition Coverage (MC)
    @Test
    void testSearchWithMultipleConditions() {
        assertTrue(searchEngine.search("java").size() > 0); // Valid query
        assertTrue(searchEngine.search("").isEmpty()); // Empty query
        assertTrue(searchEngine.search("java @#$%").isEmpty()); // Valid query with special characters
        assertTrue(searchEngine.search("@#$% java").isEmpty()); // Special characters in valid query
    }

    // Decision Coverage (DC)
    @Test
    void testFindKey() {
        // Each condition in the findKey method
        assertEquals("java", searchEngine.findKey("How to program in java?"));
        assertEquals("python", searchEngine.findKey("What's new in python?"));
        assertEquals("language", searchEngine.findKey("What is a programming language?"));
        assertEquals("social platform application", searchEngine.findKey("How to build a social platform application?"));
        assertEquals("financial system", searchEngine.findKey("Tell me about the financial system"));
        assertEquals("domain model", searchEngine.findKey("Explain the domain model"));
        assertNull(searchEngine.findKey("How is the weather today?")); // No keyword
    }

    @Test
    void findKey_WithJavaKeyword_ReturnsJava() {
        // Arrange
        String question = "How to program in java?";

        // Act
        String result = searchEngine.findKey(question);

        // Assert
        assertEquals("java", result, "The keyword 'java' should be identified from the question.");
    }

    @Test
    void findKey_NoKeyword_ReturnsNull() {
        // Arrange
        String question = "How is the weather today?"; // No keywords present

        // Act
        String result = searchEngine.findKey(question);

        // Assert
        assertNull(result, "The method should return null as the question contains no keywords.");
    }

    @Test
    void findAnswer_WithPythonKeyword_ReturnsPythonResponse() {
        // Arrange
        String question = "What's new in python?";
        String expectedResponse = """
        Python is een geïnterpreteerde programmeertaal op hoog niveau die bekend staat om zijn eenvoud en leesbaarheid. Het benadrukt de leesbaarheid van de code en een zuivere syntaxis, waardoor het een ideale taal is voor zowel beginners als ervaren ontwikkelaars.

        Functies:

        Geïnterpreteerd:

        Python is een geïnterpreteerde taal, wat betekent dat code regel voor regel wordt uitgevoerd door de Python-interpreter. Dit maakt snelle ontwikkeling en eenvoudig debuggen mogelijk.

        Dynamisch typen:

        Python wordt dynamisch getypeerd, wat betekent dat de typen variabelen tijdens runtime worden bepaald. Dit maakt flexibele en beknopte code mogelijk, maar kan leiden tot runtimefouten als deze niet zorgvuldig worden gebruikt.

        Sterk typen:

        Hoewel Python dynamisch wordt getypeerd, is het ook sterk getypeerd, wat betekent dat typefouten tijdens runtime worden opgemerkt. Dit helpt bij het schrijven van betrouwbaardere en robuustere code.

        Rijke standaardbibliotheek:

        Python wordt geleverd met een rijke standaardbibliotheek die modules en pakketten biedt voor verschillende taken, zoals bestands-I/O, netwerken, reguliere expressies en gegevensmanipulatie. Dit vermindert in veel gevallen de behoefte aan bibliotheken van derden.

        Veelzijdigheid:

        Python is een veelzijdige taal die geschikt is voor een breed scala aan toepassingen, waaronder webontwikkeling, data-analyse, wetenschappelijk computergebruik, kunstmatige intelligentie, machinaal leren, automatisering en scripting.

        Bibliotheken:

        NumPy:

        NumPy is een fundamenteel pakket voor wetenschappelijk computergebruik in Python. Het biedt ondersteuning voor multidimensionale arrays, wiskundige functies, lineaire algebra-bewerkingen en het genereren van willekeurige getallen.

        Pandas:

        Pandas is een krachtige bibliotheek voor gegevensmanipulatie en -analyse in Python. Het biedt datastructuren zoals DataFrame en Series, samen met tools voor het lezen en schrijven van gegevens uit verschillende bronnen.

        Django:

        Django is een webframework op hoog niveau voor het bouwen van webapplicaties in Python. Het volgt het architectonische patroon Model-View-Controller (MVC) en biedt functies zoals ORM, URL-routering, formulierverwerking en authenticatie.

        Hulpmiddelen:

        Integrated Development Environments (IDE's):

        Python-ontwikkelaars gebruiken vaak IDE's zoals PyCharm, Visual Studio Code en Sublime Text voor het schrijven, debuggen en testen van Python-code. Deze IDE's bieden functies zoals het voltooien van code, syntaxisaccentuering en projectbeheer.

        Pakketbeheerders:

        Pakketbeheerders zoals pip en conda worden gebruikt voor het installeren, beheren en bijwerken van Python-pakketten en afhankelijkheden. Ze vereenvoudigen het proces van het beheren van projectafhankelijkheden en zorgen voor reproduceerbare omgevingen.

        Testframeworks:

        Python-ontwikkelaars gebruiken testframeworks zoals pytest, unittest en doctest om tests voor hun code te schrijven en uit te voeren. Deze raamwerken helpen bij het waarborgen van de kwaliteit van de code, het identificeren van bugs en het vergemakkelijken van code-onderhoud.""";

        // Act
        String result = searchEngine.findAnswer(question);

        // Assert
        assertNotNull(result, "A response for 'python' should be available.");
        assertEquals(expectedResponse, result, "The response should contain expected Python content.");
    }

    @Test
    void findAnswer_NoMatchingKeyword_ReturnsNoDataFoundMessage() {
        // Arrange
        String question = "What is the weather today?";
        String expectedResponse = "No data found for: What is the weather today?";

        // Act
        String result = searchEngine.findAnswer(question);

        // Assert
        assertEquals(expectedResponse, result, "A message indicating no data found should be returned.");
    }

    @Test
    void getResponse_ValidKeywordQuery_ReturnsCorrectResponse() {
        // Arrange
        String input = "Tell me about financial system";
        String expectedResponse = """
        Financieel systeem:

        Deze module bevat de kernfunctionaliteiten van onze financiële systeemapplicatie. Het verzorgt gebruikersauthenticatie, profielbeheer, transactieverwerking, accountbeheer en rapportage.

        Klassen:

        UserAuthentication:

        Verwerkt gebruikersauthenticatie en autorisatie. Beheert gebruikerssessies en tokens voor veilige toegang tot het systeem.

        UserProfile:

        Verantwoordelijk voor het beheer van gebruikersprofielen. Slaat gebruikersinformatie op en haalt deze op, zoals naam, contactgegevens en accountvoorkeuren.

        TransactionProcessing:

        Beheert de verwerking van financiële transacties zoals stortingen, opnames, overboekingen en betalingen. Garandeert de integriteit en veiligheid van transacties.

        Accountbeheer:

        Beheert gebruikersaccounts, inclusief het aanmaken, bijwerken en verwijderen. Verwerkt rekeningsaldi, transactiegeschiedenis en accountrechten.

        Rapportage:

        genereert rapporten en analyses over de financiële activiteiten van gebruikers, rekeningsamenvattingen en trends. Biedt inzichten voor besluitvorming en naleving van regelgeving.

        Database:

        De databasemodule beheert het opslaan en ophalen van financiële gegevens voor het systeem. Het maakt gebruik van een relationeel databasebeheersysteem (RDBMS) om gestructureerde gegevens efficiënt op te slaan.

        Tabellen:

        Gebruikers:

        slaat gebruikersaccountinformatie op, zoals gebruikersnaam, e-mailadres, wachtwoord-hash en profielgegevens.

        Rekeningen:

        Bevat financiële rekeningen van gebruikers, waaronder spaargeld, cheques, beleggingen en leningen.

        Transacties:

        slaat transactierecords op, inclusief type, bedrag, tijdstempel en bijbehorende account-ID's.

        Machtigingen:

        Beheert toegangscontrole en machtigingen voor gebruikers en systeembeheerders.

        AuditTrail:

        registreert systeemactiviteiten en wijzigingen voor audit- en compliancedoeleinden.

        Meldingen:

        slaat door het systeem gegenereerde meldingen op voor gebruikersactiviteiten zoals transacties, accountupdates en beveiligingswaarschuwingen.
        """;

        // Act
        String actualResponse = searchEngine.getResponse(input);

        // Assert
        assertEquals(expectedResponse.trim(), actualResponse.trim(), "The response should correctly match the expected content for 'financial system'.");
    }

    @Test
    void getResponse_NoKeywordQuery_ReturnsNoDataFoundMessage() {
        // Arrange
        String input = "How many countries are in Europe?";
        String expectedResponse = "No data found for: How many countries are in Europe?";

        // Act
        String result = searchEngine.getResponse(input);

        // Assert
        assertEquals(expectedResponse, result, "A message indicating no data found should be returned for input without a known keyword.");
    }
}
