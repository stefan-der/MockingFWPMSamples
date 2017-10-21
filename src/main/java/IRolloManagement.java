public interface IRolloManagement {

    /**
     Rückgabe = 0 wenn Ok
     Rückgabe = 1 bei Fehler
     Rückgabe = 2 Befehl wird bereits bearbeitet
     */
    public int hochFahrenSenden(int welchesRollo);


}
