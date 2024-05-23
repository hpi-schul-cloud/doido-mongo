package svs.doido.mongo.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@QuarkusTest
class ConfigmapTest {
    @ToString
    @EqualsAndHashCode
    public static class testConfigmapClass extends Configmap {

    }
    
    private Configmap c;
    private Configmap d;

    @BeforeEach
    public void before() {
        c = new Configmap();
        d = new Configmap();
    }

    @Test
    void testGetterSetterName() {
        String name = "hausboot";
        c.setName(name);
        assertEquals(name,c.getName());
    }

    @Test
    void testGetterSetterUri() {
        String uri = "ftp://foo.bar.com/";
        c.setUri(uri);
        assertEquals(uri,c.getUri());
    }

    @Test
    void testGetterSetterUriFail() {
        String uri = "proto:foo.bar.com/";
        c.setUri(uri);
        assertEquals("", c.getUri());
    }

    @Test
    void testToString() {
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        c.setName(name);
        c.setUri(uri);
        d.setName(name);
        d.setUri(uri);
        assertEquals(d.toString(), c.toString());
    }

    @Test
    void testHashcode() {
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        assertEquals(d.hashCode(), c.hashCode());
        c.setName(name);
        assertNotEquals(d.hashCode(), c.hashCode());
        c.setUri(uri);
        assertNotEquals(d.hashCode(), c.hashCode());
        d.setName(name);
        assertNotEquals(d.hashCode(), c.hashCode());
        d.setUri(uri);
        assertEquals(d.hashCode(), c.hashCode());
        d.setName("mu");
        assertNotEquals(d.hashCode(), c.hashCode());
        d.setName(name);
        d.setUri("mongo://uri.foo.bar.com/?uir=a");
        assertNotEquals(d.hashCode(), c.hashCode());
    }
}