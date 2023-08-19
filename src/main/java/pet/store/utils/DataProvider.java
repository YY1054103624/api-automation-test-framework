package pet.store.utils;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pet.store.utils.ResourcesConnections.env;

public class DataProvider {
    public static final String REGEX_FOR_FAKE_DATA = "\\{\\{\\$\\w+?\\.\\w+?}}";
    public static final String REGEX_FOR_MANUAL_DATA = "\\{\\{\\w+?}}";
    public static String getReplacedFakeData(String originData) {
        Pattern pattern = Pattern.compile(REGEX_FOR_FAKE_DATA);
        Matcher matcher = pattern.matcher(originData);

        Faker faker = new Faker();
        Class baseProviderClass = BaseProviders.class;

        String requestBody = originData;
        while (matcher.find()) {
            String strMatcher = matcher.group();
            String[] methodNames = StringUtils.strip(strMatcher, "{{$}}").split("\\.");
            try {
                // eg. replace {{$number.positive}} with faker.number().positive()
                Method method0 = baseProviderClass.getMethod(methodNames[0]);
                Object obj = method0.invoke(faker);
                Method method1 = obj.getClass().getDeclaredMethod(methodNames[1]);
                String fakeData = String.valueOf(method1.invoke(obj));

                requestBody = requestBody.replace(strMatcher, fakeData);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return requestBody;
    }

    public static String getReplaceDataFromEnv(String originData) {
        Pattern pattern = Pattern.compile(REGEX_FOR_MANUAL_DATA);
        Matcher matcher = pattern.matcher(originData);

        String requestBody = originData;
        while (matcher.find()) {
            String strMatcher = matcher.group();
            String key = StringUtils.strip(strMatcher, "{{}}");
            requestBody = requestBody.replace(strMatcher, env.getProperty(key));
        }

        return requestBody;
    }
}
