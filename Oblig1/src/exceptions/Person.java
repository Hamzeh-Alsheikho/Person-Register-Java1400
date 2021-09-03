package exceptions;

import sample.Dato;
// this class is not in need anymore:)
public class Person {
    String navn;
    int alder;
    Dato fødselsdato;
    String epost;
    String telfonnr;

    public Person (String navn, int alder, Dato fødselsdato,String epost, String telfonnr) {
        this.navn = navn;
        this.alder = alder;
        this.fødselsdato= fødselsdato;
        this.epost= epost;
        this.telfonnr=telfonnr;
    }


    public String toString(){
        String vis = navn+";"+ fødselsdato+";"
                +alder+";"+epost+";"+telfonnr;
        return vis;

    }



}
