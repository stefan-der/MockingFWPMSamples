import Exceptions.DependencyException;
import Exceptions.RolloBedienKonsoleException;

public class RolloBedienKonsole {

    private IRolloManagement rolloManagementSoftware=null;
    private String letzterBefehl="";

    public RolloBedienKonsole(IRolloManagement rolloManagementSoftwareInstanz){
        this.rolloManagementSoftware=rolloManagementSoftwareInstanz;
    }
    public void hochFahrenKlick(int ausgewaehltesRollo){

        /* Die RolloSoftware */
        int rueckgabewertVonRolloManagement = this.rolloManagementSoftware.hochFahrenSenden(ausgewaehltesRollo);
        switch (rueckgabewertVonRolloManagement){
            case 0:
                // alles läuft korrekt
                signalisiereOkImDisplay();
                break;
            case 1:
                throw new RolloBedienKonsoleException("Es wurde ein Anwendungsfehler erkannt");
            case 2:
                throw new RolloBedienKonsoleException("Der Befehl wird derzeit vearbeitet");
            default:
                throw new DependencyException("Die Management Software hat einen unbekannten Rückgabewert zurückgeliefert");
        }
    }
    public void signalisiereOkImDisplay(){
        /* Business Logik */
    }

}
