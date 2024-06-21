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

    @Inject
    KubernetesClient client;

    public void writeConfigmap(String namespace, String name, Configmap cfg) {
        ConfigMap configmap = client.configMaps().inNamespace(namespace).withName(cfg.getName()).get();
        Map<String,String> labels;
        Map<String,String> data;
        ObjectMeta meta;

        if(configmap != null) {
            labels = configmap.getMetadata().getLabels();
            if( 
                labels.containsKey("app.kubernetes.io/name") &&
                labels.get("app.kubernetes.io/name").equals("doido-mongo")
            ) {
                data = configmap.getData();
                data.put("uri", cfg.getUri());
                configmap.setData(data);
                client.configMaps().resource(configmap).createOrReplace();
            }
        }
        else {
            labels = new HashMap<String,String>();
            data = new HashMap<String,String>();
            labels.put("app.kubernetes.io/name", "doido-mongo");
            data.put("uri", cfg.getUri());
            configmap = new ConfigMapBuilder().withNewMetadata().withName(cfg.getName()).withNamespace(namespace).and().build();
            configmap.setData(data);
            meta = configmap.getMetadata();
            meta.setLabels(labels);
            configmap.setMetadata(meta);
            client.configMaps().resource(configmap).create();
        }
    }
}