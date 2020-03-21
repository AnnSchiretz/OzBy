package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class UploadNewPhotoInAccountTest extends BaseTest {
    @Test(description = "Upload new user photo in personal account")
    public void uploadImage() {
        String pathImg = "src/test/resources/avatar.jpg";
        userSteps.goToPersonalInfo();
        infoSteps.uploadImg(pathImg);
    }

}
