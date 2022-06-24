package com.itour.dictionary.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseMessage getDictionaryList(@RequestBody RequestMessage requestMessage);
	/**
	 * 字典表列表视图
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getViewDictionaryList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getViewDictionaryList(@RequestBody RequestMessage requestMessage);
	/**
	 * 字典查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getDictData",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getDictData(@RequestBody RequestMessage requestMessage);
	/**
	 * 字典修改
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/updateDictionary",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updateDictionary(@RequestBody RequestMessage requestMessage);
	/**
	 * 字典查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getDictionary",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getDictionary(@RequestBody RequestMessage requestMessage);
	/**
	 * 网站列表查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/queryWebsiteList",produces = {"application/json;charset=UTF-8"})
	ResponseMessage queryWebsiteList(@RequestBody RequestMessage requestMessage);
	/**
	 * 网站单条查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/getWebsite",produces = {"application/json;charset=UTF-8"})
	ResponseMessage getWebsite(@RequestBody RequestMessage requestMessage);
	/**
	 * 网站信息修改
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/updateWebsite",produces = {"application/json;charset=UTF-8"})
	ResponseMessage updateWebsite(@RequestBody RequestMessage requestMessage);
	/**
	 * 敏感字列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/querySensitiveWordList",produces = {"application/json;charset=UTF-8"})
	ResponseMessage querySensitiveWordList(RequestMessage requestMessage);
	/**
	 * 网站访问数据统计列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/selectWebsiteVisitInfoList",produces = {"application/json;charset=UTF-8"})
	ResponseMessage selectWebsiteVisitInfoList(RequestMessage requestMessage);
	/**
	 * 网站访问最近的记录
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/selectRecentlyOne",produces = {"application/json;charset=UTF-8"})
	ResponseMessage selectRecentlyOne(RequestMessage requestMessage);
	/**
	 * 网站访问数据批量保存或修改
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/saveOrUpdateVisitInfoBatch",produces = {"application/json;charset=UTF-8"})
	ResponseMessage saveOrUpdateVisitInfoBatch(RequestMessage requestMessage);
	/**
	 * 网站访问数据IP数据批量保存或修改
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/dictionary/saveOrUpdateIpInfoBatch",produces = {"application/json;charset=UTF-8"})
	ResponseMessage saveOrUpdateIpInfoBatch(RequestMessage requestMessage);

}
