package unittest.pet.store.utils;

import org.junit.Test;

import static pet.store.utils.DataProvider.getReplaceDataFromEnv;
import static pet.store.utils.DataProvider.getReplacedFakeData;
import static pet.store.utils.FileAndPath.RESOURCES_DIRECTORY_PATH;
import static pet.store.utils.FileAndPath.getRequestBodyFromFile;

public class DataProviderTest {
    @Test
    public void getReplacedFakeDataTest() {
        String originData = getRequestBodyFromFile(RESOURCES_DIRECTORY_PATH, "add_a_pet.json");
        System.out.println(getReplacedFakeData(originData));
    }

    @Test
    public void getReplaceDataFromEnvTest() {
        String originData = getRequestBodyFromFile(RESOURCES_DIRECTORY_PATH, "add_a_pet.json");
        System.out.println(getReplacedFakeData(getReplaceDataFromEnv("/pet/{{$cat.name}}")));
    }
}
