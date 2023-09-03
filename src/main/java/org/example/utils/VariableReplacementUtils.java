package org.example.utils;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import org.apache.commons.lang3.StringUtils;
import org.example.environment.EnvironmentVariablesUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.example.constants.FrameworkConstants.*;

public final class VariableReplacementUtils {
    private VariableReplacementUtils() {

    }
    public static String getReplacedDataWithFakeData(String originData) {
        Pattern pattern = Pattern.compile(getRegexOfFakeDataReplacement());
        Matcher matcher = pattern.matcher(originData);

        Faker faker = new Faker();
        Class baseProviderClass = BaseProviders.class;

        String result = originData;
        while (matcher.find()) {
            String strMatcher = matcher.group();
            String[] methodNames = StringUtils.strip(strMatcher, "{{$}}").split("\\.");
            try {
                // eg. replace {{$number.positive}} with faker.number().positive()
                Method method0 = baseProviderClass.getMethod(methodNames[0]);
                Object obj = method0.invoke(faker);
                Method method1 = obj.getClass().getDeclaredMethod(methodNames[1]);
                String fakeData = String.valueOf(method1.invoke(obj));

                result = result.replace(strMatcher, fakeData);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static String getReplacedDataWithEnvVariables(String originData) {
        Pattern pattern = Pattern.compile(getRegexOfEnvDataReplacement());
        Matcher matcher = pattern.matcher(originData);

        String result = originData;
        while (matcher.find()) {
            String strMatcher = matcher.group();
            String key = StringUtils.strip(strMatcher, "{{}}");
            result = result.replace(strMatcher, EnvironmentVariablesUtils.getValue(key));
        }

        return result;
    }

    public static String getReplacedData(String requestBodyBeforeReplacement) {
        return getReplacedDataWithFakeData(getReplacedDataWithEnvVariables(requestBodyBeforeReplacement));
    }
}
