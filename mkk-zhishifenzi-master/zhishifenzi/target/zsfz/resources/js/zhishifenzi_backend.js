/**
 * displaytag paginated use  it.
 * Don't change it
 *
 * @param formId
 * @param data
 */
function displaytagform(formId, data) {
    var params = '?';
    $.map(data, function (d) {
        params += (d.f + "=" + d.v + "&");
    });
    var $form = $("#" + formId);
    var action = $form.attr("action");
    var url = action + params;
    var $targetDiv = $form.parent().find(".displayTarget");
    if ($targetDiv.length > 0) {
        //if exist, load  the content to the div
        $targetDiv.load(url);
    } else {
        location.href = url;
    }
}

/**
 * common zsfz
 *
 * @constructor
 */
function ZhiShiFenZi() {
    this.sideNav();
    this.initialTooltips();
    this.showFormValidationError();
}

ZhiShiFenZi.prototype = {
    sideNav:function () {
        var obj = $('#mainMenu li').removeClass('active');
        var localUrl = location.pathname;
        if (localUrl.indexOf('match') != -1
            || localUrl.indexOf('season') != -1) {
            obj.filter(".matchLi").addClass('active');
        } else if (localUrl.indexOf('club') != -1) {
            obj.filter(".clubLi").addClass('active');
        } else if (localUrl.indexOf('album') != -1) {
            obj.filter(".albumLi").addClass('active');
        } else if (localUrl.indexOf('player') != -1) {
            obj.filter(".playerLi").addClass('active');
        } else if (localUrl.indexOf('stadium') != -1) {
            obj.filter(".stadiumLi").addClass('active');
        } else if (localUrl.indexOf('system') != -1) {
            obj.filter(".systemLi").addClass('active');
        } else {
            obj.eq(0).addClass('active');
        }
    }, initialAlert:function (eleId, fadeInTime, fadeOutTime) {
        if ('' != eleId) {
            fadeInTime = fadeInTime || 2000;     //default fade in: 2000ms
            fadeOutTime = fadeOutTime || 3000; //default fade out: 1000ms
            var $alert = $("#" + eleId);
            $alert.removeClass("hide").parent().parent().removeClass("hide").fadeIn(fadeInTime).slideUp(fadeOutTime);
        }
    }, initialTooltips:function (ele) {
        $(ele || "span[title],a[title],div[title]").tooltip({
            placement:'bottom',
            html:true,
            delay:100
        });
    }, showFormValidationError:function () {
        $("form>div>span[class='text-danger']").parent().addClass("has-error");
    }
};

$(function () {
    new ZhiShiFenZi();
});


/**
 * b_club.jsp
 * @param alert
 * @constructor
 */
function BackendClub(alert) {
    ZhiShiFenZi.prototype.initialAlert(alert);
}

/**
 * b_index.jsp
 *
 * @constructor
 */
function BackendIndex() {
    this.initialUpload();
}

BackendIndex.prototype = {
    initialUpload:function () {
        $('#fileupload').fileupload({
            url:"upload_head_image.zsfz",
            autoUpload:true,
            replaceFileInput:false,
            formData:{ d:Math.random()},
            dataType:'json',
            done:function (e, data) {
                var fileGuid = data.result.fileGuid;
                if ("-1" == fileGuid) {
                    alert("请上传照片,后缀为:png,jpg,jpeg或gif");
                    return;
                }
                var $cropImg = $("img#cropImg").attr("src", "../public/image/" + fileGuid + ".zsfz");
                $("input#cropImageGuid").val(fileGuid);
                $('#cropModal').modal('show').on("shown.bs.modal", function (e) {
                    $cropImg.Jcrop({
                        onChange:updatePreview,
                        onSelect:updatePreview,
                        onRelease:clearCoords,
                        minSize:[32, 40],
                        allowSelect:false,
                        aspectRatio:0.81
                    }, function () {
                        this.setSelect([0, 0, 64, 80]);
                    });

                    function clearCoords() {
                        $("#crop_data_hidden").find(":hidden").val("0");
                    }

                    function updatePreview(c) {
                        if (parseInt(c.w) > 0) {
                            $("#i_width").val(parseInt(c.w));
                            $("#i_height").val(parseInt(c.h));

                            $("#i_x1").val(parseInt(c.x));
                            $("#i_y1").val(parseInt(c.y));
                            $("#i_x2").val(parseInt(c.x2));
                            $("#i_y2").val(parseInt(c.y2));
                        }
                    }
                });
            }
        });
    }
};

