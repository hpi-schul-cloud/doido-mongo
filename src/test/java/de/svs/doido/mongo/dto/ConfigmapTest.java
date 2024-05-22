package svs.doido.mongo.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@QuarkusTest
class ConfigmapTest {
    
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
        c.setName(name);
        c.setUri(uri);
        d.setName(name);
        assertNotEquals(d.hashCode(), c.hashCode());
    }

    @Test
    void testHashcodeB() {
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
        assertEquals( true, d.equals(c));
    }

    @Test
    void testEqualsEmptyAndNot () {
        String uri2 = "proto://foo.bar.com/";
        String name2 = "hausboot2";
        d.setName(name2);
        d.setUri(uri2);
        assertEquals( false, d.equals(c));
    }

    @Test
    void testEqualsEmptyAndNotUri () {
        String uri2 = "proto://foo.bar.com/";
        String name2 = "hausboot2";
        c.setName(name2);
        d.setName(name2);
        d.setUri(uri2);
        assertEquals( false, d.equals(c));
    }

    @Test
    void testEqualsEmptyAndNotName () {
        String uri2 = "proto://foo.bar.com/";
        String name2 = "hausboot2";
        d.setName(name2);
        c.setUri(uri2);
        d.setUri(uri2);
        assertEquals( false, d.equals(c));
    }

    @Test
    void testEqualsNoUri () {
        String name = "hausboot";
        c.setUri(name);
        d.setUri(name);
        assertEquals( true, d.equals(c));
    }

    @Test
    void testEqualsUriNotPass () {
        String uri = "proto:foo.bar.com/";
        c.setUri(uri);
        d.setUri(uri);
        assertEquals( true, d.equals(c));
    }

}