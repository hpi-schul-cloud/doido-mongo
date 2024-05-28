package svs.doido.mongo.kubernetes.confgimap;

import svs.doido.mongo.dto.ConfigmapDTO;
import io.fabric8.kubernetes.api.model.Configmap;
import io.fabric8.kubernetes.client.KubernetesClient;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;


public class Read {
    
    private final KubernetesClient kubernetesClient;

    @Inject
    ConfigmapDTO cfg;

    @Inject
    KubernetesClient client;

    public ConfigmapDTO readConfigmap() {
        return cfg;
    }
}