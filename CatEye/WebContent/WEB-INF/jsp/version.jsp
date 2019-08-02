<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="/CatEye/layui/css/layui.css" media="all">
</head>
<body>

	<table class="layui-hide" id="version" lay-filter="version"></table>
	<script type="text/html" id="toolbar-button">
  <div class="layui-btn-container">
   
	<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="refresh"><i class="layui-icon">&#xe666;</i> 刷新</button>
	<button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe654;</i> 上传新版本</button>
	<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del"><i class="layui-icon">&#xe640;</i> 删除选中</button>

  </div>
	</script>
	<!-- <div class="layui-upload-drag" id="test10">
		<i class="layui-icon"></i>
		<p>点击上传，或将文件拖拽到此处</p>
	</div> -->

	<div id="addVersion" style="display: none;">
		<form class="layui-form" id="edit-form" action="" lay-filter="myfrom"
			method="post" enctype="multipart/form-data">
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 15px;">
				<legend>信息</legend>
			</fieldset>


			<div class="layui-form-item">
				<label class="layui-form-label">版本标示</label>
				<div class="layui-input-block">
					<input type="text" name="versionCode" required value=""
						lay-verify="required" placeholder="1" autocomplete="off"
						class="layui-input" style="width: 50%">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">版本号</label>
				<div class="layui-input-block">
					<input type="text" name="versionName" required value=""
						lay-verify="required" placeholder="1.0.0" autocomplete="off"
						class="layui-input" style="width: 50%">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">描述</label>
				<div class="layui-input-block">
					<input type="text" name="des" required value=""
						lay-verify="required" placeholder="请输入简单描述" autocomplete="off"
						class="layui-input" style="width: 70%">
				</div>
			</div>

			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 15px;">
				<legend>文件上传</legend>
			</fieldset>
			<div class="layui-form-item">

				<label class="layui-form-label">新版本上传</label>
				<div class="layui-upload-drag" id="file" name="file">
					<i class="layui-icon"></i>
					<p>点击上传，或将文件拖拽到此处</p>
					<p id="upload-msg"></p>
				</div>
			</div>
			

			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 15px;">
				<legend>功能</legend>
			</fieldset>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit
						lay-filter="save" id="submit" >立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary"
						id="closeBtn">重置</button>
				</div>
			</div>
		</form>
	</div>
	<script src="/CatEye/js/jquery-1.11.1.min.js"></script>
	<script src="/CatEye/layui/layui.js"></script>
	<script>
		layui.use([ 'upload', 'laydate', 'laypage', 'layer', 'table',
								'carousel', 'upload', 'element', 'slider' ],
						function() {
							var $ = layui.jquery, upload = layui.upload, table = layui.table, element = layui.element, layer = layui.layer, slider = layui.slider, form = layui.form;

							table.render({
								elem : '#version',
								url : 'version/data',
								toolbar : '#toolbar-button',
								title : '数据表格',
								cols : [ [ {
									type : 'checkbox',
									fixed : 'left'
								}, {
									field : 'versionId',
									title : '版本ID',
									fixed : 'left',
									unresize : true,
									sort : true
								}, {
									field : 'versionCode',
									title : '版本标识'
								}, {
									field : 'versionName',
									title : '版本号'
								}, {
									field : 'stringTime',
									title : '上传时间'
								}, {
									field : 'fileName',
									title : '文件名称'
								}, {
									field : 'des',
									title : '描述'
								} ] ],
								page : true
							});
							upload.render({
								elem : '#file',
								url : 'version/upload/',
								done : function(res) {
									layer.msg('上传成功');
									$("#upload-msg").html(res.obj);
								},
								accept : 'file'
							});

							
							
							
							table
									.on(
											'toolbar(version)',
											function(obj) {
												var checkStatus = table
														.checkStatus(obj.config.id);

												switch (obj.event) {
												case 'refresh':
													table.reload('version', {
														url : 'version/data'
													})
													layer.msg('已刷新');
													break;
												case 'add':
													url = "cateye/save"
													addindex = layer
															.open({
																id : 'add',
																type : 1,
																title : "上传新版本",
																closeBtn : 1,
																maxmin : 1,
																anim : 1,
																area : [ '70%',
																		'70%' ],
																resize : false,
																// btn: ['新增', '取消'],
																// btnAlign: 'c',
																content : $("#addVersion"),
																success : function(
																		layero,
																		index) {
																	form
																			.val(
																					'myfrom',
																					{
																						"sn" : ""
																					});
																}
															});

													break;
												case 'del':
													var data = checkStatus.data;
													
													if (data.length > 0) {
														layer
																.confirm(
																		'真的删除行么',
																		function(
																				index) {
																			var ids = "";
																			for (var i = 0; i < data.length; i++) {
																				ids += data[i].versionId
																						+ ",";
																			}

																			$.ajax({
																						url : 'version/delete',
																						type : 'post',
																						data : {
																							"ids" : ids
																						},
																						success : function(
																								data) {
																							
																							layer.close(index);
																							table.reload('version', {
																								url : 'version/data'
																							})
																							layer.msg(data.msg);
																						}
																					});
																		});
													} else {
														layer.msg('请先选中行')
													}

													break;
												}
												;
											});
							
							form.on('submit(save)', function(){
							  	var data=$("#edit-form").serialize();
							    $.ajax({
									url:"version/save",
									type:'post',
									data:data,
									success:function(data){
										
										table.reload('version', {
											url : 'version/data'
										})
										
										layer.close(addindex);
										layer.msg(data.msg);
										
									}
								}); 
							    return false;
						  });

						})
						
						
	</script>
</body>
</html>