package com.andaily.zhishifenzi.domain.match;

import com.andaily.zhishifenzi.domain.player.Player;
import com.andaily.zhishifenzi.domain.player.PlayerRepository;
import com.andaily.zhishifenzi.domain.shared.Application;
import com.andaily.zhishifenzi.domain.shared.BeanProvider;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.infrastructure.STRender;
import com.andaily.zhishifenzi.infrastructure.mail.MailTransmitResult;
import com.andaily.zhishifenzi.infrastructure.mail.MailTransmitter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 14-12-11
 * <p/>
 * 当发布比赛预告时,
 * 发邮件通知所有球员比赛预告的信息
 * <p/>
 * 若球员未设置 邮件则不发送
 * <p/>
 * 新启动一个线程来完成这个任务.
 *
 * @author Shengzhao Li
 */
public class MatchNoticeMailSender extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(MatchNoticeMailSender.class);
    private static final String EMAIL_SUBJECT = " 比赛预告 -知识分子足球队";

    private transient PlayerRepository playerRepository = BeanProvider.getBean(PlayerRepository.class);
    private MatchNotice matchNotice;

    public MatchNoticeMailSender(MatchNotice matchNotice) {
        this.matchNotice = matchNotice;
    }

    //发送邮件
    public boolean send() {
        this.start();
        return true;
    }

    /*
    * .查找球员邮件
    * 生成邮件内容
    * 依次发送
    *
    * */
    @Override
    public void run() {

        STRender render = initialSTRender();

        List<Player> players = playerRepository.findAvailablePlayers();
        for (Player player : players) {
            if (availableMailAddress(player)) {
                sendToPlayer(render, player);
            }
        }
    }

    private void sendToPlayer(STRender render, Player player) {
        String mailContent = generateMailContent(player, render);
        String subject = DateUtils.toDateText(matchNotice.time(), MatchNotice.DATE_WEEK_FORMAT) + EMAIL_SUBJECT;

        MailTransmitter mailTransmitter = new MailTransmitter(subject, mailContent, player.email());
        MailTransmitResult result = mailTransmitter.transmit();
        LOG.debug("Send MatchNotice[{}] mail to Player[name={}] result [{}]", matchNotice, player.fullName(), result.success());
    }

    private STRender initialSTRender() {
        Map<String, Object> model = new HashMap<>();
        model.put("matchTime", DateUtils.toDateText(matchNotice.time(), MatchNotice.DATE_WEEK_FORMAT));
        model.put("oppo", matchNotice.opponent().name());
        model.put("position", matchNotice.stadium().name());
        model.put("remark", matchNotice.remark());
        model.put("host", Application.host());
        return new STRender("template/match_notice_mail.html", model);
    }

    private String generateMailContent(Player player, STRender render) {
        render.addAttribute("playerName", player.fullName());
        return render.render();
    }


    //检查邮件地址是否设置
    private boolean availableMailAddress(Player player) {
        String email = player.email();
        return StringUtils.isNotEmpty(email);
    }
}
