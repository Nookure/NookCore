package es.angelillo15.core;

import net.byteflux.libby.Library;

public class CoreLoader {
    public static Library getCore() {
        return Library.builder()
                .groupId("es{}angelillo15{}core")
                .artifactId("nookcore-core")
                .version("{version}")
                .build();
    }
}