/**
 * b_match.jsp
 * @param alert
 * @param today
 * @constructor
 */
function BackendMatch(alert, today) {
    ZhiShiFenZi.prototype.initialAlert(alert);
    this.matchDate(today);
}

BackendMatch.prototype = {
    matchDate:function (today) {
        $('#matchDate').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:"zh-CN",
            endDate:today,
            todayHighlight:true,
            weekStart:1
        });
    }
};

/**
 * b_match.jsp
 * @constructor
 */
function MatchForm() {
    this.matchTime();
    this.changeNotice();
    this.removeGoal();
    this.ownGoal();
    this.addGoalLine();
    this.checkAllPlayers();
}

MatchForm.prototype = {
    checkAllPlayers:function () {
        $("a.checkAllPlayers").click(function () {
            $(this).parent().parent().find("input:checkbox").attr("checked", true);
        });
    },
    addGoalLine:function () {
        $("#addGoalLine").click(function () {
            var $lastTr = $("#goalTable tbody tr:last");
            var index = parseInt($lastTr.attr("index")) + 1;

            var $newTr = $lastTr.clone();
            $newTr.attr("index", index).find("td:first").html(index + 1);
            $newTr.find("td:eq(1)").find("select")
                .attr("name", "goals[" + index + "].goalPlayer.guid").attr("id", "goals" + index + ".goalPlayer.guid");
            $newTr.find("td:eq(2)").find("select")
                .attr("name", "goals[" + index + "].assistantPlayer.guid").attr("id", "goals" + index + ".assistantPlayer.guid");
            $newTr.find("td:eq(3)").find("select")
                .attr("name", "goals[" + index + "].goalTime").attr("id", "goals" + index + ".goalTime");
            $newTr.find("td:eq(4)").find(".ownGoal")
                .attr("name", "goals[" + index + "].ownGoal").attr("id", "goals" + index + ".ownGoal");

            $newTr.appendTo($lastTr.parent());
            $lastTr.find("td a.removeGoal").addClass("hidden");
        });
    },
    ownGoal:function () {
        $("#goalTable").on("click", "input.ownGoal", function () {
            var $this = $(this);
            if ($this.is(":checked")) {
                $this.parent().parent().parent().parent().find("td select").not(":last").attr("disabled", true);
            } else {
                $this.parent().parent().parent().parent().find("td select").not(":last").attr("disabled", false);
            }
        });
    },
    removeGoal:function () {
        $("#goalTable").on("click", "a.removeGoal", function () {
            if (confirm('确认删除? ')) {
                var $thisTr = $(this).parent().parent();
                $thisTr.prev().find("td a.removeGoal").removeClass("hidden");
                $thisTr.remove();
            }
        });
    },
    matchTime:function () {
        $('input#matchTime').datetimepicker({
            format:'yyyy-mm-dd hh:ii',
            autoclose:true,
            language:"zh-CN",
            todayHighlight:true,
            minuteStep:10,
            weekStart:1
        });
    },
    changeNotice:function () {
        $("#noticeGuid").change(function () {
            var $this = $(this);
            var $stadium = $("#stadiumGuid");
            var $opponent = $("#opponentGuid");
            var $matchTime = $("#matchTime");

            if ('' == $this.val()) {
                $stadium.val("");
                $opponent.val("");
                $matchTime.val("");
                return;
            }

            var $selectedOption = $this.find("option:selected");
            var stadiumGuid = $selectedOption.attr("stadiumGuid");
            var clubGuid = $selectedOption.attr("clubGuid");
            var time = $selectedOption.attr("time");

            $stadium.find("option").filter("[value='" + stadiumGuid + "']").attr("selected", true);
            $opponent.find("option").filter("[value='" + clubGuid + "']").attr("selected", true);
            $matchTime.val(time);

        });
    }
};

