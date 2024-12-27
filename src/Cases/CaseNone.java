package Cases;

public class CaseNone extends Case {
    /*
    @affiche qu'aucun argument n'a été détecté. Propose de taper -h ou --help
    */
    public void noArgs()
    {
        System.out.println("Aucun argument détecté. Pour accéder à la documentation utilisez : -h ou --help");
    }
}