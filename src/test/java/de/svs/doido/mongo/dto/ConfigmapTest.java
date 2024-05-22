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
        String uri = "ftp://foo.bar.com/";
        c.setUri(uri);
        assertEquals(uri,c.getUri());
    }

    @Test
    void testGetterSetterUriFail() {
        Configmap c = new Configmap();
        String uri = "proto:foo.bar.com/";
        c.setUri(uri);
        assertEquals("", c.getUri());
    }

    @Test
    void testToString() {
        Configmap c = new Configmap();
        Configmap d = new Configmap();
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
        Configmap c = new Configmap();
        Configmap d = new Configmap();
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        c.setName(name);
        c.setUri(uri);
        d.setName(name);
        d.setUri(uri);
        assertEquals(d.hashCode(), c.hashCode());
    }

    @Test
    void testEquals () {
        Configmap c = new Configmap();
        Configmap d = new Configmap();
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        c.setName(name);
        c.setUri(uri);
        d.setName(name);
        d.setUri(uri);
        assertEquals( true, d.equals(c));
    }

    @Test
    void testEquals2 () {
        Configmap c = new Configmap();
        Configmap d = new Configmap();
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        String uri2 = "proto://foo.bar.com/";
        String name2 = "hausboot2";
        c.setName(name);
        c.setUri(uri);
        d.setName(name2);
        d.setUri(uri2);
        assertEquals( false, d.equals(c));
    }

    @Test
    void testEqualsEmpty () {
        Configmap c = new Configmap();
        Configmap d = new Configmap();
        assertEquals( true, d.equals(c));
    }

    @Test
    void testEqualsEmptyAndNot () {
        Configmap c = new Configmap();
        Configmap d = new Configmap();
        String uri2 = "proto://foo.bar.com/";
        String name2 = "hausboot2";
        d.setName(name2);
        d.setUri(uri2);
        assertEquals( true, d.equals(c));
    }

    @Test
    void testEqualsNoUri () {
        Configmap c = new Configmap();
        Configmap d = new Configmap();
        String name = "hausboot";
        c.setUri(name);
        d.setUri(name);
        assertEquals( true, d.equals(c));
    }

    @Test
    void testEqualsUriNotPass () {
        Configmap c = new Configmap();
        Configmap d = new Configmap();
        String uri = "proto:foo.bar.com/";
        c.setUri(uri);
        d.setUri(uri);
        assertEquals( true, d.equals(c));
    }

}