package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.LabelService;
import com.itour.service.WorkColumnService;
import com.itour.service.WorkInfoService;
@RestController
public class WorkApiController implements WorkApi {
	@Autowired
	WorkInfoService workInfoService;
	@Autowired
	LabelService labelService;
	@Autowired
	WorkColumnService workColumnService;
	/**
	 * 日志标签列表查询
	 */
	@Override
	public ResponseMessage queryLabelList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return labelService.queryLabelList(requestMessage);
	}
   /**
    * 日志分类专栏查询
    */
	@Override
	public ResponseMessage queryColumnList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workColumnService.queryColumnList(requestMessage);
	}
	/**
	 *  新增或修改工作日志
	 */
	@Override
	public ResponseMessage savaOrUpdateWorkInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.saveOrUpdateWorkInfo(requestMessage);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public ResponseMessage selectWorkInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.selectWorkInfoList(requestMessage);
	}

	@Override
	public ResponseMessage selectWorkInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.selectWorkInfo(requestMessage);
	}

	@Override
	public ResponseMessage searchTextList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.searchTextList(requestMessage);
	}

	@Override
	public ResponseMessage queryPersonCenterList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.queryPersonCenterList(requestMessage);
	}

	@Override
	public ResponseMessage getInfoData(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.getInfoData(requestMessage);
	}

	@Override
	public ResponseMessage updatePvBatch(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.updatePvBatch(requestMessage);
	}

	

	
}
