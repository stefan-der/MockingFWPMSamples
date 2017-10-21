import Exceptions.DependencyException;
import Exceptions.RolloSteuerungException;

public class RolloBedienKonsole {

    private IRolloManagement rolloManagementSoftware=null;

    public RolloBedienKonsole(IRolloManagement rolloManagementSoftwareInstanz){
        this.rolloManagementSoftware=rolloManagementSoftwareInstanz;
    }
    public void hochFahrenKlick(int ausgewaehltesRollo){

        /* Die RolloSoftware */
        int rueckgabewertVonRolloManagement = this.rolloManagementSoftware.hochFahrenSenden(ausgewaehltesRollo);
        switch (rueckgabewertVonRolloManagement){
            case 0:
                // alles läuft korrekt
                break;
            case 1:
                throw new RolloSteuerungException("Es wurde ein Anwendungsfehler erkannt");
            case 2:
                throw new RolloSteuerungException("Der Befehl wird derzeit vearbeitet");
            default:
                throw new DependencyException("Die Management Software hat einen unbekannten Rückgabewert zurückgeliefert");
        }
    }

}
