package svs.doido.mongo.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.Assert.assertEquals;

import svs.doido.mongo.dto.Configmap;

@QuarkusTest
class ConfigmapTest {
    @Test
    void testGetterSetter() {
        Configmap c = new Configmap();
        String name = "hausboot";
        c.setName(name);
        assertEquals(name,c.getName());
    }

}