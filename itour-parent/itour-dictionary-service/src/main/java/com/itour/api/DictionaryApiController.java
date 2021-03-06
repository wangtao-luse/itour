package com.itour.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.dictionary.api.DictionaryApi;
import com.itour.service.DictionaryService;
import com.itour.service.ViewDDictionaryService;
import com.itour.service.WebsiteRecommendService;
@RestController
public class DictionaryApiController implements DictionaryApi {
	@Autowired
    DictionaryService dictionaryService;
	@Autowired
	ViewDDictionaryService viewDDictionaryService;
	@Autowired
	WebsiteRecommendService websiteRecommendService;
	/**
	 * 字典列表
	 */
	@Override
	public ResponseMessage getDictionaryList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return dictionaryService.getDictionaryList(requestMessage);
	}
	/**
	 * 字典列表(视图)
	 */
	@Override
	public ResponseMessage getViewDictionaryList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewDDictionaryService.getViewDictionaryList(requestMessage);
	}
	/**
	 * 字典查询
	 */
	@Override
	public ResponseMessage getDictData(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return dictionaryService.getDictData(requestMessage);
	}
	/**
	 * 字典修改
	 */
	@Override
	public ResponseMessage updateDictionary(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return dictionaryService.updateDictionary(requestMessage);
	}
    /**
     * 字典查询单条
     */
	@Override
	public ResponseMessage getDictionary(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return dictionaryService.getDictionary(requestMessage);
	}
	/**
	 * 网址列表查询
	 */
	@Override
	public ResponseMessage queryWebsiteList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return websiteRecommendService.queryWebsiteList(requestMessage);
	}
	/**
	 * 网址查询单条
	 */
	@Override
	public ResponseMessage getWebsite(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return websiteRecommendService.getWebsite(requestMessage);
	}
	/**
	 * 网址查询单条
	 */
	@Override
	public ResponseMessage updateWebsite(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return websiteRecommendService.updateWebsite(requestMessage);
	}


}
