package org.example.endpoints.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ApiResponse<T> {

    @JsonProperty("result")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T result;

    @JsonProperty("errors")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Set<String> errors;

    public static <T> ApiResponse<T> ofSuccess(T data) {
        return ApiResponse.<T>builder()
                .result(data)
                .build();
    }

    public static <T> ApiResponse<T> of(final Set<String> errors) {
        return ApiResponse.<T>builder()
                .errors(errors)
                .build();
    }
}