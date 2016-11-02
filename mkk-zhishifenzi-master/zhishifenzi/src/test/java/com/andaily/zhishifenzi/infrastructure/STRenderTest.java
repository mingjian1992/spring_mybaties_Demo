package com.andaily.zhishifenzi.infrastructure;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

/**
 * @author Shengzhao Li
 */
public class STRenderTest {

    @Test
    public void testRender() throws Exception {
        Map<String, Object> modal = new HashMap<>();
        modal.put("title", "达州.江湾城");
        modal.put("resourceAddress", "http://112.124.30.176/u3d/");
        modal.put("resourcePath", "web.unity3d");

        STRender stRender = new STRender("template/vr.html");
        final String render = stRender.render(modal);

        assertNotNull(render);
        System.out.println(render);
    }
}