/**
 * album_manage.jsp
 * @param alert
 * @constructor
 */
function AlbumManage(alert, albumGuid) {
    ZhiShiFenZi.prototype.initialAlert(alert);
    this.initColorbox();
    this.initUpload(albumGuid);
    this.lazyImg();
}

AlbumManage.prototype = {
    lazyImg:function () {
        $("img.lazy").lazyload({
            effect:"fadeIn",
            threshold:10
        });
    },
    initUpload:function (albumGuid) {
        $('#file_upload').uploadify({
            'swf':'../../../resources/uploadify/uploadify.swf',
            'uploader':'upload.zsfz',
            'fileObjName':'files',
            'buttonText':'选择图片...',
            'progressData':'上传中,请稍候...',
            'formData':{'albumGuid':albumGuid},
            'auto':false,
            'multi':true,
            'fileTypeDesc':'图片文件',
            'fileTypeExts':'*.jpg;*.jpeg;*.gif;*.bmp;*.png',
            'fileSizeLimit':'1MB',
            'queueID':'fileQueue',
            'uploadLimit':10,
            'debug':false,
            'onQueueComplete':function () {
                location.href = location.pathname + "?alert=uploadPhotosSuccess";
            }
        });
        $("#uploadBtn").click(function () {
            $('#file_upload').uploadify('upload', '*');
            return false;
        });
        $("#showUploadA").click(function () {
            $('.panel-body .well').toggle(300);
        });

    },
    initColorbox:function () {
        //colorbox init
        $(".pictures-area .pictureGroup").colorbox({
            rel:'pictureGroup',
            maxWidth:'80%',
            photo:true
        });
    }
};

/**
 * album_details.jsp
 * @constructor
 */
function AlbumDetails() {
    this.initColorbox();
    this.lazyImg();
}

AlbumDetails.prototype = {
    lazyImg:function () {
        $("img.lazy").lazyload({
            effect:"fadeIn",
            threshold:10
        });
    },
    initColorbox:function () {
        //colorbox init
        $(".pictures-area .pictureGroup").colorbox({
            rel:'pictureGroup',
            maxWidth:'80%',
            photo:true
        });
    }
};

/**
 * album_form.jsp
 * @constructor
 */
function AlbumForm() {
    this.submitLoading();
}

AlbumForm.prototype = {
    submitLoading:function () {
        $("#albumForm").submit(function () {
            $("#loadingInfo").removeClass("hide");
        });
    }
};

/**
 * b_album.jsp
 * @param alert
 * @constructor
 */
function BackendAlbum(alert) {
    ZhiShiFenZi.prototype.initialAlert(alert);
    this.lazyImg();
}

BackendAlbum.prototype = {
    lazyImg:function () {
        $("img.lazy").lazyload({
            effect:"fadeIn",
            threshold:10
        });
    }
};

/**
 * b_match_notice.jsp
 * @param alert
 * @constructor
 */
function BackendMatchNotice(alert) {
    ZhiShiFenZi.prototype.initialAlert(alert);
    this.preview();
}

BackendMatchNotice.prototype = {
    preview:function () {
        $("a[preview]").click(function () {
            var preview = $(this).attr("preview");
            $("span#noticePreview span").html(preview).parent()
                .removeClass("hidden").parent().parent().removeClass("hide").fadeIn(5000).delay(8000).slideUp(5000);
        });
    }
};

/**
 * match_notice_form.jsp
 * @constructor
 */
function MatchNoticeForm(startTime) {
    this.bindTime(startTime);
}

