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
}

ZhiShiFenZi.prototype = {
    sideNav:function () {
        var obj = $('#mainMenu li').removeClass('active');
        var localUrl = location.pathname;
        if (localUrl.indexOf('match') != -1) {
            obj.filter(".matchLi").addClass('active');
        } else if (localUrl.indexOf('data') != -1) {
            obj.filter(".dataLi").addClass('active');
        } else if (localUrl.indexOf('album') != -1) {
            obj.filter(".albumLi").addClass('active');
        } else if (localUrl.indexOf('record') != -1) {
            obj.filter(".recordLi").addClass('active');
        } else if (localUrl.indexOf('about') != -1) {
            obj.filter(".aboutLi").addClass('active');
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
    }
};

$(function () {
    new ZhiShiFenZi();
});


//CompanyLogForm.prototype = {
//    bindDatePicker:function () {
//        $('#timeAsDate').datepicker({
//            format:'yyyy-mm-dd',
//            autoclose:true,
//            language:"zh-CN",
//            todayHighlight:true,
//            weekStart:1
//        });
//    }
//};

/**
 * about_main.jsp
 * @constructor
 */
function AboutMain() {
    this.scrollTop();
}

AboutMain.prototype = {
    scrollTop:function () {
        $.scrollUp({
            scrollText:'',
            scrollSpeed:800
        });
    }
};


/**
 * data_main.jsp
 * @constructor
 */
function DataMain() {
    this.loadSeasonData();
    this.scrollTop();
    this.playerPopover();
}

DataMain.prototype = {
    playerPopover:function () {
        $("a.playerPopover").popover({
            html:true,
            trigger:"hover",
            placement:"auto",
            delay:{ show:500, hide:100 },
            content:function () {
                var content = "暂无";
                var playerGuid = $(this).attr("guid");
                $.ajax({
                    url:"player_info/" + playerGuid + ".zsfz",
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
            scrollSpeed:800
        });
    },
    loadSeasonData:function () {
        $("a[currSeason='false']").click(function () {
            var guid = $(this).attr("seasonGuid");
            location.href = location.pathname + "?guid=" + guid;
        });
    }
};


/**
 * album_main.jsp
 * @constructor
 */
function AlbumMain() {
    this.scrollTop();
    this.initialColorbox();
    this.lazyImg();
}

AlbumMain.prototype = {
    lazyImg:function () {
        $("img.lazy").lazyload({
            effect:"fadeIn",
            threshold:10
        });
    },
    scrollTop:function () {
        $.scrollUp({
            scrollText:'',
            scrollSpeed:800
        });
    },
    initialColorbox:function () {
        //colorbox init
        $(".pictures-area a").colorbox({
            maxWidth:'80%',
            rel:'pictureGroup',
            photo:true
        });
    }
};

/**
 * match_details.jsp
 * @constructor
 */
function MatchDetails() {
    this.initPopover();
    this.matchPhotos();
}

MatchDetails.prototype = {
    initPopover:function () {
        $("a.playerPopover").popover({
            html:true,
            trigger:"hover",
            placement:"auto",
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
    matchPhotos:function () {
        var $matchPhotos = $("#matchPhotos");
        $matchPhotos.load("../match_photos/" + $matchPhotos.attr("guid") + ".zsfz");
    }
};

