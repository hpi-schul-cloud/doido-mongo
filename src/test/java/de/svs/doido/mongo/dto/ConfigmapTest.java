package svs.doido.mongo.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ConfigmapTest {
    @Test
    void testGetterSetterName() {
        Configmap c = new Configmap();
        String name = "hausboot";
        c.setName(name);
        assertEquals(name,c.getName());
    }

    @Test
    void testGetterSetterUri() {
        Configmap c = new Configmap();
        String uri = "mongos://test.local/de?uir=10";
        c.setUri(uri);
        assertEquals(uri,c.getUri());
    }

}