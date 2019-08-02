<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="/CatEye/layui/css/layui.css" media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body class="body" style="padding: 10px;">

<table class="layui-hide" id="test" lay-filter="test"></table>
 
<script type="text/html" id="toolbar-button">
  <div class="layui-btn-container">
    <!-- <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button> -->
	<br>
	<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="refresh"><i class="layui-icon">&#xe666;</i> 刷新</button>
	<button class="layui-btn layui-btn-sm layui-btn-disabled" lay-event="add"><i class="layui-icon">&#xe654;</i> 添加</button>
	<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delall"><i class="layui-icon">&#xe640;</i> 批量删除</button>

  </div>
</script>
 
<script type="text/html" id="edit-del-bar">
  <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="find"><i class="layui-icon">&#xe63a;</i>查看</a>
  <a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
</script>
              
   <div id="add-update" style="display: none;">
       <form class="layui-form" id="edit-form" action="//www.baidu.com" lay-filter="myfrom" method="post" enctype="multipart/form-data">
       	 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
			  <legend>信息</legend>
		 </fieldset>
         <div class="layui-form-item">
	          <label class="layui-form-label">设备号</label>
	          <div class="layui-input-block">
	           		<input type="text" name="sn" required value=""  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" style="width: 50%">
	          </div>
         </div>
         
         <fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
			  <legend>功能</legend>
		 </fieldset>
         <div class="layui-form-item">
          <div class="layui-input-block">
           	<button class="layui-btn layui-btn-disabled" lay-submit lay-filter="save" disabled>立即提交</button>
           	<button type="reset" class="layui-btn layui-btn-primary" id="closeBtn" >重置</button>
          </div>
         </div> 
       </form>  
   </div>   
	 
<script src="/CatEye/js/jquery-1.11.1.min.js"></script>	
<script src="/CatEye/layui/layui.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 --> 
 
<script>
layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
  var table = layui.table,
  element=layui.element,
  layer=layui.layer,
  slider=layui.slider,
  form=layui.form;
  
  var updateindex,addindex;
  var url;
  
  table.render({
    elem: '#test'
    ,url:'cateye/data'
    ,toolbar: '#toolbar-button'
    ,title: '数据表格'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field:'deviceId', title:'ID', fixed: 'left', unresize: true, sort: true}
      ,{field:'sn', title:'设备编号'}
      ,{fixed: 'right', title:'操作', toolbar: '#edit-del-bar', width:230}
    ]]
    ,page: true
  });
  
  //头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    
    switch(obj.event){
      /* case 'getCheckData':
        var data = checkStatus.data;
        layer.alert(JSON.stringify(data));
      break;
      case 'getCheckLength':
        var data = checkStatus.data;
        layer.msg('选中了：'+ data.length + ' 个');
      break;
      case 'isAll':
        layer.msg(checkStatus.isAll ? '全选': '未全选');
      break; */
      case 'refresh':
    	  table.reload('test',{
    		  url:'cateye/data'
    	  })
          layer.msg('已刷新');
      break;
      case 'add':
    	  url="cateye/save"
    	  addindex=layer.open({
      		  id:'add',
              type: 1,
              title:"添加用户",
              closeBtn: 1,
              maxmin:1,
              anim:1,
              area: ['70%', '70%'],
              resize:false,
              // btn: ['新增', '取消'],
              // btnAlign: 'c',
              content: $("#add-update"),
              success: function(layero, index){
            	  form.val('myfrom', {
              	    "sn": ""
              	  });
              }
          });
    	  
      break;
      case 'delall':
    	  var data = checkStatus.data;
    	  if(data.length>0){
    		  layer.confirm('真的删除行么', function(index){
	    		  var ids="";
	    		  for(var i=0;i<data.length;i++){
	    			  ids+=data[i].deviceId+",";
	    		  }
	    		  
	    		  $.ajax({
	      			url:'cateye/deleteCat',
	      			type:'post',
	      			data:{"ids":ids},
	      			success:function(data){
	      				
	      				layer.close(index);
	      				
	      			}
	      		  });
    		  });  
    	  }else{
    		  layer.msg('请先选中行')
    	  }
    	  
      break;
    };
  });
  
  //监听行工具事件
  table.on('tool(test)', function(obj){
    var data = obj.data;
    //console.log(obj)
    
    
    if(obj.event === 'del'){
      	layer.confirm('真的删除行么', function(index){
      		layer.msg();
      		$.ajax({
    			url:'cateye/deleteCat',
    			type:'post',
    			data:{'ids':data.deviceId},
    			success:function(result){
    				
    				layer.close(index);
    				layer.msg(result);
    				obj.del();
    			}
    		}); 	
      		
      });
    } else if(obj.event === 'edit'){
    	
      /* layer.prompt({
        formType: 2
        ,value: data.name
      }, function(value, index){
        obj.update({
          name: value
        });
        layer.close(index);
      }); */
      url="cateye/save?id="+data.deviceId;
      
      updateindex=layer.open({
    		id:'update',
            type: 1,
            title:"编辑用户",
            closeBtn: 1,
            maxmin:1,
            anim:1,
            area: ['70%', '70%'],
            resize:false,
            // btn: ['新增', '取消'],
            // btnAlign: 'c',
            content: $("#add-update"),
            success: function(layero, index){
            	form.val('myfrom', {
            	    "sn": data.sn
            	});
            }
        });
      
    }else if(obj.event === 'find'){
    	layer.alert('需要写个信息查看弹框，添加一下就好了。信息：'+JSON.stringify(data));
    }
  });
  
  //监听提交
  form.on('submit(save)', function(){
	  	var data=$("#edit-form").serialize();
	    $.ajax({
			url:url,
			type:'post',
			data:data,
			success:function(data){
				layer.close(updateindex);
				layer.close(addindex);
				layer.msg(data);
			}
		}); 
	    return false;
  });
  
});
</script>

</body>
</html>