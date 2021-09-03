package exceptions;

import java.io.IOException;

public class InvalidPersonFormatException extends IOException {
        InvalidPersonFormatException (String meg){
            super(meg);
        }
}
