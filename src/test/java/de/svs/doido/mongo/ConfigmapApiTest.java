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

        // Set up Kubernetes so that our "pretend" namespaces are deleted
        client.namespaces().resource(namespace1).delete();
        for ( ConfigMap cfg : client.configMaps().list().getItems()) {
            client.configMaps().resource(cfg).delete();
        }
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

}