MatchNoticeForm.prototype = {
    bindTime:function (startTime) {
        $('input#time').datetimepicker({
            format:'yyyy-mm-dd hh:ii',
            autoclose:true,
            language:"zh-CN",
            todayHighlight:true,
            minuteStep:10,
            startDate:startTime,
            weekStart:1
        });
    }
};


/**
 * b_player.jsp
 * @param alert
 * @constructor
 */
function BackendPlayer(alert) {
    ZhiShiFenZi.prototype.initialAlert(alert);
    this.scrollTop();
    this.playerPopover();
}

BackendPlayer.prototype = {
    playerPopover:function () {
        $("a.playerPopover").popover({
            html:true,
            trigger:"hover",
            placement:"left",
            delay:{ show:500, hide:100 },
            content:function () {
                var content = "暂无";
                var playerGuid = $(this).attr("guid");
                $.ajax({
                    url:"../player_info/" + playerGuid + ".zsfz",
                    async:false,
                    success:function (data) {
                        content = data;
                    }
                });
                return content;
            }
        });
    },
    scrollTop:function () {
        $.scrollUp({
            scrollText:'',
            scrollSpeed:600
        });
    }
};

/**
 * player_account_form.jsp
 * @param alert
 * @constructor
 */
function PlayerAccountForm(alert) {
    ZhiShiFenZi.prototype.initialAlert(alert);
}

/**
 * player_form.jsp
 * @constructor
 */
function PlayerForm() {
    this.bindDatePicker();
}

PlayerForm.prototype = {
    bindDatePicker:function () {
        $('#entryDate').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:"zh-CN",
            todayHighlight:true,
            weekStart:1
        });
        $('#birthday').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:"zh-CN",
            todayHighlight:true,
            weekStart:1
        });
    }
};

/**
 * b_season.jsp
 * @param alert
 * @param add
 * @constructor
 */
function BackendSeason(alert, add) {
    ZhiShiFenZi.prototype.initialAlert(alert);
    this.bindDatePicker();
    this.addFocus(add);
}

BackendSeason.prototype = {
    addFocus:function (add) {
        if (!add) {
            $("input#name").focus();
        }
    },
    bindDatePicker:function () {
        $('#start').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:"zh-CN",
            todayHighlight:true,
            weekStart:1
        });
        $('#end').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:"zh-CN",
            todayHighlight:true,
            weekStart:1
        });
    }
};


/**
 *   my_profile.jsp
 * @param alert
 * @constructor
 */
function MyProfile(alert) {
    ZhiShiFenZi.prototype.initialAlert(alert);
    this.bindDatePicker();
}


MyProfile.prototype = {
    bindDatePicker:function () {
        $('#entryDate').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:"zh-CN",
            todayHighlight:true,
            weekStart:1
        });
        $('#birthday').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:"zh-CN",
            todayHighlight:true,
            weekStart:1
        });
    }
};


/**
 *   front_photo_form.jsp
 * @constructor
 */
function FrontPhotoForm() {
    this.updatePreview();
}

FrontPhotoForm.prototype = {
    updatePreview:function () {
        $("#updatePreview").click(function () {
            var url = $("#url").val();
            var existUrl = $("#existUrl").val();
            if (url != existUrl) {
                $(this).prev().attr("src", url);
            }
        });
    }
};

/**
 * match_details.jsp
 * @constructor
 */
function MatchDetails() {
    this.matchPhotos();
}

MatchDetails.prototype = {
    matchPhotos:function () {
        var $matchPhotos = $("#matchPhotos");
        $matchPhotos.load("../../../match_photos/" + $matchPhotos.attr("guid") + ".zsfz");
    }
};


/**
 * global_setting.jsp
 * @param alert
 * @constructor
 */
function GlobalSetting(alert) {
    ZhiShiFenZi.prototype.initialAlert(alert);
}

/**
 * sync_photos.jsp
 * @param alert
 * @constructor
 */
function SyncPhotos(alert) {
    ZhiShiFenZi.prototype.initialAlert(alert);
}

