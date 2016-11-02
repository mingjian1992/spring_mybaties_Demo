package com.andaily.zhishifenzi.infrastructure;

import org.testng.annotations.Test;

/**
 * @author Shengzhao Li
 */
public class PasswordHandlerTest {

    @Test
    public void testDecryptReversiblePassword() throws Exception {
        String text = "YWRtaW46MTM5MTc2MTA4MTY4MTphZG1pbjoxMzkxNzYxMDgxNjgxOnBjZH5HNTc2NF9fNDM2Yzg3ZmYwMjk3NGRlNDllZDk4NDBiYzMxMmNlMDA";
        final String out = PasswordHandler.encryptReversiblePassword(text);
        String aa = PasswordHandler.encryptPassword("123456");
        System.out.println(out);
        System.out.println(aa);
    }
}