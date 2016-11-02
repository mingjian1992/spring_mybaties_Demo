package com.andaily.zhishifenzi.infrastructure.mail;

import com.andaily.zhishifenzi.ContextTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


/**
 * 14-12-1
 *
 * @author Shengzhao Li
 */
public class MailTransmitterTest extends ContextTest {


    @Test(enabled = false)
    public void testTransmit() throws Exception {
        MailTransmitter transmitter = new MailTransmitter("Testing mail from ZSFZ",
                "Only Testing configuration is correct!!!"
                , "shengzhao@andaily.com");
        MailTransmitResult result = transmitter.transmit();
        assertTrue(result.success());
    }
}
