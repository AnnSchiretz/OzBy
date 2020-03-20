package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class UploadNewPhotoInAccountTest extends BaseTest {
    @Test
    public void uploadImage() {
        String pathImg = "src/test/resources/avatar.jpg";
        userSteps.goToPersonalInfo();
        infoSteps.uploadImg(pathImg);
    }

}
