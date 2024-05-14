package svs.doido.mongo;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesTestServer;
import io.quarkus.test.kubernetes.client.WithKubernetesTestServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import jakarta.inject.Inject;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;




@WithKubernetesTestServer
@QuarkusTest
class NamespacesTest {
/*
    @KubernetesTestServer
    KubernetesServer mockServer;
    @Inject
    KubernetesClient client;

    @BeforeEach
    public void before() {
        final Pod pod1 = new PodBuilder().withNewMetadata().withName("pod1").withNamespace("test").and().build();
        final Pod pod2 = new PodBuilder().withNewMetadata().withName("pod2").withNamespace("test").and().build();

        // Set up Kubernetes so that our "pretend" pods are created
        client.pods().resource(pod1).create();
        client.pods().resource(pod2).create();
    }

    @Test
    public void testInteractionWithAPIServer() {
        given()
            .when().get("/pod/test")
            .then().body("size()", is(2));
    }
*/
}