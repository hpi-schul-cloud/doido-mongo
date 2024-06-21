package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import svs.doido.mongo.dto.Configmap;
import svs.doido.mongo.kubernetes.configmap.Read;
import svs.doido.mongo.kubernetes.configmap.Write;

@Path("/configmap")
public class ConfigmapApi {

    @Inject
    Read cfgRead;
    
    @Inject
    Write cfgWrite;

    @GET
    @Path("/{namespace}/{configmapName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Configmap configmapsRead(String namespace, String configmapName) {
        return cfgRead.readConfigmap(namespace,configmapName);
    }

    @POST
    @Path("/{namespace}/{configmapName}")
    @Produces(MediaType.APPLICATION_JSON)
    public void configmapsWrite(String namespace, String configmapName) {
        Configmap cfg = new Configmap();
        cfg.setName(configmapName);
        cfg.setUri("http://www.test.org";)
        cfgWrite.writeConfigmap(namespace,configmapName,cfg);
    }
}