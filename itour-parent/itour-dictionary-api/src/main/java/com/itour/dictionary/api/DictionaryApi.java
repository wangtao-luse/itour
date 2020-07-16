package com.itour.dictionary.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
@FeignClient(name = "itour-dictionary-service")
public interface DictionaryApi {
	/**
	 * 字典表列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getDictionaryList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getDictionaryList(RequestMessage requestMessage);
	/**
	 * 字典表列表视图
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getViewDictionaryList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getViewDictionaryList(RequestMessage requestMessage);
	/**
	 * 字典查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getDictData",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getDictData(RequestMessage requestMessage);
	/**
	 * 字典修改
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/updateDictionary",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updateDictionary(RequestMessage requestMessage);
	/**
	 * 字典查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getDictionary",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getDictionary(RequestMessage requestMessage);
	
}
