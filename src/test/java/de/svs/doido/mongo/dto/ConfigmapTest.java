package svs.doido.mongo.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@QuarkusTest
class ConfigmapTest {
    @ToString
    @EqualsAndHashCode(callSuper=false)
    public static class testConfigmapClass extends Configmap {

    }
    
    private Configmap c;
    private Configmap d;
    private Configmap sub;

    @BeforeEach
    public void before() {
        c = new Configmap();
        d = new Configmap();
        sub = new testConfigmapClass();
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
        assertEquals(c.hashCode(), c.hashCode());
        assertNotEquals((new Object()).hashCode(), d.hashCode());
        c.setName(name);
        assertNotEquals(d.hashCode(), c.hashCode());
        c.setUri(uri);
        assertNotEquals(d.hashCode(), c.hashCode());
        d.setName(name);
        assertNotEquals(d.hashCode(), c.hashCode());
        d.setUri(uri);
        assertEquals(d.hashCode(), c.hashCode());
        d.setName(name);
        d.setUri("mongo://uri.foo.bar.com/?uir=a");
        assertNotEquals(d.hashCode(), c.hashCode());
        assertEquals(c.hashCode(), c.hashCode());
    }

    @Test
    void testHashcodeSubClass() {
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        assertEquals(c.hashCode(), c.hashCode());
        assertNotEquals(sub.hashCode(), c.hashCode());c
        c.setName(name);
        assertNotEquals(sub.hashCode(), c.hashCode());
        c.setUri(uri);
        assertNotEquals(sub.hashCode(), c.hashCode());
        d.setName(name);
        assertNotEquals(sub.hashCode(), c.hashCode());
        d.setUri(uri);
        assertEquals(sub.hashCode(), c.hashCode());
        d.setName(name);
        d.setUri("mongo://uri.foo.bar.com/?uir=a");
        assertNotEquals(sub.hashCode(), c.hashCode());
    }

    @Test
    void testEqualsEmpty () {
        assertEquals( true, c.equals(c));
        assertEquals( true, d.equals(c));
        assertEquals( true, c.equals(sub));
        assertEquals( true, sub.equals(c));
    }

    @Test
    void testEqualsObject () {
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        d.setName(name);
        d.setUri(uri);
        assertEquals( false, c.equals(new Object()));
        assertEquals( false, d.equals(new Object()));
    }

    @Test
    void testEqualsName () {
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        c.setName(name);
        assertEquals( false, d.equals(c));
        assertEquals( false, c.equals(d));
        assertEquals( false, sub.equals(c));
        assertEquals( false, c.equals(sub));
        d.setName(name);
        sub.setName(name);
        assertEquals( true, d.equals(c));
        assertEquals( true, c.equals(d));
        assertEquals( false, sub.equals(c));
        assertEquals( false, c.equals(sub));
    }

    @Test
    void testEqualsUri () {
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        c.setUri(uri);
        assertEquals( false, d.equals(c));
        assertEquals( false, c.equals(d));
        d.setUri(uri);
        assertEquals( true, d.equals(c));
        assertEquals( true, c.equals(d));
    }

    @Test
    void testEqualsUriHttps () {
        String uri = "https://foo.bar.com/";
        String name = "hausboot";
        c.setUri(uri);
        assertEquals( false, d.equals(c));
        assertEquals( false, c.equals(d));
        d.setUri(uri);
        assertEquals( true, d.equals(c));
        assertEquals( true, c.equals(d));
    }

    @Test
    void testEqualsNameUri () {
        String uri = "proto://foo.bar.com/";
        String name = "hausboot";
        c.setUri(uri);
        c.setName(name);
        assertEquals( false, d.equals(c));
        assertEquals( false, c.equals(d));
        d.setUri(uri);
        assertEquals( false, d.equals(c));
        assertEquals( false, c.equals(d));
        d.setName(name);
        assertEquals( true, d.equals(c));
        assertEquals( true, c.equals(d));
    }
}