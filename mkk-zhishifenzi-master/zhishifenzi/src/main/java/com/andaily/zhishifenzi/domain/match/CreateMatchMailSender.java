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
 * 14-12-12
 * <p/>
 * 当添加一场比赛后, 发邮件给球队所有队员(有email的)
 *
 * @author Shengzhao Li
 */
public class CreateMatchMailSender extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(CreateMatchMailSender.class);


    private transient PlayerRepository playerRepository = BeanProvider.getBean(PlayerRepository.class);
    private Match match;

    public CreateMatchMailSender(Match match) {
        this.match = match;
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
        STRender stRender = initialSTRender();
        String subject = generateMailSubject();

        List<Player> players = playerRepository.findAvailablePlayers();
        for (Player player : players) {
            if (availableMailAddress(player)) {
                sendToPlayer(player, stRender, subject);
            }
        }
    }

    private STRender initialSTRender() {
        String scoresInfo = generateScoresInfo();

        Map<String, Object> model = new HashMap<>();
        model.put("matchTime", matchSpecifyTime());
        model.put("oppo", match.opponent().name());
        model.put("scores", match.homeScores() + " : " + match.awayScores());
        String host = Application.host();
        //http://andaily.com/zsfz/match_details/c36d5f75d0ce49afbfe920484f134dee1417962966911.zsfz
        model.put("link", host + "match_details/" + match.guid() + ".zsfz");
        model.put("host", host);
        model.put("scoresInfo", scoresInfo);

        return new STRender("template/match_created_mail.html", model);
    }

    private String matchSpecifyTime() {
        return DateUtils.toDateText(match.matchTime(), MatchNotice.DATE_WEEK_FORMAT);
    }

    private void sendToPlayer(Player player, STRender stRender, String subject) {
        stRender.addAttribute("playerName", player.fullName());
        String mailContent = stRender.render();

        MailTransmitter mailTransmitter = new MailTransmitter(subject, mailContent, player.email());
        MailTransmitResult result = mailTransmitter.transmit();
        LOG.debug("Send Match[{}] mail to Player[name={}] result [{}]", match, player.fullName(), result.success());
    }

    // [3:1]  - 12月14日 (星期日), 15:30 VS 蓉乐足球队 的比赛信息 -知识分子足球队
    private String generateMailSubject() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(match.homeScores()).append(":").append(match.awayScores()).append("]");
        sb.append(" - ").append(matchSpecifyTime()).append(" VS ")
                .append(match.opponent().name()).append(" 比赛信息 - 知识分子足球队");
        return sb.toString();
    }

    //检查邮件地址是否设置
    private boolean availableMailAddress(Player player) {
        String email = player.email();
        return StringUtils.isNotEmpty(email);
    }

    private String generateScoresInfo() {
        StringBuilder sb = new StringBuilder();
        STRender render = new STRender("template/match_created_score_item.html");
        Map<String, Object> model = new HashMap<>();

        List<Goal> goals = match.goals();
        for (Goal goal : goals) {
            boolean og = goal.ownGoal();
            model.put("og", og);
            if (!og) {
                model.put("scorePlayer", goal.goalPlayer().fullName());
                Player assPlayer = goal.assistantPlayer();
                model.put("assistPlayer", assPlayer != null ? assPlayer.fullName() : "无");
            }
            sb.append(render.render(model));
        }
        return sb.toString();
    }
}
