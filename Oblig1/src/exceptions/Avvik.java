package exceptions;

public class Avvik {
    public static int sjekkAlder(int aldern) throws InvalidAgeException {
        if (aldern < 0 || aldern > 120) {
            throw new InvalidAgeException("Feil input age");
        }
        return aldern;
    }

    public static int sjekkDag(int dag) throws InvalidAgeException {
        if (dag < 1 || dag > 31) {
            throw new InvalidDateException("Feil input day");
        }
        return dag;
    }

    public static int sjekkMåned(int måned) throws InvalidAgeException {
        if (måned < 1 || måned > 12) {
            throw new InvalidDateException("Feil input month");
        }
        return måned;
    }

    public static int sjekkÅr(int år) throws InvalidAgeException {
        if (år < 1900 || år > 2020) {
            throw new InvalidDateException("Feil input year");
        }
        return år;
    }

    public static String sjekkTlfNummer(String tlf) throws IllegalArgumentException {
        if (!tlf.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]+$")){
                throw new IllegalArgumentException("");
            }
        return tlf;
    }

    public static  String sjekkEmail (String eml) throws  IllegalArgumentException{
        if (!eml.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9-]+.[a-z]{2,3}$")){
            throw new IllegalArgumentException("");
                }
                return eml;
    }

    public static  String sjekkName (String nam) throws  IllegalArgumentException{
        if (!nam.matches("[A-ZÅØÆ][a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$")){
            throw new IllegalArgumentException("");
                }
                return nam;
    }

    public static class InvalidAgeException extends IllegalArgumentException {
            public InvalidAgeException (String msg){
                super(msg);
            }
    }
}
