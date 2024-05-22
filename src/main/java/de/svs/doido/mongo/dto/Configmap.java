package svs.doido.mongo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import org.apache.commons.validator.routines.UrlValidator;


@Data
public class Configmap {

    @Getter
    @Setter
    private String name;
    
    @Getter
    private String uri;

    public void setUri(String uriIn) {
        UrlValidator urlValidator = new UrlValidator( UrlValidator.ALLOW_ALL_SCHEMES );
        if (urlValidator.isValid( uriIn )) {
            uri = uriIn;
        }
    }
}