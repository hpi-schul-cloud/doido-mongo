package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.List;
import jakarta.inject.Inject;
import svs.doido.mongo.dto.Configmap;
import svs.doido.mongo.kubernetes.Configmap.Read;

@Path("/configmap")
public class ConfigmapApi {

    private final KubernetesClient kubernetesClient;

    @Inject
    Read cfg;

    public ConfigmapApi(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Path("/{namespace}/{configmapName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Configmap ConfigmapApi(String nameespac, String configmapName) {
        return kubernetesClient.pods().inNamespace(namespace).list().getItems();
    }
}