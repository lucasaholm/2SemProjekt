package Test;

import Application.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FadHistorikTest {

    private FadHistorik fadHistorik;
    private Fad fad1;
    private Fad fad2;
    private Fad fad3;

    @BeforeEach
    public void setUp() {
        Medarbejder medarbejder = new Medarbejder(1, "Ole Hansen", "Destillering");
        Destillering destillering = new Destillering(1, "Eksempel Destilleri", 1000.0, LocalDate.now().minusYears(1), LocalDate.now(), 40.0, "Malt1", false, "Ingen kommentarer", null, medarbejder);
        Lager lager = new Lager(1, "Salls whisky", "Salls", 1000);
        Fad fad = new Fad(1,"Burbon", 100, 50,1,"FedEX", "Burbon", lager);

        fadHistorik = new FadHistorik(1, 120, LocalDate.now(), destillering);
        fad1 = new Fad(1, "Bourbon", 40, 0, 0, "Leverandør A", "Tidligere Indhold A", null);
        fad2 = new Fad(2, "Bourbon", 30, 0, 0, "Leverandør B", "Tidligere Indhold B", null);
        fad3 = new Fad(3, "Bourbon", 50, 0, 0, "Leverandør C", "Tidligere Indhold C", null);

        fadHistorik.addFad(fad1);
        fadHistorik.addFad(fad2);
        fadHistorik.addFad(fad3);
    }

    @Test
    public void testManglendePåfyldning() {
        String result = fadHistorik.manglendePåfyldning();
        assertEquals("Det går lige op", result);
    }

    @Test
    public void testPåfyldFad() {
        fadHistorik.påfyldFade();
        assertEquals(40, fad1.getNuværendeVolumen());
        assertEquals(30, fad2.getNuværendeVolumen());
        assertEquals(50, fad3.getNuværendeVolumen());
    }


    @Test
    public void testUdtrækSpiritusHistorie() {
        Spiritus spiritus = fadHistorik.createSpiritus(1, "Eksempel Spiritus", fadHistorik, ProduktType.WHISKY, 100, 40.0, 150.0, 60.0, "Kilde A", "En god spiritus");

        String result = fadHistorik.udtrækSpiritusHistorie(spiritus);

        String expectedOutput = "Spiritus ID: 1\n" +
                "Spiritus Navn: Eksempel Spiritus\n" +
                "Spiritus Type: WHISKY\n" +
                "Spiritus Alkoholprocent: 40.0\n" +
                "Spiritus Volume: 100.0\n" +
                "Spiritus Pris: 150.0\n" +
                "Spiritus Vandmængde: 60.0\n" +
                "Spiritus Kilde: Kilde A\n" +
                "Spiritus Beskrivelse: En god spiritus\n";


        assertEquals(expectedOutput, result);
    }

    @Test
    public void testCreateSpiritus() {
        Spiritus spiritus = fadHistorik.createSpiritus(1, "Eksempel Whisky", fadHistorik, ProduktType.WHISKY, 70, 40, 500, 30, "Kilde A", "Beskrivelse A");
        assertNotNull(spiritus);
        assertEquals(1, fadHistorik.getSpiritusArrayList().size());
    }

    @Test
    public void testAddSpiritus() {
        Spiritus spiritus = new Spiritus(1, "Eksempel Whisky", fadHistorik, ProduktType.WHISKY, 70, 40, 500, 30, "Kilde A", "Beskrivelse A");
        fadHistorik.addSpiritus(spiritus);
        assertEquals(1, fadHistorik.getSpiritusArrayList().size());
        assertTrue(fadHistorik.getSpiritusArrayList().contains(spiritus));
    }

    @Test
    public void testRemoveSpiritus() {
        Spiritus spiritus = new Spiritus(1, "Eksempel Whisky",fadHistorik, ProduktType.WHISKY, 70, 40, 500, 30, "Kilde A", "Beskrivelse A");
        fadHistorik.addSpiritus(spiritus);
        assertEquals(1, fadHistorik.getSpiritusArrayList().size());
        fadHistorik.removeSpiritus(spiritus);
        assertEquals(0, fadHistorik.getSpiritusArrayList().size());
    }

    @Test
    public void testCreateOmhældning() {
        int udtagelsesvolumen = 5;
        LocalDate date = LocalDate.of(2023, 01, 02);
        Medarbejder medarbejder = new Medarbejder(1, "Ole Hansen", "Destillering");
        Destillering destillering2 = new Destillering(2, "Eksempel Destilleri", 1000.0, LocalDate.now().minusYears(1), LocalDate.now(), 40.0, "Malt1", false, "Ingen kommentarer", null, medarbejder);
        Destillering destillering = new Destillering(1, "Eksempel Destilleri", 1000.0, LocalDate.now().minusYears(1), LocalDate.now(), 40.0, "Malt1", false, "Ingen kommentarer", null, medarbejder);
        fad1 = new Fad(1, "Bourbon", 1000, 100, 0, "Leverandør A", "Tidligere Indhold A", null);
        fad2 = new Fad(2, "Bourbon", 430, 100, 0, "Leverandør B", "Tidligere Indhold B", null);
        fad3 = new Fad(3, "Bourbon", 430, 100, 0, "Leverandør C", "Tidligere Indhold C", null);
        FadHistorik fadHistorik1 = new FadHistorik(69, udtagelsesvolumen, LocalDate.of(2023, 01, 01), destillering2);

        fadHistorik1.addFad(fad1);

        FadHistorik fadHistorikResult = fadHistorik1.createOmhældning(fad2, date, destillering2, udtagelsesvolumen);


        assertEquals(fadHistorikResult, fad2.getFadHistorik());
        assertEquals(95, fad1.getNuværendeVolumen());
    }
}