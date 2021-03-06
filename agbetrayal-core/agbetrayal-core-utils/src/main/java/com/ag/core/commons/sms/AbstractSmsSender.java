package com.ag.core.commons.sms;

import com.ag.core.commons.JsonResult;
import com.ag.core.commons.util.*;
import lombok.Setter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author zhengaiguo
 * @date 2019-8-10 14:37
 */
public abstract class AbstractSmsSender implements SmsSender<SmsSenderResult> {

    private static Lazy<TemplateMessageService> templateMessageService = Lazy.of(() -> SpringContextHolder.getBean(TemplateMessageService.class));

//    /**
//     * 相同手机号每分钟限制
//     */
//    @Setter
//    private int samePhoneMinuteLimit = 1;
//
//    /**
//     * 相同客户端每分钟限制
//     */
//    @Setter
//    private int sameClientMinuteLimit = 1;

    @Setter
    private SmsSenderFilter smsSenderFilter;

    @Override
    public final JsonResult<SmsSenderResult> sendSms(Set<String> phones, String message) throws IOException {
        validatePhone(phones);
        AssertUtils.notEmpty(message, "发送消息不能为空");
        if (null != smsSenderFilter) {
            phones = smsSenderFilter.filter(phones.toArray(new String[0]));
        }
        return doSendSms(phones, message);
    }

    @Override
    public final JsonResult<SmsSenderResult> sendTemplateSms(Set<String> phones, String smsTemplateId, Map<String, ?> templateParameter) throws IOException {
        validatePhone(phones);
        if (null != smsSenderFilter) {
            phones = smsSenderFilter.filter(phones.toArray(new String[0]));
        }
        return doSendSms(phones, StringUtils.processTemplate(templateMessageService.get().getTemplateContent(smsTemplateId), templateParameter));
    }

    protected abstract JsonResult<SmsSenderResult> doSendSms(Set<String> phones, String message) throws IOException;

    private void validatePhone(Collection<String> phones) {
        AssertUtils.notNull(phones, "验证手机号不能为空");
        for (String phone : phones) {
            AssertUtils.isTrue(ValidateUtils.isMobilePhone(phone), "手机号格式不正确:" + phone);
        }
    }
}
