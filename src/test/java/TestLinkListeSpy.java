import Exceptions.AccessDeniedException;
import org.junit.Assert;
import org.junit.Test;
import org.omg.CORBA.Any;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestLinkListeSpy {


    @Test(expected = AccessDeniedException.class)
    public void abfangenEinesMethodenAufrufs(){

        List<String> reelesListenObjekt = new LinkedList<String>();
        List<String> spyListenObjekt = spy(reelesListenObjekt);

        /* Standard Listen Funktionalität da nicht überschrieben */
        spyListenObjekt.add("erster Eintrag");
        spyListenObjekt.add("zweiter Listeneintrag");

        /* Abfangen der Listen Funktionalität add */
        when(spyListenObjekt.add(anyString())).thenThrow(AccessDeniedException.class);

        /* Ausführung der remove Funktion mit dem  */
        /* wirft keine Exception da nur die Funktion add überschrieben wurde) */
        spyListenObjekt.remove(1);

        /* wirft die vordefinierte Exception */
        spyListenObjekt.add("dritter Eintrag");

    }
}
