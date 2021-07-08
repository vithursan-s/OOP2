package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    @Test
    void setUniqueID() {
        try{
            UserModel userModel= new UserModel();
            userModel.setUniqueID(2);
            fail("wrong datatype in setUniqueID() should, trigger an exception");
        } catch (Exception e) {
            fail("Wrong data type provided");
        }
    }
}