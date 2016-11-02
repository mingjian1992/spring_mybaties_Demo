package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.dto.match.SeasonDto;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * 14-3-16 上午2:17
 *
 * @author Shengzhao Li
 */
@Component
public class SeasonDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return SeasonDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formDto.name", null, "赛季名称不能为空");

        SeasonDto seasonDto = (SeasonDto) o;
        String start = seasonDto.getStart();
        Date startDate = null;
        if (StringUtils.isEmpty(start) || !DateUtils.isDate(start)) {
            errors.rejectValue("formDto.start", null, "开始日期格式错误");
        } else {
            startDate = DateUtils.getDate(start);
        }

        String end = seasonDto.getEnd();
        Date endDate = null;
        if (StringUtils.isEmpty(end) || !DateUtils.isDate(end)) {
            errors.rejectValue("formDto.end", null, "结束日期格式错误");
        } else {
            endDate = DateUtils.getDate(end);
        }

        if (startDate != null && endDate != null && startDate.after(endDate)) {
            errors.rejectValue("formDto.end", null, "结束日期必须在开始日期之后");
        }

    }
}
