<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="./easyui/demo.css">
	<script type="text/javascript" src="./easyui/jquery.min.js"></script>
	<script type="text/javascript" src="./easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="./easyui/jquery.edatagrid.js"></script>
	<script>
	  $(function(){
		  $('#dg_user').edatagrid({
		  		title:'用户管理',
		  		url:'./user_getusers',
		  		saveUrl:'./user_adduser',
		  		updateUrl:'./user_updateuser',
		  		destroyUrl:'./user_deluser',
		  		idField:'uid',
		  		fitColumns:true,
		  		columns:[[
	  				{field:'uid',title:'用户ID',editor:{type:'validatebox',options:{required:false}}},
	  				{field:'useremail',title:'用户email',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'username',title:'用户名',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'userpass',title:'用户密码',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'score',title:'积分',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'state',title:'账户状态',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'word',title:'宣言',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'flag',title:'账户权限',editor:{type:'validatebox',options:{required:true}}}
		  		]],
		  		toolbar: [{
					iconCls: 'icon-save',
					text:'保存',
					handler: function(){$('#dg_kaoheshuju').edatagrid('saveRow')}
				},'-',{
					iconCls: 'icon-add',
					text:'增加',
					handler: function(){$('#dg_kaoheshuju').edatagrid('addRow')}
				},'-',{
					iconCls: 'icon-remove',
					text:'删除',
					handler: function(){$('#dg_kaoheshuju').edatagrid('destroyRow')}
				}]
			  });
		  });
		$(function(){
		  $('#dg_article').edatagrid({
		  		title:'文章管理',
		  		url:'./article_getallarticles',
		  		saveUrl:'./article_addarticle',
		  		updateUrl:'./article_updatearticle',
		  		destroyUrl:'./article_delarticle',
		  		idField:'aid',
		  		fitColumns:true,
		  		columns:[[
	  				{field:'aid',title:'文章ID',editor:{type:'validatebox',options:{required:false}}},
	  				{field:'uid',title:'用户ID',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'title',title:'标题',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'content',title:'内容',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'time',title:'时间',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'tag',title:'tag',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'type',title:'类型（0为博文）',editor:{type:'validatebox',options:{required:true}}},
	  				{field:'looktimes',title:'被查看次数',editor:{type:'validatebox',options:{required:true}}}
		  		]],
		  		toolbar: [{
					iconCls: 'icon-add',
					text:'增加',
					handler: function(){$('#dg_kaoheshuju').edatagrid('addRow')}
				},'-',{
					iconCls: 'icon-remove',
					text:'删除',
					handler: function(){$('#dg_kaoheshuju').edatagrid('destroyRow')}
				}]
			  });
		  });
	  </script>
  </head>
  
  <body>
  <table id="dg_user"></table>
  <table id="dg_article"></table>
  </body>
</html>
