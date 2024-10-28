package Test;

import Application.Controller.Controller;
import Application.Model.Fad;
import Application.Model.Lager;
import Storage.Storage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LagerOgTest {


    private Lager lager;
    private Controller controller = new Controller(Storage.getInstance());
    private Storage storage = Storage.getInstance();

    @Before
    public void setUp(){
        controller.clearLagerListe();
        lager = new Lager(1,"Test lager", "Test lokation", 10);
        controller.addLager(lager);
    }


    @Test
    public void TestAddLager(){
        assertEquals(1, storage.getLagere().size());
        assertEquals(lager, storage.getLagere().get(0));
    }

    @Test
    public void TestRemoveLager(){
        storage.addLager(lager);
        storage.removeLager(lager);
        assertEquals(lager, storage.getLagere().get(0));
    }

    @Test
    public void testAddFadTilLager(){
        Fad fad = new Fad(1, "Type A", 50.0,40.0,2, "Lev A", "Burbon", lager);
        controller.addFadTilLager(lager, fad);
        assertEquals(1,lager.getFade().size());
        assertEquals(fad, lager.getFade().get(0));
    }

    @Test
    public void testUpdateFad(){
        Fad fad = new Fad(1, "Type A", 50.0,40.0,2, "Lev A", "Burbon", lager);
        controller.addFadTilLager(lager, fad);
        controller.updateFad(fad, 2, "Type B", 3, "Lev B", "Gin", lager);
        assertEquals(2, fad.getId());
        assertEquals("Type B",fad.getFadType());
        assertEquals(3, fad.getAntalGangeBrugt());
        assertEquals("Lev B", fad.getLeverandør());
        assertEquals("Gin", fad.getTidligereIndhold());
    }

    @Test
    public void testRemoveFadFromLager(){
        Fad fad = new Fad(1, "Type A", 50.0,40.0,2, "Lev A", "Burbon", lager);
        controller.addFadTilLager(lager, fad);
        controller.deleteFad(lager, fad);
        assertEquals(0, lager.getFade().size());
    }




}
