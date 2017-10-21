import Exceptions.DependencyException;
import Exceptions.RolloSteuerungException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestRolloBedienKonsole {

    @Mock
    IRolloManagement rolloSoftwareInstanzMock = mock(IRolloManagement.class);
    IRolloManagement rolloSoftwareInstanzSpy = spy(IRolloManagement.class);

    /* Stub */
    @Test(expected = RolloSteuerungException.class)
    public void rolloSteuerung_Reaktion_Auf_Nicht_Vorhandenen_Rollo(){
        when(rolloSoftwareInstanzMock.hochFahrenSenden(anyInt())).thenReturn(1);
        RolloBedienKonsole aktuelleRolloBedienKonsole =new RolloBedienKonsole(rolloSoftwareInstanzMock);
        aktuelleRolloBedienKonsole.hochFahrenKlick(-1);
    }
    @Test(expected = DependencyException.class)
    public void rolloSteuerung_Reaktion_Auf_Nicht_Bekannten_Ausgabe_Der_RolloManagementSoftware(){
        when(rolloSoftwareInstanzMock.hochFahrenSenden(anyInt())).thenReturn(100);
        RolloBedienKonsole aktuelleRolloBedienKonsole =new RolloBedienKonsole((rolloSoftwareInstanzMock));
        aktuelleRolloBedienKonsole.hochFahrenKlick(20);
    }


    /* Spy */
    @Test
    public void rolloSteuerung_Reagiert_Normal(){
        RolloBedienKonsole aktuelleRolloBedienKonsole =new RolloBedienKonsole(rolloSoftwareInstanzSpy);
        aktuelleRolloBedienKonsole.hochFahrenKlick(1);
        Mockito.verify(rolloSoftwareInstanzSpy).hochFahrenSenden(1);
    }


}
