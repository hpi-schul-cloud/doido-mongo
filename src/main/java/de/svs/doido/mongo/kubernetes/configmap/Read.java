package svs.doido.mongo.kubernetes.configmap;

import svs.doido.mongo.dto.Configmap;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.KubernetesClient;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;


@RequestScoped
public class Read {

    @Inject
    Configmap cfg;

    @Inject
    KubernetesClient client;

    public Configmap readConfigmap(String namespace, String name) {
        ConfigMap configmap = client.configMaps().inNamespace(namespace).withName(name);
        cfg.setName(configmap.getMetadata().getName());
        cfg.setUri(confgimap.getData().get("uri"));
        return cfg;
    }
}