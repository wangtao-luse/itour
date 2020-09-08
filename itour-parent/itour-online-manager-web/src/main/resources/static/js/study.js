/**
 * 初始化带分页的initDataGrid
 * @param dataGridNode 元素节点$("#dataGridNode")
 * @param newOptions  属性 var options={width:'auto',	height:'auto',fitColumns:true,pagination:true....}
 * @returns
 */
function initDataGrid(url,dataGridNode,newOptions){
	//1.初始化默认属性
	var options ={
			width:'auto',
			height:'auto',
			fitColumns:true,//自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
			pagination:true,//在数据网格（datagrid）底部显示分页工具栏
			idField:'id',//标识字段
			sortName:'id',//可以排序的列
			sortOrder:'asc',//指定排序的方式,只能用 'asc' 或 'desc'
			checkOnSelect:true,//当用户点击某一行时，则会选中/取消选中复选框。如果设置为 false 时，只有当用户点击了复选框时，才会选中/取消选中复选框。
			singleSelect:true,//只允许选中一行
			selectOnCheck:true,//如果设置为 true，点击复选框将会选中该行。如果设置为 false，选中该行将不会选中复选框。
			nowrap:true,//设置为 true，则把数据显示在一行里。设置为 true 可提高加载性能。
			pageNumber:1,//当设置了 pagination 属性时，初始化页码。
			pageSize:(newOptions.postData&&newOptions.postData.page&&newOptions.postData.page.size?newOptions.postData.page.size:"100"),//当设置了 pagination 属性时，初始化页面尺寸。
			pageList:[50, 100, 300, 500, 1000],//当设置了 pagination 属性时，初始化页面尺寸的选择列表。
			toolbar: '#toolbar',//数据网格（datagrid）面板的头部工具栏
			onLoadSuccess:function(data){
				//当数据加载成功时触发
				 $('#searchForm table').show();
		         $.messager.progress('close');
			}
		};
	//2.覆盖默认属性
	$.extend(options, newOptions);
    //3.创建数据网格（datagrid）
	dataGridNode.datagrid(options);
	//4.创建分页对象
	dataGridNode.datagrid("getPager").pagination({
		total:newOptions.data.total,
		onSelectPage:function(pageNumber, pageSize){
			//当用户选择新的页面时触发
			//4.1修改前端提交的信息
			newOptions.postData.page.size = pageSize;
            newOptions.postData.page.current = pageNumber;
            //4.2修改DataGrid属性
            newOptions.pageNumber = pageNumber;
            newOptions.pageSize = pageSize;
            //4.3调用initAjaxDataGrid更新数据
            initAjaxDataGrid(url, dataGridNode, newOptions);
		},
		onChangePageSize:function(pageSize){
			//当用户改变页面显示的条数时触发
			//4.1修改前端提交到后端的数据
			newOptions.postData.page.size = pageSize;
			//4.2修改DataGrid属性
            newOptions.pageSize = pageSize;	
            //4.3调用initAjaxDataGrid更新数据
		    initAjaxDataGrid(url, dataGridNode, newOptions)
		},
		onRefresh:function(){
			//刷新之后触发
		}
	});
	return dataGridNode;
}

/**
 * 初始化带分页、Ajax请求的DataGrid
 * @param url
 * @param dataGridNode
 * @param newOptions
 * @returns
 */
function initAjaxDataGrid(url,dataGridNode,newOptions){
	//1.提交后端数据处理
    var options = {postData: {}};
    $.extend(options, newOptions);
    //2.发送Ajax请求
    postAjax(url,JSON.stringify(options.postData),function(data){
    	//2.1后去后台数据返回的JSON数据
    	if(data.returnResult.result){
    	  var formatData={};
    	  if(data.returnResult.result.records) {
    		  //2.2组装DataGrid数据
              formatData.total = data.returnResult.result.total;
              formatData.rows = data.returnResult.result.records;
             // formatData={"total":"2","rows":[{"id":"01","name":"zhangsan","age":"18"},{"id":"02","name":"mk","age":"25"}]};
          }else{
              internalError();
              return;
          }
    	}
    	//https://www.cnblogs.com/polk6/p/js-var.html(JavaScript 变量作用域)
    	 options.data = formatData;	
    	 console.log("formatData------>");
    	 console.log(formatData);
    	 //待研究
    	 if ($.data(dataGridNode[0], "datagrid")) {
             dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
         }
    	 console.log(options);
    	 initDataGrid(url,dataGridNode, options);
    },{})
}
function initAjaxDataGrid(url,dataGridNode,newOptions,postData){
	//1.提交后端数据处理
    var options = {postData: {}};
    $.extend(options, newOptions);
    //2.发送Ajax请求
    postAjax(url,JSON.stringify(postData),function(data){
    	//2.1后去后台数据返回的JSON数据
    	if(data.returnResult.result){
    	  var formatData={};
    	  if(data.returnResult.result.records) {
    		  //2.2组装DataGrid数据
              formatData.total = data.returnResult.result.total;
              formatData.rows = data.returnResult.result.records;
             // formatData={"total":"2","rows":[{"id":"01","name":"zhangsan","age":"18"},{"id":"02","name":"mk","age":"25"}]};
          }else{
              internalError();
              return;
          }
    	}
    	//https://www.cnblogs.com/polk6/p/js-var.html(JavaScript 变量作用域)
    	 options.data = formatData;	
    	 console.log("formatData------>");
    	 console.log(formatData);
    	 //待研究
    	 if ($.data(dataGridNode[0], "datagrid")) {
             dataGridNode.datagrid('unselectAll').datagrid('uncheckAll')
         }
    	 console.log(options);
    	 initDataGrid(url,dataGridNode, options);
    },{})
}
/**
 * 查询(带分页、没有统计)
 * @param url 
 * @param node   $("#dataGrid")
 * @param funcName  回调函数
 * @param op         属性值
 * @returns
 */
