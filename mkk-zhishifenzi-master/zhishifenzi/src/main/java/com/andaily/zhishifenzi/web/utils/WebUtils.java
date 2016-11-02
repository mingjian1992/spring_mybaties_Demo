package com.andaily.zhishifenzi.web.utils;

import com.andaily.zhishifenzi.domain.dto.AbstractDto;
import net.sf.json.JSON;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Shengzhao Li
 */
public abstract class WebUtils {


    public static void writeJson(HttpServletResponse response, JSON json) {

        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            json.write(writer);
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Write json to response error", e);
        }

    }

    public static void writeJson(HttpServletResponse response, String json) {
        response.setContentType("application/json;charset=UTF-8");

        try {
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Write json to response error", e);
        }

    }

    public static void writeXml(HttpServletResponse response, AbstractDto dto) throws JAXBException, IOException {
        response.setContentType("text/xml;charset=UTF-8");

        JAXBContext context = JAXBContext.newInstance(dto.getClass());
        final Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        PrintWriter writer = response.getWriter();
        marshaller.marshal(dto, writer);
        writer.flush();

    }


    /**
     * Retrieve client ip address
     *
     * @param request HttpServletRequest
     * @return IP
     */
    public static String retrieveClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (isUnAvailableIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isUnAvailableIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isUnAvailableIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean isUnAvailableIp(String ip) {
        return (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip));
    }

    /**
     * Check user client browser is Mobile browser or not
     *
     * @param request HttpServletRequest
     * @return True is mobile browser
     */
    public static boolean isMobileBrowser(HttpServletRequest request) {
        final String userAgent = request.getHeader("user-agent");
        if (StringUtils.isNotEmpty(userAgent)) {
            final String lowerAgent = userAgent.toLowerCase();
            return (lowerAgent.contains("mobile")
                    || lowerAgent.contains("android"));
        }
        return false;
    }
}