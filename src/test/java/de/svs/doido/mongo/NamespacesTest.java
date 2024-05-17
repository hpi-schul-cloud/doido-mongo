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

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;




@WithKubernetesTestServer
@QuarkusTest
class NamespacesTest {

    @KubernetesTestServer
    KubernetesServer mockServer;
    @Inject
    KubernetesClient client;

    @BeforeEach
    public void before() {
        final Namespace namespace1 = new NamespaceBuilder().withNewMetadata().withName("namespaceA").and().build();
        final Namespace namespace2 = new NamespaceBuilder().withNewMetadata().withName("namespaceB").and().build();

        // Set up Kubernetes so that our "pretend" pods namespaces created
        client.namespaces().resource(namespace1).create();
        client.namespaces().resource(namespace2).create();
    }

    @AfterEach
    public void after() {
        final Namespace namespace1 = new NamespaceBuilder().withNewMetadata().withName("namespaceA").and().build();
        final Namespace namespace2 = new NamespaceBuilder().withNewMetadata().withName("namespaceB").and().build();

        // Set up Kubernetes so that our "pretend" namespaces are deleted
        client.namespaces().resource(namespace1).delete();
        client.namespaces().resource(namespace2).delete();
    }


    @Test
    public void testInteractionWithAPIServerForNamespaces() {
        given()
            .when().get("/namespace")
            .then()
            .statusCode(200)
            .body("size()", is(2));
    }

}