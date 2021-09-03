package sample;

public class PersonValidator {
    public static class NavnValidation {
        static boolean testRegx(String ... strings){
            for (String str : strings){
                if (!str.matches("[A-ZÅØÆ][a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$")){
                    return false;
                }
            }
            return true;
        }
    }

    public static class EmailValidator {
        static boolean testRegx(String ... strings){
            for (String str : strings){
                if (!str.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9-]+.[a-z]{2,3}$")){
                    return false;
                }
            }
            return true;
        }



    }

    public static class NumberValidation {
        static boolean testRegx(String ... strings){
            for (String str : strings){
                if (!str.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]+$")){
                    return false;
                }
            }
            return true;
        }

    }
}
