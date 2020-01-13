package job.utils;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//http://smsow.zhen
//
// http://smsow.zhenzikj.com/doc/java_sdk_doc.html
//  1.安装
//      方法1:使用maven安装
//          <dependency>
//              <groupId>com.zhenzikj</groupId>
//              <artifactId>zhenzisms</artifactId>
//              <version>1.0.4</version>
//          </dependency>
//          下载最新的maven版本: maven
//    方法2:直接下载jar安装
//    下载后的SDK只包含一个jar文件，并且不依赖任何其他jar包或文件，直接导入到工程中即可使用。
//  2.用法
//      使用事先申请的appId、appSecret初始化ZhenziSmsClient:
//      appId、appSecret的获取请参考准备
//      ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
//      apiUrl为请求地址，个人开发者使用https://sms_developer.zhenzikj.com，企业开发者使用https://sms.zhenzikj.com
//      1)发送短信
//          Map<String, String> params = new HashMap<String, String>();
//          params.put("message", "验证码为: 1123");
//          params.put("number", "18511111111");
//          String result = client.send(params);
//          send方法用于单条发送短信,所有请求参数需要封装到Map中,所有请求参数请参考下表
//          返回结果是json格式的字符串, code: 发送状态，0为成功。非0为发送失败，可从data中查看错误信息
//          注: 测试发送短信内容不要使用"你好"或"12132"全数字形式，这种短信内容没有具体的意义，可能会被运营商屏蔽
//          {
//              "code":0,
//              "data":"发送成功"
//          }
//      请求参数
//      参数名称    必选 	类型 	    描述
//      message     是 	    string 	    短信内容
//      number 	    是 	    string 	    接收者手机号码
//      messageId 	否 	    string 	    messageId即该条短信的唯一标识, 不能重复
//      clientIp 	否 	    string 	    客户端IP，需要与应用设置中的”客户IP限额“配合使用，主要防止用户恶意刷短信
//    错误代码表
//    错误码 	原因 	                解决方案
//      100 	参数格式错误 	        检查请求参数是否为空, 或手机号码格式错误
//      101 	短信内容超过1000字 	    短信内容过长，请筛检或分多次发送
//      105 	appId错误或应用不存在 	请联系工作人员申请应用或检查appId是否输入错误
//      106 	应用被禁止 	            请联系工作人员查看原因
//      107 	ip错误 	                如果设置了ip白名单，系统会检查请求服务器的ip地址，已确定是否为安全的来源访问
//      108 	短信余额不足 	        需要到用户中心进行充值
//      109 	今日发送超过限额 	        如果设置了日发送数量，则每个接收号码不得超过这个数量
//      110 	应用秘钥(AppSecret)错误 	检查AppSecret是否输入错误，或是否已在用户中心进行了秘钥重置
//      111 	账号不存在 	            请联系工作人员申请账号
//      1000 	系统位置错误 	        请联系工作人员或技术人员检查原因
//  2)查看余额
//      通过该接口可查看当前剩余的短信条数
//      String result = client.balance();
//      返回结果是json格式的字符串, code: 查询状态，0为成功，data为剩余短信条数。非0为查询失败，可从data中查看错误信息
//            错误代码表
//      错误码 	原因 	                    解决方案
//      100 	参数格式错误 	            检查请求参数是否为空
//      105 	appId错误或应用不存在 	    请联系工作人员申请应用或检查appId是否输入错误
//      106 	应用被禁止 	                请联系工作人员查看原因
//      107 	ip错误 	                    如果设置了ip白名单，系统会检查请求服务器的ip地址，已确定是否为安全的来源访问
//      110 	应用秘钥(AppSecret)错误 	    检查AppSecret是否输入错误，或是否已在用户中心进行了秘钥重置
//      1000 	系统位置错误 	            请联系工作人员或技术人员检查原因
//  3)查询短信
//      接口描述
//      根据messageId查询已发送短信
//      String result = client.findSmsByMessageId("dfee_dfdw_xdfd_dfdfd");
//      注: 必须是post请求,个人开发者域名使用sms_developer.zhenzikj.com，企业开发者域名使用sms.zhenzikj.com
//      请求参数
//      参数名称 	必选 	类型 	描述
//      appId 	    是 	    string 	应用id，可通过用户中心，应用详情查看
//      appSecret 	是 	    string 	应用秘钥，可通过用户中心，应用详情查看
//      messageId 	是 	    string 	信息id，对应发送短信接口的messageId字段
//      返回结果
//      返回结果是json格式的字符串, code: 查询状态，0为成功。非0为失败，可从data中查看错误信息
//    {
//        "code":0,
//         "data":{}
//    }
//    返回结果是json格式的字符串, code: 查询状态，0为成功，data短信信息的json字符串

@Component
public class MessageUtil {
    @Value("${spring.message.apiUrl}")
    private String apiUrl;

    @Value("${spring.message.appId}")
    private String appId;

    @Value("${spring.message.appSecret}")
    private String appSecret;

    /**
     * 发短信
     *
     * @param telephonenumber  发送给手机号码
     * @param verificationCode 短信内容
     * @throws Exception
     */
    public void sendMessage(String telephonenumber, String verificationCode) throws Exception {
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        Map<String, String> params = new HashMap<String, String>();
        String message = "验证码为 :" + verificationCode ;
        params.put("number", telephonenumber);
        params.put("message", message);
        String result = client.send(params);
        System.out.println(result);
    }

    /**
     * 查询短信剩余条数
     *
     * @throws Exception
     */
    public void queryNumber() throws Exception {
        ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
        String result = client.balance();
        System.out.println(result);
    }

}
