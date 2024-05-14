package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.List;

@Path("/pod")
public class Pods {

    private final KubernetesClient kubernetesClient;

    public Pods(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Path("/{namespace}")
    @Produces(MediaType.APPLICATION_JSON)    
    public List<Pod> pods(String namespace) {
        return kubernetesClient.pods().inNamespace(namespace).list().getItems();
    }
}