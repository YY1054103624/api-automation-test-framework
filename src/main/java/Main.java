import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.Medical;
import net.datafaker.providers.base.Number;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        Faker faker = new Faker();
        System.out.println(faker.cat().name());
//        System.out.println(StringUtils.strip("{{$name}}", "{{$}}"));
        /**
         * Regex
         */
//        String contents = Files.readString(Path.of("src/test/resources/add_a_pet.json"));
//        Pattern pattern = Pattern.compile("(\\{\\{)(\\w+?\\.?\\w+?)(\\}\\})", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(contents);
//        System.out.println(matcher.matches());
//        System.out.println(matcher.groupCount());
//        System.out.println(matcher.group(1));
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }
        /**
         * ** invoke method to generate faker data by reflect
         */
//        Faker faker = new Faker();
//        Class fakerClass = faker.getClass();
//        Class baseProviderClass = BaseProviders.class;
//        Method numMethod = baseProviderClass.getDeclaredMethod("number");
//        Object num = numMethod.invoke(faker);
//        Class numClass = num.getClass();
//        Method randomNumber = numClass.getDeclaredMethod("randomNumber");
//        System.out.println(randomNumber.invoke(num));

//        Class numClass = Class.forName("net.datafaker.providers.base.Number");
//        Constructor numCon = numClass.getDeclaredConstructor(BaseProviders.class);
//        Number numObj = (Number) numCon.newInstance(faker);
//        System.out.println(numObj.positive());

//        User u = new User();
//        Class uClass = u.getClass();
//        Method method = uClass.getDeclaredMethod("say");
//        method.invoke(u);

//        try {
//            String contents = Files.readString(Path.of("src/test/resources/add_a_pet.json"));
//            given().contentType(ContentType.JSON).body(contents.replace("{{id}}", "1")).when().post("https://petstore.swagger.io/v2" + "/pet").then().log().body().assertThat().statusCode(201);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Map<String, String> map = Map.of("count", "20");
//        JSONObject jsonObject = getJSONObject("Gpath.json");
//        String j = jsonObject.toString();
//        for (Map.Entry<String, String> entry: map.entrySet()) {
//            if (NumberUtils.isCreatable(entry.getValue())) {
//
//                System.out.println(j.replace("{{" + entry.getKey() + "}}", entry.getValue()));
//            }
//        }
//            JsonPath jsonPath = new JsonPath(fr);
//            System.out.println(jsonPath.get("count").toString());
//            System.out.println(jsonPath.get("teams.id[1]").toString());
//            System.out.println(jsonPath.getString("teams.name[-1]"));
//            System.out.println(jsonPath.getList("teams.name").toString());
//
//            Map<String, ?>map = jsonPath.getMap("teams[0]");
//            System.out.println(map.toString());
//            JSONObject jsonObject = (JSONObject) new JSONParser().parse(fr);
//            System.out.println(jsonObject.get("teams[1].name"));
    }
}
