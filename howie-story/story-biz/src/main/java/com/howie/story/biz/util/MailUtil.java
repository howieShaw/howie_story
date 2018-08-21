//package com.howie.story.biz.util;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.mail.HtmlEmail;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
///**
// * @Author:xiaohaoyun
// * @Description：
// * @Date：created in 上午10:37 2018/7/23
// * @Modified by:xiaohaoyun
// */
//public class MailUtil {
//
//        private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);
//
//        private MailUtil() {
//        }
//
//        // 默认的编码方式
//        private static final String MAIL_DEFAULT_CHARSET = "UTF-8";
//        // 默认的连接等待时间（ms）
//        private static final int MAIL_DEFAULT_CONNECTION_TIMEOUT = 5000;
//        // 默认的数据等待时间（ms）
//        private static final int MAIL_DEFAULT_SOCKET_TIMEOUT = 30000;
//
//
//        private static String host = "smtpin.meituan.com";
//
//        private static int port = 25;
//        // 发件人邮箱 sender
//        private static String sender = "orderdish@meituan.com";
//        // 发件人姓名
//        private static String senderName = "pull-action-alarm";
//
//
//        /**
//         * @param title     邮件标题
//         * @param content   邮件内容，支持html格式
//         * @param receivers 邮件接受者
//         */
//        public static void sendMail(String title, String content, List<String> receivers) {
//
//            HtmlEmail email = new HtmlEmail();
//            email.setCharset(MAIL_DEFAULT_CHARSET);
//            email.setHostName(host);
//            email.setSmtpPort(port);
//            email.setSocketConnectionTimeout(MAIL_DEFAULT_CONNECTION_TIMEOUT);
//            email.setSocketTimeout(MAIL_DEFAULT_SOCKET_TIMEOUT);
//
//            try {
//                for (String recipient : receivers) {
//                    email.addTo(recipient);
//                }
//
//                email.setFrom(sender, senderName);
//                email.setSubject(title);
//                email.setMsg(StringUtils.trimToEmpty(content));
//                email.send();
//            } catch (Exception e) {
//                LOGGER.error(String.format("邮件发送失败，邮件主题：%s，收件人：%s，邮件内容：%s", title, receivers, content), e);
//                throw new RuntimeException(e);
//            }
//        }
//
//
//}
