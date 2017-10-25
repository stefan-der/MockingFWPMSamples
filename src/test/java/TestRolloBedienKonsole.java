import Exceptions.DependencyException;
import Exceptions.RolloSteuerungException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestRolloBedienKonsole {

    @Mock
    IRolloManagement rolloManagementInstanzMock = mock(IRolloManagement.class);
    @Spy
    IRolloManagement rolloManagementInstanzSpy = spy(IRolloManagement.class);

    @Test(expected = RolloSteuerungException.class)
    public void rolloBedienKonsole_Reaktion_Auf_Nicht_Vorhandenen_Rollo(){

        // Record / Setup
        when(rolloManagementInstanzMock.hochFahrenSenden(-1)).thenReturn(1);

        // Replay
        RolloBedienKonsole aktuelleRolloBedienKonsole =new RolloBedienKonsole(rolloManagementInstanzMock);
        aktuelleRolloBedienKonsole.hochFahrenKlick(-1);

    }
    @Test(expected = DependencyException.class)
    public void rolloBedienkonsole_Reaktion_Auf_Nicht_Bekannten_Ausgabe_Der_RolloManagementSoftware(){
        when(rolloManagementInstanzMock.hochFahrenSenden(anyInt())).thenReturn(100);
        RolloBedienKonsole aktuelleRolloBedienKonsole =new RolloBedienKonsole((rolloManagementInstanzMock));
        aktuelleRolloBedienKonsole.hochFahrenKlick(20);
    }


    /* Spy */
    @Test
    public void rolloBedienKonsole_Reagiert_Normal(){
        RolloBedienKonsole aktuelleRolloBedienKonsole =new RolloBedienKonsole(rolloManagementInstanzSpy);
        aktuelleRolloBedienKonsole.hochFahrenKlick(1);
        Mockito.verify(rolloManagementInstanzSpy).hochFahrenSenden(1);
    }


}
