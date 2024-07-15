package svs.doido.mongo.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class MongoServerTest {

    @Test
    void testGetterSetterName() {
        MongoServer a = new MongoServer("Server 1","mongodb://mongodb-2.mongo-svc:27017/replicaSet=rs0&directConnection=false");        
        MongoServer b = new MongoServer("Server 1","mongodb://mongodb-2.mongo-svc:27017/replicaSet=rs0&directConnection=false");
        assertEquals(1,a.equasl(b));
    }
}