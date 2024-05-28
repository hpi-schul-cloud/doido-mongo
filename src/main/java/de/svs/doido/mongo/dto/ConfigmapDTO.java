package svs.doido.mongo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.apache.commons.validator.routines.UrlValidator;
import jakarta.enterprise.context.RequestScoped;


@ToString
@EqualsAndHashCode
@RequestScoped
public class ConfigmapDTO {
    @Getter
    @Setter
    private String name = "";

    @Getter
    private String uri = "";

    public void setUri(String uriIn) {
        UrlValidator urlValidator = new UrlValidator( UrlValidator.ALLOW_ALL_SCHEMES|UrlValidator.ALLOW_LOCAL_URLS );
        if (urlValidator.isValid( uriIn )) {
            uri = uriIn;
        }
    }
}