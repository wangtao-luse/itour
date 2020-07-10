package com.itour.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.dictionary.api.DictionaryApi;
import com.itour.service.DictionaryService;
import com.itour.service.ViewDDictionaryService;
@RestController
public class DictionaryApiController implements DictionaryApi {
	@Autowired
    DictionaryService dictionaryService;
	@Autowired
	ViewDDictionaryService viewDDictionaryService;
	@Override
	public ResponseMessage getDictionaryList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return dictionaryService.getDictionaryList(requestMessage);
	}
	@Override
	public ResponseMessage getViewDictionaryList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewDDictionaryService.getViewDictionaryList(requestMessage);
	}
	@Override
	public ResponseMessage getDictData(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return dictionaryService.getDictData(requestMessage);
	}


}
