package svs.doido.mongo.kubernetes.confgimap;

import svs.doido.mongo.dto.Configmap;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.client.KubernetesClient;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;


public class Read {

    @Inject
    Configmap cfg;

    @Inject
    KubernetesClient client;

    public Configmap readConfigmap() {
        return cfg;
    }
}