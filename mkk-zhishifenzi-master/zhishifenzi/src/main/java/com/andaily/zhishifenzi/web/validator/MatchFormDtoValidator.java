package com.andaily.zhishifenzi.web.validator;

import com.andaily.zhishifenzi.domain.dto.match.GoalDto;
import com.andaily.zhishifenzi.domain.dto.match.MatchFormDto;
import com.andaily.zhishifenzi.domain.dto.player.PlayerDto;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.file.ImageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 14-4-6 下午14:06
 *
 * @author Shengzhao Li
 */
@Component
public class MatchFormDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return MatchFormDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "stadium.guid", null, "请选择比赛球场");
        ValidationUtils.rejectIfEmpty(errors, "opponent.guid", null, "请选择比赛对手");
        ValidationUtils.rejectIfEmpty(errors, "stadium.guid", null, "请选择比赛球场");
        ValidationUtils.rejectIfEmpty(errors, "season.guid", null, "请选择赛季");

        MatchFormDto formDto = (MatchFormDto) o;
        validateTime(formDto, errors);
        validateGoals(formDto, errors);
        validatePlayers(formDto, errors);

        if (formDto.isNewly()) {
            validatePhotos(formDto, errors);
        }
    }

    private void validatePhotos(MatchFormDto formDto, Errors errors) {
        final List<MultipartFile> photos = formDto.getPhotos();
        if (photos.isEmpty()) {
            //ignore if empty
            return;
        }
        //max size: 3
        validateSinglePhoto(photos.get(0), errors);
        validateSinglePhoto(photos.get(1), errors);
        validateSinglePhoto(photos.get(2), errors);

    }

    private void validateSinglePhoto(MultipartFile file, Errors errors) {
        if (file == null || file.isEmpty()) {
            return;
        }
        if (!ImageUtils.isImageFilename(file.getOriginalFilename())) {
            errors.rejectValue("photos", null, "图片格式不正确,允许的格式为 " + ImageUtils.IMAGE_EXTENSIONS);
            return;
        }
        if (!ImageUtils.isAvailableUploadImageSize(file.getSize())) {
            errors.rejectValue("photos", null, "图片大小超出1MB, 请重新上传");
        }
    }

    private void validatePlayers(MatchFormDto formDto, Errors errors) {
        List<String> joinPlayerGuids = formDto.getJoinPlayerGuids();
        if (joinPlayerGuids.isEmpty()) {
            errors.rejectValue("joinPlayerGuids", null, "请勾选参赛球员");
        }
    }

    private void validateGoals(MatchFormDto formDto, Errors errors) {
        int homeScores = formDto.getHomeScores();
        List<GoalDto> goals = formDto.getGoals();
        int size = goals.size();
        if (size != homeScores) {
            errors.rejectValue("homeScores", null, "比分与进球信息不一致,进球数为[" + homeScores + "], 但进球信息为[" + size + "]条.");
            return;
        }
        for (GoalDto goal : goals) {
            if (!goal.isOwnGoal()) {
                PlayerDto goalPlayer = goal.getGoalPlayer();
                if (goalPlayer == null || StringUtils.isEmpty(goalPlayer.getGuid())) {
                    errors.rejectValue("goals", null, "请选择进球球员");
                }
            }
        }
    }

    private void validateTime(MatchFormDto formDto, Errors errors) {
        String time = formDto.getMatchTime();
        if (StringUtils.isEmpty(time)) {
            errors.rejectValue("matchTime", null, "请选择比赛时间");
        }
        if (!DateUtils.isDateTime(time)) {
            errors.rejectValue("matchTime", null, "比赛时间格式错误,正确格式为: " + DateUtils.DATE_TIME_FORMAT);
        }
    }
}