function searchFun(url,node,funcName,op){
   	//序列化表单
   	// {'current': 1, 'size': 40,'k':'vo'}
    var formData = $.serializeObject($('#searchForm'));
    op&&op.nodeNames && op.nodeNames.length > 0?
            $.each(op.nodeNames, function (index, value) {
                postData[value] = $("#" + op.nodes[index]).val();
            }):"";
    var postData ={"page":{}};
    op&&op.k?postData[op.k]=formData:'';
    postData.page.current=op&&op.current?op.current:"1";
    postData.page.size=op&&op.size?op.size:"100";
    var options = funcName({postData: postData});
    //为了在搜索的时候保证size的正确性：获取分页中的size
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? postData.page.size = pageNode.val() : "100";
    initAjaxDataGrid(url,node,options);
    }
function searchFun(url,node,funcName,op,postData){
   	//序列化表单
    op&&op.nodeNames && op.nodeNames.length > 0?
            $.each(op.nodeNames, function (index, value) {
                postData[value] = $("#" + op.nodes[index]).val();
            }):"";
    var postData ={"page":{}};
    postData.page.current=op&&op.current?op.current:"1";
    postData.page.size=op&&op.size?op.size:"100";
    var options = funcName({postData: postData});
    //为了在搜索的时候保证size的正确性：获取分页中的size
    var pageNode = node.closest(".datagrid-wrap").find(".pagination-page-list");
    pageNode.length > 0 ? postData.page.size = pageNode.val() : "100";
    initAjaxDataGrid(url,node,options,postData);
    }
/**
 * 初始化无分页的initDataGridNoPager
 * @param dataGridNode 元素节点$("#dataGridNode")
 * @param newOptions  属性 var options={width:'auto',	height:'auto',fitColumns:true,pagination:true....}
 * @returns
 */
function initDataGridNoPager(dataGridNode,newOptions){
	//1.初始化默认属性
	var options ={
			width:'auto',
			height:'auto',
			fitColumns:true,//自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动
			pagination:true,//在数据网格（datagrid）底部显示分页工具栏
			idField:'id',//标识字段
			sortName:'id',//可以排序的列
			sortOrder:'asc',//指定排序的方式,只能用 'asc' 或 'desc'
			checkOnSelect:true,//当用户点击某一行时，则会选中/取消选中复选框。如果设置为 false 时，只有当用户点击了复选框时，才会选中/取消选中复选框。
			singleSelect:true,//只允许选中一行
			selectOnCheck:true,//如果设置为 true，点击复选框将会选中该行。如果设置为 false，选中该行将不会选中复选框。
			nowrap:true,//设置为 true，则把数据显示在一行里。设置为 true 可提高加载性能。
			pageNumber:1,//当设置了 pagination 属性时，初始化页码。
			pageSize:(newOptions.postData&&newOptions.postData.page&&newOptions.postData.page.size?newOptions.postData.page.size:"100"),//当设置了 pagination 属性时，初始化页面尺寸。
			pageList:[50, 100, 300, 500, 1000],//当设置了 pagination 属性时，初始化页面尺寸的选择列表。
			toolbar: '#toolbar',//数据网格（datagrid）面板的头部工具栏
			onLoadSuccess:function(data){
				//当数据加载成功时触发
				 $('#searchForm table').show();
		         $.messager.progress('close');
			}
		};
	//2.覆盖默认属性
	$.extend(options, newOptions);
    //3.创建数据网格（datagrid）
	dataGridNode.datagrid(options);
	return dataGridNode;

}
