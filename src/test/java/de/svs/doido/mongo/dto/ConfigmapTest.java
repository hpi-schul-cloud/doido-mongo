package svs.doido.mongo.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ConfigmapTest {
    
    private Configmap c;

    @BeforeEach
    public void before() {
        c = new Configmap();
    }

    @Test
    void testGetterSetterName() {
        String name = "hausboot";
        c.setName(name);
        assertEquals(name,c.getName());
    }

    @Test
    void testGetterSetterUri() {
        String uri = "proto://foo.bar.com/";
        c.setUri(uri);
        assertEquals(uri,c.getUri());
    }

    @Test
    void testGetterSetterUriFail() {
        String uri = "proto:foo.bar.com/";
        c.setUri(uri);
        assertEquals("", c.getUri());
    }
}