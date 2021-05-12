package com.test;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.itour.OnlienApp;
import com.itour.common.vo.ExUsernamePasswordToken;
import com.itour.constant.Constant;
import com.itour.model.vo.PageInfo;

/***https://blog.csdn.net/qq_35915384/article/details/80227297
 * 1.添加@RunWith(SpringRunner.class)
 *   @RunWith是JUnit的一个注解, 用来告诉JUnit不要使用内置的方式进行单元测试, 
 *   而应该使用指定的类做单元测试 对于Spring单元测试总是要使用 SpringRunner.class;
 * 2.添加 @SpringBootTest(classes = OnlienApplication.class)
 *   用来指定SpringBoot应用程序的入口类, 该注解默认会根据包名逐级往上找, 一直找到一个SpringBoot主程序class为止, 
 *   然后启动该类为单元测试准备Spring上下文环境.  Spring单元测试并不在每个测试方法前都移动一个全新的Spring上下文, 
 *   因为这样做太耗费时间, 而是会缓存上下文环境. 如果某个测试方法需要重新准备Spring上下文, 
 *   需要在该方法上加 @DirtiesContext 注解. 
 *3.@Test注解: JUnit在执行每个测试方法之前, 都会为测试类创建一个新的实例, 这样有助于隔离各个测试方法之前的相互影响.
 *4.如果要插入数据到数据库的话必须加上@Transactional
 *5.@Before初始化方法，执行当前测试类的每个测试方法前执行
 *6.@Test：测试方法，在这里可以测试期望异常和超时时间
 *7.一个单元测试类执行顺序为：@BeforeClass –> @Before –> @Test –> @After –> @AfterClass
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlienApp.class)
@AutoConfigureMockMvc //Controller测试的，需要是用这个注解
public class TestSpringboot {
	@Autowired
    private WebApplicationContext applicationContext;
	private MockMvc mockMvc;
	private MockHttpSession mockHttpSession;
	private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;
	@Autowired//解决org.apache.shiro.UnavailableSecurityManagerException
	private SecurityManager securityManager;
	private Subject subject;
	

	@Before //@Before初始化方法，执行当前测试类的每个测试方法前执行
    public void setupMockMvc(){
		//1.初始化MockMvc对象
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		mockHttpSession = new MockHttpSession(applicationContext.getServletContext());
		mockHttpServletRequest = new MockHttpServletRequest(applicationContext.getServletContext());
        mockHttpServletResponse = new MockHttpServletResponse();
        mockHttpServletRequest.setSession(mockHttpSession);
        SecurityUtils.setSecurityManager(securityManager);

        //login("1712854561@qq.com", "top958958");
    }
	private void login(String username, String password) {
		//https://www.freesion.com/article/1793181827/
	        subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse).buildWebSubject();
	        String ip = mockHttpServletRequest.getLocalAddr();
	        String cname=null;
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("ip", "127.0.0.1");
			ExUsernamePasswordToken token = new ExUsernamePasswordToken(username, password, ip,cname,jsonObject,mockHttpServletRequest);
	        subject.login(token);
	        ThreadContext.bind(subject);
	}

		
	//@Test
	public void testInsert() throws Exception {
		//2.构建请求
		String url ="/travel/inserTravelTag";
		String jsonStr ="{\"arr\":[{\"tag\":\"北京旅行\",\"uid\":\"10000\"},{\"tag\":\"苏州旅行\",\"uid\":\"10000\"},{\"tag\":\"香港旅行\",\"uid\":\"10000\"}]}";
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonStr.getBytes())
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.session(mockHttpSession);
		//3.执行请求
		ResultActions perform = mockMvc.perform(requestBuilder);
		//4.返回处理
		perform.andExpect(MockMvcResultMatchers.status().isOk())
		       .andDo(MockMvcResultHandlers.print());
		 MvcResult mvcResult = perform.andReturn();
	        String result = mvcResult.getResponse().getContentAsString();
	        System.out.println("客户端获得反馈数据:" + result);

	}
	@Test
	public void testPersonCenterList() throws Exception {
		String url ="/travel/queryPersonCenterList";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("uid", "10000");
		jsonObject.put("type", "1");
		jsonObject.put(Constant.COMMON_KEY_PAGE, new PageInfo());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(jsonObject.toJSONString().getBytes())
						.accept(MediaType.APPLICATION_JSON_VALUE);
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcReturn = perform.andReturn();
		String result = mvcReturn.getResponse().getContentAsString();
		System.out.println("testPersonCenterList: "+result);
	}
}
