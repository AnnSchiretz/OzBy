package tests;

import org.testng.annotations.Test;

public class UploadNewPhotoInAccount extends BaseTest {
    @Test
    public void uploadImage() {
        String pathImg = "src/test/resources/avatar.jpg";
        userSteps.goToPersonalInfo();
        infoSteps.uploadImg(pathImg);
    }

}
