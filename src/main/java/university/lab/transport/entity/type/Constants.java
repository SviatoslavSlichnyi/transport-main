package university.lab.transport.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Constants {
    public static class ArrivalGateway {
        public static final String ARRIVAL_DOMAIN = "http://localhost:8181/rest-api/arrival";

        @RequiredArgsConstructor
        @Getter
        public enum ArrivalTime {
            CALCULATE("/arrival-time/calculate");

            private final String endPoint;
        }
    }
}
