package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import svs.doido.mongo.dto.Configmap;
import svs.doido.mongo.kubernetes.configmap.Read;

@Path("/configmap")
public class ConfigmapApi {

    @Inject
    Read cfg;

    @GET
    @Path("/{namespace}/{configmapName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Configmap configmaps(String namespace, String configmapName) {
        return cfg.readConfigmap(namespace,configmapName);
    }
}