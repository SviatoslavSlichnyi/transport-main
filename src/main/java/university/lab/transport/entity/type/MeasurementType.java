package university.lab.transport.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import university.lab.transport.exception.BusinessOperationRuntimeError;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum MeasurementType {
    KM("KM", "kilometer"),
    M("M", "meters");

    private final String shortCode;
    private final String fullName;

    public static MeasurementType findByShortCode(String shortCode) {
        return Arrays.stream(MeasurementType.values())
                .filter(measurementType -> measurementType.getShortCode().equals(shortCode))
                .findFirst()
                .orElseThrow(() ->
                        new BusinessOperationRuntimeError("MeasurementType was not found by shortcode: " + shortCode));
    }
}
