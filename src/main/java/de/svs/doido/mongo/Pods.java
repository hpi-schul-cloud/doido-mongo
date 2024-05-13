package svs.doido.mongo;

import io.fabric8.kubernetes.client.KubernetesClient;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;

@Path("/pod")
public class Pods {

    private final KubernetesClient kubernetesClient;

    public Pods(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Path("/{namespace}")
    public List<Pod> pods(String namespace) {
        return kubernetesClient.pods().inNamespace(namespace).list().getItems();
    }
}