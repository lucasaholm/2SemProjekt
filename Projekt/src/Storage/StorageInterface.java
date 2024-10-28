package Storage;

import Application.Model.*;

import java.util.ArrayList;
import java.util.List;

public interface StorageInterface {

        // ------------------------------------------------------------------------- Lager
        List<Lager> getLagere();

        void addLager(Lager lager);

        void removeLager(Lager lager);

        void clearLagerListe();

        // ------------------------------------------------------------------------- Destillering
        List<Destillering> getDestilleringer();

        void addDestillering(Destillering destillering);

        void removeDestillering(Destillering destillering);

        // ------------------------------------------------------------------------- Medarbejder
        List<Medarbejder> getMedarbejderer();

        void addMedarbejder(Medarbejder medarbejder);

        void removeMedarbejder(Medarbejder medarbejder);

        // ------------------------------------------------------------------------- FadHistorik
        List<FadHistorik> getFadHistorikker();

        void addFadHistorik(FadHistorik fadHistorik);

        void removeFadHistorik(FadHistorik fadHistorik);

        // ------------------------------------------------------------------------- Fad
        List<Fad> getFade();

        void addFad(Fad fad);

        void removeFad(Fad fad);

        // ------------------------------------------------------------------------- Spiritus
        List<Spiritus> getSpiritusser();

        void addSpiritus(Spiritus spiritus);

        void removeSpiritus(Spiritus spiritus);
        // -------------------------------------------------------------------------

    }


