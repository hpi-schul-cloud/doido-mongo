package svs.doido.mongo.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


@ToString
@EqualsAndHashCode
public class MongoServer {
    @Getter
    private String name;

    @Getter
    private MongoClient mongoClient;

    @Getter
    private ConnectionString connectionString;

    public void MongoServer(String name, String connectionString) {
        if (name == null ) {
            throw new NullPointerException();
        }
        if (connectionString == null) {
            throw new NullPointerException();
        }
        else {
            this.connectionString = new ConnectionString(connectionString);
        }
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
         MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(this.connectionString)
                .applicationName(name)
                .serverApi(serverApi)
                .build();
        mongoClient = MongoClients.create(settings);
    }

}