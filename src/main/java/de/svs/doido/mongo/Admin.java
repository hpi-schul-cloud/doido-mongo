package svs.doido.mongo;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.inject.Inject;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;
import com.mongodb.client.MongoClient;
import io.quarkus.mongodb.runtime.MongoClientConfig;
import java.util.List;

@Path("/admin")
public class Admin {

    private final KubernetesClient kubernetesClient;
    @Inject MongoClient mongoClient;
    

    public Admin(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Path("/{clientname}")
    public void testConnect(String clientname) {
        MongoClient monClient = mongoClient.createMongoClient(clientname);
        MongoClientConfig monClientCfg = mongoClient.getMatchingMongoClientConfig(clientname);
    }
}
