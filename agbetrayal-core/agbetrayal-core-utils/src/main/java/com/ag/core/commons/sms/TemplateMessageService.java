package com.ag.core.commons.sms;

/**
 * @author zhengaiguo
 * @date 2019-8-10 14:38
 */
public interface TemplateMessageService {

    /**
     * 根据 templateId 获取 内容
     *
     * @param templateId templateId
     * @return 模板内容
     */
    String getTemplateContent(String templateId);

}
