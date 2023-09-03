package org.example.utils;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import org.apache.commons.lang3.StringUtils;
import org.example.environment.EnvironmentVariablesUtils;
import org.example.exceptions.FrameworkException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.example.constants.FrameworkConstants.*;

/**
 * This class is for replacing variables in request body.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public final class VariableReplacementUtils {
    private VariableReplacementUtils() {

    }

    /**
     * Replace variables with fake data.
     * @param originData request body.
     * @return replaced request body
     */
    public static String getReplacedDataWithFakeData(String originData) {
        Pattern pattern = Pattern.compile(getRegexOfFakeDataReplacement());
        Matcher matcher = pattern.matcher(originData);

        Faker faker = new Faker();
        Class<BaseProviders> baseProviderClass = BaseProviders.class;

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
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new FrameworkException("There's an issue with reflection", e);
            }
        }
        return result;
    }

    /**
     * Replace variables with values from the environment variable.
     * @param originData request body.
     * @return replaced request body
     */
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

    /**
     * Get replaced request body.
     * @param requestBodyBeforeReplacement request body that hasn't been replaced.
     * @return replaced request body.
     */
    public static String getReplacedData(String requestBodyBeforeReplacement) {
        return getReplacedDataWithFakeData(getReplacedDataWithEnvVariables(requestBodyBeforeReplacement));
    }
}
