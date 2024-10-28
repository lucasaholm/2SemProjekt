package Test;

import Application.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DestilleringTest {

    private Destillering destillering;

    @BeforeEach
    void setUp(){
        destillering = new Destillering(1, "Test Navn", 100.0, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31), 40.0, "Malt Batch 1", false, "Ingen kommentar", null, null);
    }

    @Test
    void testCreateFadHistorik() {

        //indstil inputparametre
        int id = 1;
        int udtagelsesDestillering = 0;
        LocalDate påfyldningsdato = LocalDate.of(2023, 3, 28);
        Medarbejder medarbejderDestillering = new Medarbejder(1, "John doe", "Direktør");
        Lager lager = new Lager(1, "Salls whisky", "Salls", 1000);
        Fad fad = new Fad(1,"Burbon", 100, 50,1,"FedEX", "Burbon", lager);

        // Kald metoden og få resultatet
        FadHistorik fadhistorik = destillering.createFadHistorik(id,  udtagelsesDestillering, påfyldningsdato, destillering, fad);

        // Bekræft, at det returnerede objekt ikke er null
        Assertions.assertNotNull(fadhistorik);

        // Bekræft, at de relevante felter i det returnerede objekt er korrekte
        Assertions.assertEquals(id, fadhistorik.getId());
        Assertions.assertEquals(påfyldningsdato, fadhistorik.getPåfyldningsDato());

        // Bekræft, at fadhistorik objektet er blevet tilføjet til fade-listen
        Assertions.assertTrue(destillering.getFade().contains(fadhistorik));
    }
    @Test
    void addFadhistorik() {
        Medarbejder medarbejder = new Medarbejder(1, "Test Medarbejder", "Test Position");
        Lager lager = new Lager(1, "Salls whisky", "Salls", 1000);
        Fad fad = new Fad(1,"Burbon", 100, 50,1,"FedEX", "Burbon", lager);
        FadHistorik fadHistorik1 = new FadHistorik(1, 10, LocalDate.now(), destillering);

        destillering.addFadhistorik(fadHistorik1);

        List<FadHistorik> fade = destillering.getFade();
        Assertions.assertTrue(fade.contains(fadHistorik1), "FadHistorik objektet skulle være tilføjet til listen.");

        destillering.addFadhistorik(fadHistorik1);
        Assertions.assertEquals(1, fade.size(), "FadHistorik objektet skulle ikke være tilføjet igen, da det allerede er i listen.");
    }

    @Test
    void removeFadHistorik(){
        Medarbejder medarbejder = new Medarbejder(1, "Test Medarbejder", "Test Position");
        Lager lager = new Lager(1, "Salls whisky", "Salls", 1000);
        Fad fad = new Fad(1,"Burbon", 100, 50,1,"FedEX", "Burbon", lager);
        FadHistorik fadHistorik1 = new FadHistorik(1, 10, LocalDate.now(), destillering);
        FadHistorik fadHistorik2 = new FadHistorik(2, 10, LocalDate.now(), destillering);

        destillering.addFadhistorik(fadHistorik1);
        destillering.addFadhistorik(fadHistorik2);

        List<FadHistorik> fade = destillering.getFade();
        Assertions.assertEquals(2, fade.size(), "Der skulle være 2 FadHistorik objekter i listen.");

        destillering.removeFadhistorik(fadHistorik1);

        // Opdater fade listen efter at have kaldt removeFadHistorik
        fade = destillering.getFade();

        Assertions.assertEquals(1, fade.size(), "Der skulle kun være 1 FadHistorik objekt i listen efter fjernelse af det første.");
        Assertions.assertFalse(fade.contains(fadHistorik1), "FadHistorik1 objektet skulle ikke længere være i listen.");
        Assertions.assertTrue(fade.contains(fadHistorik2), "FadHistorik2 objektet skulle stadig være i listen.");
    }

}










