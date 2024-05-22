package svs.doido.mongo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import org.apache.commons.validator.routines.UrlValidator;

@Getter
@Setter
@Data
public class Configmap {
    
    private String name;
    private String uri;

    public void setUri(String uriIn) {
        UrlValidator urlValidator = new UrlValidator( ALLOW_ALL_SCHEMES );
        if (urlValidator.isValid( uriIn )) {
            uri = uriIn;
        }
    }
}