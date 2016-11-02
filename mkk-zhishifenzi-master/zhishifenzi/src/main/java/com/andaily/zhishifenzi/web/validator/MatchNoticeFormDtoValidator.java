package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.dto.match.MatchNoticeFormDto;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.MatchUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 14-3-21 下午11:38
 *
 * @author Shengzhao Li
 */
@Component
public class MatchNoticeFormDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MatchNoticeFormDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "stadium.guid", null, "请选择比赛球场");
        ValidationUtils.rejectIfEmpty(errors, "opponent.guid", null, "请选择比赛对手");
        ValidationUtils.rejectIfEmpty(errors, "remark", null, "请输入备注信息");

        MatchNoticeFormDto formDto = (MatchNoticeFormDto) o;
        validateTime(formDto, errors);

    }

    private void validateTime(MatchNoticeFormDto formDto, Errors errors) {
        String time = formDto.getTime();
        if (StringUtils.isEmpty(time)) {
            errors.rejectValue("time", null, "请选择比赛时间");
        }
        if (!DateUtils.isDateTime(time)) {
            errors.rejectValue("time", null, "比赛时间格式错误,正确格式为: " + DateUtils.DATE_TIME_FORMAT);
        }
    }
}
