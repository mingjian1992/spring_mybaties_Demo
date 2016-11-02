package com.andaily.zhishifenzi.web.controller.file;

import com.andaily.zhishifenzi.domain.dto.file.CKEditorFileDto;
import com.andaily.zhishifenzi.infrastructure.STRender;
import com.andaily.zhishifenzi.service.FileService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/public/ckeditor")
public class CKEditorController {

    private static Logger logger = LoggerFactory.getLogger(CKEditorController.class);

    @Autowired
    private FileService fileService;


    /**
     * Upload CKeditor images
     *
     * @param request  HttpServletRequest
     * @param response response
     * @param fileDto  CKEditorFileDto
     * @throws Exception Exception
     */
    @RequestMapping(value = "upload.wdcy", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response, CKEditorFileDto fileDto) throws Exception {
        logger.debug("Upload CKEdirot file: " + fileDto);
        String guid = fileService.uploadCKEditorFile(fileDto);
        response(guid, request, response);

    }

    private void response(String guid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        if (StringUtils.isEmpty(guid)) {
            logger.info("The upload file is null or it is not an image file,please check");
            return;
        }
        //Show image uri: see @FileController
        String fileUri = request.getContextPath() + "/public/image/" + guid + ".wdcy";
        logger.debug("Upload image visit uri: " + fileUri);
        String callback = request.getParameter("CKEditorFuncNum");

        Map<String, Object> model = new HashMap<>();
        model.put("funcNum", callback);
        model.put("uri", fileUri);
        model.put("guid", guid);

        String content = responseContent(request, model);
        logger.debug("Upload image response content: " + content);
        PrintWriter out = response.getWriter();
        //<script type="text/javascript">window.parent.CKEDITOR.tools.callFunction(1, '/userfiles/images/sea.jpg', '');</script>
        out.print(content);
        out.flush();
    }

    private String responseContent(HttpServletRequest request, Map<String, Object> model) throws IOException {
        STRender render = new STRender("template/ckeditor_response.st", model);
        return render.render();
    }

}