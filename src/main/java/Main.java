import net.datafaker.Faker;
import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.Medical;
import net.datafaker.providers.base.Number;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // invoke method by method's name (reflect)
        Faker faker = new Faker();
        faker.number().randomNumber();
        Number num = faker.number();
        Class numClass = num.getClass();
        Method randomNumber = numClass.getDeclaredMethod("randomNumber");
        System.out.println(randomNumber.invoke(num));

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
