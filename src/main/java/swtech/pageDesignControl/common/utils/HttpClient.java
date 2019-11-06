///*
//package swtech.pageDesignControl.common.utils;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import swtech.pageDesignControl.common.vo.ReturnMsg;
//import swtech.pageDesignControl.service.HotelRelatedService;
//import swtech.pageDesignControl.service.impl.HotelRelateServiceImpl;
//
//@Component
//public class HttpClient {
//
//	@Autowired
//	//public HotelRelatedService hotelRelatedService;
//	private  HotelRelatedService hotelRelatedService ;
//	public static HttpClient httpClient;
//
//	private int i;
//
//	@PostConstruct
//	@Scheduled(cron = "0 0/5 * * * ?")// 0 5 10 * * *每天10：05
//	public void init() {
//		i++;
//		System.out.println("执行定时器第一次"+"======>次数"+i);
//		httpClient = this;
//		httpClient.hotelRelatedService = this.hotelRelatedService;
//		String url="http://192.168.0.152:8091/hotel/wxpayApi/downloadBill?bill_date=20190827";
//
////		 httpClient.hotelRelatedService.timedtask("fasd");
//
//		MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();
//		//		stringMultiValueMap.add("bill_date", "201908");
//		HttpHeaders headers =new HttpHeaders();
//		//		headers.add("key1", "values");
//		//		headers.add("key2", "ddd");
//
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
////		sendGetRequest(url,  stringMultiValueMap, headers);
//	}
////	public static void main(String[] args) {
////		String url="http://pay.m13w.cn/hotel/wxpayApi/downloadBill?bill_date=20190827";
////		MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();
////		//		stringMultiValueMap.add("bill_date", "201908");
////		HttpHeaders headers =new HttpHeaders();
////		//		headers.add("key1", "values");
////		//		headers.add("key2", "ddd");
////
////		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
////		sendGetRequest(url,  stringMultiValueMap, headers);
////	}
//	*/
///**
//	 * 向目的URL发送post请求
//	 * @param url       目的url
//	 * @param params    发送的参数
//	 * @return  AdToutiaoJsonTokenData
//	 *//*
//
//
//	public static String sendPostRequest(String url, MultiValueMap<String, String> params){
//		RestTemplate client = new RestTemplate();
//		//新建Http头，add方法可以添加参数
//		HttpHeaders headers = new HttpHeaders();
//		//设置请求发送方式
//		HttpMethod method = HttpMethod.POST;
//		// 以表单的方式提交
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		//将请求头部和参数合成一个请求
//		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//		//执行HTTP请求，将返回的结构使用String 类格式化（可设置为对应返回值格式的类）
//		ResponseEntity<String> response = client.exchange(url, method, requestEntity,String .class);
//
//		return response.getBody();
//	}
//	*/
///**
//	 * 向目的URL发送get请求
//	 * @param url       目的url
//	 * @param params    发送的参数
//	 * @param headers   发送的http头，可在外部设置好参数后传入
//	 * @return  String
//	 *//*
//
//
//	public static String sendGetRequest(String url, MultiValueMap<String, String> params,HttpHeaders headers){
//		RestTemplate client = new RestTemplate();
//
//		HttpMethod method = HttpMethod.GET;
//		// 以表单的方式提交
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		//将请求头部和参数合成一个请求
//		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//		//执行HTTP请求，将返回的结构使用String 类格式化
//		ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
//
//		System.out.println(response.getBody());
//		ReturnMsg msg =new ReturnMsg();
//		try {
////			HotelRelateServiceImpl hotelRelateServiceImpl = new HotelRelateServiceImpl();
//			msg = httpClient.hotelRelatedService.timedtask(response.getBody());
//		} catch (Exception e) {
//			e.printStackTrace();
//			msg.setStatus("201");
//			msg.setStatusMsg("操作失败");
//			msg.setMsg(e.getMessage());
//		}
//
//		return response.getBody();
//	}
//
//
//
//}
//*/
