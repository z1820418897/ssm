<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>layui</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  
  <style>
  	.layui-body {
         overflow: visible;
    }
  	.layui-tab{
  		margin: 0px;
  	}
	.my-body .layui-tab, .my-body .layui-tab .layui-tab-content, .my-body .layui-tab .layui-tab-item {
		height: 100%;
	}
	.my-body .layui-tab, .my-body .layui-tab .layui-tab-content{
		margin: 0;
		padding: 0
	}
	
	  </style>
</head>
<body class="layui-layout-body">
 
<!-- 你的HTML代码 -->
 <div class="layui-layout layui-layout-admin">
 	<!--
     	作者：郑华晨
     	时间：2018-09-14
     	描述：头部标题栏
     -->
 	<div class="layui-header">
 			<a class="layui-logo" href="#">
 				<img src="" alt="网页logo"/>
 			</a>
 			<ul class="layui-nav layui-layout-left">
 				<li class="layui-nav-item">
 					<a href="#">
						导航1					
 					</a>
 				</li> 	
 				<li class="layui-nav-item layui-this">
 					<a href="#">
						导航2				
 					</a>
 				</li> 
 				<li class="layui-nav-item layui-hide-xs">
 					<a href="#">
						导航3
					<span class="layui-badge-dot"></span>
 					</a>
 				</li> 
 				<li class="layui-nav-item layui-hide-xs">
 					<a href="#" target="_blank">
						导航4			
 					</a>
 				</li> 
 				<li class="layui-nav-item">
 					<a href="javascript:;">
						下拉菜单
						<span class="layui-nav-more"></span>
 					</a>
 					<dl class="layui-nav-child layui-anim layui-anim-upbit">
 						<dd>
 							<a href="#" target="_blank">下拉选项1</a>
 							<a href="#" target="_blank">下拉选项2</a>
 						</dd>
 						<hr />
 						<dd>
 							<a href="#" target="_blank">下拉选项3</a>
 							<a href="#" target="_blank">下拉选项4</a>
 						</dd>
 						<hr />
 						<dd>
 							<a href="#" target="_blank">下拉选项5</a>
 						</dd>
 					</dl>
 					
 				</li>
 			</ul>
 			<ul class="layui-nav layui-layout-right">
 				<li class="layui-nav-item">
 					<a href="#">
						<img src="" alt="头像" class="layui-nav-img"/>xxx  
 					</a>
 					<dl class="layui-nav-child">
 						<dd><a href="#">我的资料</a></dd>
 						<dd><a href="#">安全设置</a></dd>
 					</dl>
 				</li>
 			</ul>
 	</div>
 	<!--
     	作者：郑华晨
     	时间：2018-09-14
     	描述：左侧列表
     -->
 	<div class="layui-side layui-bg-black">
 		<div class="layui-side-scroll">
 			<ul class="layui-nav layui-nav-tree" lay-filter="test">
 				<li class="layui-nav-item">
		          <a href="javascript:;">xx管理</a>
		          <dl class="layui-nav-child">
		            <dd><a href="javascript:;" data-id="1" data-title="管理xx" data-url="//www.baidu.com/"
                               class="site-demo-active" data-type="tabAdd">管理xx</a></dd>
		            <dd><a href="javascript:;"data-id="2" data-title="管理xx" data-url="datagrid"
                               class="site-demo-active" data-type="tabAdd">管理xx</a></dd>
		          </dl>
		        </li>
		        <li class="layui-nav-item">
		          <a href="javascript:;" >xx管理</a>
		          <dl class="layui-nav-child">
		            <dd><a href="javascript:;">管理xx</a></dd>
		            <dd><a href="javascript:;">管理xx</a></dd>
		          </dl>
		        </li>
		        <li class="layui-nav-item">
		        	<a href="//www.baidu.com/">百度一下</a>
		        </li>
 			</ul>
 			
 		</div>
 	</div>
 	<!--
     	作者：郑华晨
     	时间：2018-09-14
     	描述：中间内容
     -->
     
     
    <div class="layui-body my-body" >
	    	<div class="layui-tab layui-tab-card" lay-filter="mytabs" lay-allowclose="true">
	    		<ul class="layui-tab-title"></ul>
	    		<div class="layui-tab-content">
	    		</div>
	    	</div>
  	</div>
    	
 	<!--
     	作者：郑华晨
     	时间：2018-09-14
     	描述：底部栏
     -->
 	<div class="layui-footer" style="text-align: center;">
 		GC-Robot 2018-9-25
 	</div>
 	
 </div>
 
 
 
<script src="layui/layui.js"></script>
<script>
layui.use(['element', 'layer', 'jquery'], function () {
        var element = layui.element;
        // var layer = layui.layer;
        var $ = layui.$;
        // 配置tab实践在下面无法获取到菜单元素
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);
            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            } else {
                //否则判断该tab项是否以及存在
                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                alert(url);
                element.tabAdd('mytabs', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                    id: id //规定好的id
                })
                FrameWH();  //计算ifram层的大小
            },
            tabChange: function (id) {
                //切换到指定Tab项
                element.tabChange('mytabs', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("mytabs", id);//删除
            }
        };
        function FrameWH() {
            var h = $(window).height();
            $("iframe").css("height",h+"px");
        }
    });
</script> 
</body>
</html>