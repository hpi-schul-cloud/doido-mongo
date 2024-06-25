package svs.doido.mongo.kubernetes.configmap;

import svs.doido.mongo.dto.Configmap;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.KubernetesClient;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;

import java.util.Map;
import java.util.HashMap;

@RequestScoped
public class Write {

    private static final String NAME_LABEL = "app.kubernetes.io/name";
    private static final String NAME_LABEL_VALUE = "doido-mongo";

    @Inject
    KubernetesClient client;

    public boolean writeConfigmap(String namespace, Configmap cfg) {
        ConfigMap configmap = client.configMaps().inNamespace(namespace).withName(cfg.getName()).get();
        Map<String,String> labels;
        Map<String,String> data;
        ObjectMeta meta;

        if(configmap != null) {
            labels = configmap.getMetadata().getLabels();
            if( 
                labels.containsKey(NAME_LABEL) &&
                labels.get(NAME_LABEL).equals(NAME_LABEL_VALUE)
            ) {
                data = configmap.getData();
                data.put("uri", cfg.getUri());
                configmap.setData(data);
                client.configMaps().createOrReplace(configmap);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            labels = new HashMap<>();
            data = new HashMap<>();
            labels.put(NAME_LABEL, NAME_LABEL_VALUE);
            data.put("uri", cfg.getUri());
            configmap = new ConfigMapBuilder().withNewMetadata().withName(cfg.getName()).withNamespace(namespace).and().build();
            configmap.setData(data);
            meta = configmap.getMetadata();
            meta.setLabels(labels);
            configmap.setMetadata(meta);
            client.configMaps().create(configmap);
            return true;
        }
    }
}