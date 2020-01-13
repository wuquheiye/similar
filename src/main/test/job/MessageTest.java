package job;

import job.utils.MessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {
    @Autowired
    private MessageUtil messageUtil;

    /**
     * 发送简单纯文本邮件
     */
    @Test
    public void sendSimpleMail() throws Exception {
//        messageUtil.sendMessage("17620502773","于他的努力和我这个做姐夫的支援，改变了开了。");
        messageUtil.queryNumber();
    }
}
