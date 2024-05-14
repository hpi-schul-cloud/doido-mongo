package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.List;

@Path("/namespaces")
public class Namespaces {

    private final KubernetesClient kubernetesClient;

    public Namespaces(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Path("/")
    public List<Namespace> Namespaces() {
        return kubernetesClient.namespaces().list().getItems();
    }
}