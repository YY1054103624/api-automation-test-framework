package org.example.utils;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.environment.EnvironmentVariablesUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.constants.FrameworkConstants.getRequestBodyPath;
import static org.example.utils.FileContentGetter.getFileContentAsString;
import static org.example.utils.VariableReplacementUtils.*;


public final class SendRequestUtils {
    private SendRequestUtils() {

    }

    public static ValidatableResponse sendRequest(Map<String, String> requestSettings) {
        ValidatableResponse response = null;
        RequestSpecification requestSpecification = given();
        requestSpecification.baseUri(EnvironmentVariablesUtils.getValue("baseUrl"));

        String endpoint = requestSettings.get("endpoint");
        String method = requestSettings.get("method");
        String contentType = requestSettings.get("contendType");
        String requestBodyFileName = requestSettings.get("requestBodyFileName");

        // if endpoint is needed
        if (!"none".equalsIgnoreCase(endpoint)) {
            // Process endpoint
            endpoint = getReplacedData(endpoint);
        }

        // if contentType is needed
        if (!"none".equalsIgnoreCase(contentType)) {
            requestSpecification.contentType(contentType);
        }

        // if requestBodyFileName is needed
        if (!"none".equalsIgnoreCase(requestBodyFileName)) {
            // Process requestBodyFileName
            requestSpecification.body(getReplacedData(getFileContentAsString(getRequestBodyPath(requestBodyFileName))));
        }

        try {
            // invoke specific http method by reflection
            Class<RequestSpecificationImpl> c = RequestSpecificationImpl.class;
            Method httpMethod = c.getDeclaredMethod(method.toLowerCase(), String.class, Object[].class);
            // new Object[0]: invoke method with variadic parameters as empty
            Response r = (Response) httpMethod.invoke(requestSpecification, endpoint, new Object[0]);
            response = r.then();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
