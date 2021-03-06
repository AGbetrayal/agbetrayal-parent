package com.ag.core.authentication.api.validatecode;

import com.ag.core.web.Webs;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * 图片验证码生成器
 *
 * @author zhengaiguo
 * @date 2018-07-27 14:46
 */
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    public ImageCodeProcessor() {
        super(new ImageCodeGenerator());
    }

    @Override
    protected void send(ImageCode imageCode, ServletWebRequest request) throws IOException {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

    @Override
    protected String getSuffix(ServletWebRequest request) {
        return Webs.getRemoteAddr(request.getRequest());
    }
}
