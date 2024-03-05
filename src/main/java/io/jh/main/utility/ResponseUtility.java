package io.jh.main.utility;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtility {

    @JsonSerialize
    public static class EmptyJsonResponse {}

    public static <T> ResponseEntity<ResponseData<T>> createGetSuccessResponse(T data) {
        return createSuccessResponse(data, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseData<Void>> createPostSyncSuccessResponse() {
        return createSuccessResponse(null, HttpStatus.OK);
    }

    private static <T> ResponseData<T> createSuccessResponseData(
            String statusCode, T data) {
        if (data == null) data = (T) new EmptyJsonResponse();

        return ResponseData.<T>builder()
                .statusCode(statusCode)
                .data(data)
                .build();
    }

    public static <T> ResponseEntity<ResponseData<T>> createSuccessResponse(
            String statusCode, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                createSuccessResponseData(statusCode, data), httpStatus);
    }

    public static <T> ResponseEntity<ResponsePagingData<T>> createGetSuccessPagingResponse(
            Page<T> data) {
        return createSuccessPagingResponse(data);
    }

    public static <T> ResponseEntity<ResponseData<T>> createSuccessResponse(
            T data, HttpStatus httpStatus) {
        return createSuccessResponse(
                "SUCCESS",
                data,
                httpStatus);
    }
//
//    public static <T> ResponseEntity<ResponsePagingData<T>> createSuccessPagingResponse(
//            Page<T> page) {
//        return new ResponseEntity<>(
//                ResponsePagingData.of(
//                        "SUCCESS", "SUCCESS", page),
//                HttpStatus.OK);
//    }

    public static <T> ResponseEntity<ResponsePagingData<T>> createSuccessPagingResponse(
            Page<T> page) {
        return new ResponseEntity<>(
                ResponsePagingData.of(
                        "SUCCESS", page),
                HttpStatus.OK);
    }
}
