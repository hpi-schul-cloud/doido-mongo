package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.Collections;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Set;
import java.lang.Iterable;

@Path("/namespace")
public class Namespaces {

    private final KubernetesClient kubernetesClient;
    

    public Namespaces(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Path("/")
    public List<String> namespaces() {
        NamespaceList namespacesL = kubernetesClient.namespaces().list();
        List<String> namespacesNames = Collections.newListFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        for ( Namespace n: namespacesL.getItems() ) {
            namespacesNames.add(n.getMetadata().getName());
        }
        return namespacesNames;
    }
}