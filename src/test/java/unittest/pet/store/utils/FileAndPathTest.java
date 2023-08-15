package unittest.pet.store.utils;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pet.store.utils.FileAndPath.getJsonFileObject;

public class FileAndPathTest {
    @Test
    public void getJsonFileObjectTest() {
        File file = getJsonFileObject("add_a_pet.json");
        assertTrue(file.exists());
    }
}
