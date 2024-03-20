package svs.doido.mongo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/me")
public class MeResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String TestTextMe() {
        return "It's me the POD";
    }
}
