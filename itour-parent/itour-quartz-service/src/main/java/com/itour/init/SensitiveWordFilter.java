package com.itour.init;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.travel.SensitiveWord;
import com.itour.util.FastJsonUtil;
@Configuration
public class SensitiveWordFilter implements InitializingBean {

	/**
	 * https://www.jianshu.com/p/f0af22d671a5
	 * https://blog.csdn.net/qq_33101675/article/details/77836725
	 */
	@Autowired
	TravelApi travelApi;
	// {冰={毒={isEnd=1}, isEnd=0}, 白={粉={isEnd=1}, isEnd=0}, 大={麻={isEnd=1}, isEnd=0, 坏={蛋={isEnd=1}, isEnd=0}}}
    private static Map<Object, Object> sensitiveWordMap;
 
    public static int minMatchType = 1; //最小匹配规则
    public static int maxMatchType = 2; //最大匹配规则
    /**
     * 是否包含敏感词
     *
     * @param txt
     * @return
     */
    public boolean isSensitive(String txt) {
        return isSensitive(txt, minMatchType);
    }
 
    /**
     * 是否包含敏感词
     *
     * @param txt
     * @param matchType
     * @return
     */
    public boolean isSensitive(String txt, int matchType) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag = this.checkSensitiveWord(txt, i, matchType);
            if (matchFlag > 0) {
                flag = true;
            }
        }
        return flag;
    }
 
    /**
     * 获取文本中的敏感词
     *
     * @param txt
     * @return
     */
    public Set<String> getSensitiveWord(String txt) {
        return getSensitiveWord(txt, maxMatchType);
    }
 
    /**
     * 获取文本中的敏感词
     *
     * @param txt
     * @param matchType
     * @return
     */
    public Set<String> getSensitiveWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<>();
        for (int i = 0; i < txt.length(); i++) {
            int length = checkSensitiveWord(txt, i, matchType);
            if (length > 0) {
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1; //减1的原因，是因为for会自增
            }
        }
        return sensitiveWordList;
    }
 
    /**
     * 替换文本中的敏感词
     *
     * @param txt
     * @param replaceChar
     * @return
     */
    public String replaceSensitiveWord(String txt, String replaceChar) {
        return replaceSensitiveWord(txt, maxMatchType, replaceChar);
    }
 
    /**
     * 替换文本中的敏感词
     *
     * @param txt
     * @param matchType
     * @param replaceChar
     * @return
     */
    public String replaceSensitiveWord(String txt, int matchType, String replaceChar) {
        String resultTxt = txt;
        Set<String> set = this.getSensitiveWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }
        return resultTxt;
    }
 
    private String getReplaceChars(String replaceChar, int length) {
        String resultReplace = replaceChar;
        for (int i = 1; i < length; i++) {
            resultReplace += replaceChar;
        }
        return resultReplace;
    }
 
    private int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        Map nowMap = sensitiveWordMap;
        boolean flag = false;
        char word = 0;
        int matchFlag = 0;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word); //获取指定key
            if (nowMap == null) {
                break;
            }
            matchFlag++;
            if (isEnd(nowMap)) {
                flag = true;
                if (SensitiveWordFilter.minMatchType == matchType) {
                    break;
                }
            }
        }
        if (matchFlag < 2 || !flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }
 
    /**
     * 是否为最后一个
     *
     * @param nowMap
     * @return
     */
    private boolean isEnd(Map nowMap) {
        boolean flag = false;
        if ("1".equals(nowMap.get("isEnd"))) {
            flag = true;
        }
        return flag;
    }


	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		 initSensitiveWord();
	}
	  /**
     * 初始化敏感词
     */
    private void initSensitiveWord() {
    	JSONObject jsonObject = new JSONObject();
    	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
        ResponseMessage sensitiveWordList = this.travelApi.querySensitiveWordList(requestMessage);
        if(Constant.SUCCESS_CODE.equals(sensitiveWordList.getResultCode())&&!StringUtils.isEmpty(sensitiveWordList.getReturnResult())) {
        	List<SensitiveWord> list = FastJsonUtil.mapToList(sensitiveWordList.getReturnResult(), SensitiveWord.class, Constant.COMMON_KEY_RESULT);
	        Set<String> sensitiveWordSet = new HashSet<>();
	        list.stream().forEach(l -> {
	            sensitiveWordSet.add(l.getWordContent());
	        });
	        Iterator<String> iterator = sensitiveWordSet.iterator();
	        String key;
	        Map nowMap;
	        Map<String, String> newWorMap;
	        sensitiveWordMap = new HashMap(sensitiveWordSet.size());
	        while (iterator.hasNext()) {
	            key = iterator.next(); // 冰毒
	            nowMap = sensitiveWordMap;
	            for (int i = 0; i < key.length(); i++) {
	 
	                char charKey = key.charAt(i); // 冰
	                Object wordMap = nowMap.get(charKey);
	                if (wordMap != null) {
	                    nowMap = (Map) wordMap; //一个一个放进Map中
	                } else { //不存在，则构建一个Map,同时将isEnd设置为0，因为它不是最后一个
	                    newWorMap = new HashMap<>();
	                    newWorMap.put("isEnd", "0");//不是最后一个
	                    nowMap.put(charKey, newWorMap);//没有这个key，就把(isEnd，0) 放在Map中
	                    nowMap = newWorMap;
	                }
	                if (i == key.length() - 1) { //最后一个
	                    nowMap.put("isEnd", "1");
	                }
	            }
	            System.out.println(sensitiveWordMap);
	        }
      }
    }


}
