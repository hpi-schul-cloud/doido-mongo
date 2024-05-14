package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.List;

@Path("/namespace")
public class Namespaces {

    private final KubernetesClient kubernetesClient;

    public Namespaces(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public List<Namespace> namespaces() {
        return kubernetesClient.namespaces().list().getItems();
    }
}