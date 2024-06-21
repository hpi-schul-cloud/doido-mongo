package svs.doido.mongo;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import jakarta.inject.Inject;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapList;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ObjectMeta;

import java.util.Map;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;




@WithKubernetesTestServer
@QuarkusTest
class ConfigmapApiTest {

    @KubernetesTestServer
    KubernetesServer mockServer;
    @Inject
    KubernetesClient client;

    @BeforeEach
    public void before() {
        final Namespace namespace1 = new NamespaceBuilder().withNewMetadata().withName("namespaceA").and().build();
        final ConfigMap cfgTest = new ConfigMapBuilder().withNewMetadata().withName("test").withNamespace("namespaceA").and().build();
        final HashMap<String,String> data = new HashMap<String,String>();
        data.put("uri","http://www.test.de");
        cfgTest.setData(data);

        // Set up Kubernetes so that our "pretend" pods namespaces created
        client.namespaces().resource(namespace1).create();
        client.configMaps().resource(cfgTest).create();
    }

    @AfterEach
    public void after() {
        final Namespace namespace1 = new NamespaceBuilder().withNewMetadata().withName("namespaceA").and().build();

        for ( ConfigMap cfg : client.configMaps().inNamespace("namespaceA").list().getItems()) {
            client.configMaps().resource(cfg).delete();
        }
        // Set up Kubernetes so that our "pretend" namespaces are deleted
        client.namespaces().resource(namespace1).delete();
    }


    @Test
    void testInteractionWithGetConfigmaps() {
        given()
            .when().get("/configmap/namespaceA/test")
            .then()
            .statusCode(200)
            .body("size()", is(2));
    }
    

    @Test
    void testInteractionWithPostConfigmaps1() {
        given()
            .when().post("/configmap/namespaceA/tes2t")
            .then()
            .statusCode(204);
    }
    

    @Test
    void testInteractionWithPostConfigmapsExistAndNoLabels() {
        given()
            .when().post("/configmap/namespaceA/test")
            .then()
            .statusCode(403);
    }
    

    @Test
    void testInteractionWithPostConfigmapsExistAndLabels() {
        ConfigMap configmap = new ConfigMapBuilder().withNewMetadata().withName("test3").withNamespace("namespaceA").and().build();
        Map<String,String> labels = new HashMap<>();
        ObjectMeta meta;
        labels.put("app.kubernetes.io/name", "doido-mongo");
        meta = configmap.getMetadata();
        meta.setLabels(labels);
        configmap.setMetadata(meta);
        client.configMaps().create(configmap);
        given()
            .when().post("/configmap/namespaceA/test3")
            .then()
            .statusCode(204);
    }
    

    @Test
    void testInteractionWithPostConfigmapsExistAndLabelsWrong() {
        ConfigMap configmap = new ConfigMapBuilder().withNewMetadata().withName("test3").withNamespace("namespaceA").and().build();
        Map<String,String> labels = new HashMap<>();
        ObjectMeta meta;
        labels.put("app.kubernetes.io/name", "doido-mongoB");
        meta = configmap.getMetadata();
        meta.setLabels(labels);
        configmap.setMetadata(meta);
        client.configMaps().create(configmap);
        given()
            .when().post("/configmap/namespaceA/test3")
            .then()
            .statusCode(204);
    }

}