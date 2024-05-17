package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.Collections;
import java.util.List;

@Path("/namespace")
public class Namespaces {

    private final KubernetesClient kubernetesClient;
    

    public Namespaces(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Path("/")
    public List<Namespace> namespaces() {
        return kubernetesClient.namespaces().list().getItems();
    }
}