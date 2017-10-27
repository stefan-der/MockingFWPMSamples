import Exceptions.DependencyException;
import Exceptions.RolloBedienKonsoleException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestRolloBedienKonsole {

    @Mock
    IRolloManagement rolloManagementInstanzMock = mock(IRolloManagement.class);
    @Test
    public void rolloBedienKonsole_Reagiert_Normal(){
        // Setup
        RolloBedienKonsole reelleRolloBedienKonsole = new RolloBedienKonsole(rolloManagementInstanzMock);
        when(rolloManagementInstanzMock.hochFahrenSenden(anyInt())).thenReturn(0);

        // Execute
        reelleRolloBedienKonsole.hochFahrenKlick(1);

        // Verify
        Mockito.verify(rolloManagementInstanzMock).hochFahrenSenden(1);
    }
    @Test(expected = RolloBedienKonsoleException.class)
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

}
