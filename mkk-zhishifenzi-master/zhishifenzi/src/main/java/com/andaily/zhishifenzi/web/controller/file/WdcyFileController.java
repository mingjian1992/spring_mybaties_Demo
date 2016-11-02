package com.andaily.zhishifenzi.web.controller.file;

import com.andaily.zhishifenzi.domain.dto.album.PhotoDto;
import com.andaily.zhishifenzi.domain.dto.file.GeckoFileDto;
import com.andaily.zhishifenzi.infrastructure.DimensionalCodeHelper;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;
import com.andaily.zhishifenzi.infrastructure.file.ImageSizeAdjuster;
import com.andaily.zhishifenzi.service.GeckoFileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/public/")
public class WdcyFileController {


    private static Logger logger = LoggerFactory.getLogger(WdcyFileController.class);

    @Autowired
    private GeckoFileService fileService;


    @RequestMapping("image/{guid}.zsfz")
    public void image(@PathVariable("guid") String guid, String w, HttpServletResponse response) throws Exception {
        logger.debug("Load image by guid " + guid);
        GeckoFileDto fileDto = fileService.loadFileByGuid(guid);

        final String extension = fileDto.getContextTypeExtension();
        response.setContentType("image/" + extension);
        byte[] data = fileDto.getData();
        //check adjust image or not
        writeData(w, response, MatchUtils.isPositiveNumber(w), extension, data);
    }

    /*
     * Just for photo
     *
     */
    @RequestMapping("photo/{guid}.zsfz")
    public void photo(@PathVariable("guid") String guid, String w, HttpServletResponse response) throws Exception {
        final boolean isPositiveNumber = MatchUtils.isPositiveNumber(w);
        PhotoDto photoDto = fileService.loadPhotoDtoIncludeDataByGuid(guid);
        final String url = photoDto.getUrl();

        if (StringUtils.isEmpty(url)) {
            final String extension = photoDto.getContextTypeExtension();
            response.setContentType("image/" + extension);
            byte[] data = photoDto.getData();
            writeData(w, response, isPositiveNumber, extension, data);

        } else {
            //redirect to   url
            if (isPositiveNumber) {
                // ?imageView/1/w/200/h/140/q/85
                //See http://developer.qiniu.com/docs/v6/kb/drawing-tools.html
                response.sendRedirect(url + "?imageView/1/w/" + w + "/h/140/q/85");
            } else {
                response.sendRedirect(url);
            }
        }


    }

    private void writeData(String w, HttpServletResponse response, boolean positiveNumber, String extension, byte[] data) throws IOException {
        //check adjust image or not
        if (positiveNumber) {
            ImageSizeAdjuster sizeAdjuster = new ImageSizeAdjuster(data);
            data = sizeAdjuster.adjust(Integer.valueOf(w), extension);
        }
        ServletOutputStream os = response.getOutputStream();
        os.write(data);
        os.flush();
    }

    @RequestMapping("player_image/{guid}.zsfz")
    public void playerImage(@PathVariable("guid") String guid, String w, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PhotoDto fileDto = fileService.loadPlayerPhotoByGuid(guid);

        byte[] data;
        if (fileDto != null) {
            final String extension = fileDto.getContextTypeExtension();
            response.setContentType("image/" + extension);
            data = fileDto.getData();
            //check adjust image or not
            if (MatchUtils.isPositiveNumber(w)) {
                ImageSizeAdjuster sizeAdjuster = new ImageSizeAdjuster(data);
                data = sizeAdjuster.adjust(Integer.valueOf(w), extension);
            }
        } else {
            //response default image
            Resource resource = new ServletContextResource(request.getSession().getServletContext(), "resources/images/default_user.png");
            response.setContentType("image/png");
            data = FileUtils.readFileToByteArray(resource.getFile());
        }

        ServletOutputStream os = response.getOutputStream();
        os.write(data);
        os.flush();
    }

    //生成二维码
    @RequestMapping("generate_code.zsfz")
    public void generateCode(String key, HttpServletResponse response) throws Exception {
        final byte[] data = (StringUtils.isEmpty(key) ? new byte[]{} : DimensionalCodeHelper.encode(key));

        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        os.write(data);
        os.flush();

    }

    @RequestMapping("download/{guid}.zsfz")
    public void fileUpload(@PathVariable("guid") String guid, HttpServletResponse response) throws Exception {
        logger.debug("Load file by guid" + guid);
        GeckoFileDto fileDto = fileService.loadFileByGuid(guid);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileDto.downloadFileName());

        ServletOutputStream out = response.getOutputStream();
        out.write(fileDto.getData());
        out.flush();
    }


}