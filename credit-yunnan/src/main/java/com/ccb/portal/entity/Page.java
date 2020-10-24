package com.ccb.portal.entity;

import com.ccb.portal.util.PageData;



/**
 * 分页类
 *
 */
public class Page {

	private int showCount; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
//	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	private PageData pd = new PageData();

	public Page(){
		try {
			this.showCount =10;
		} catch (Exception e) {
			this.showCount = 10;
		}
	}

	public int getTotalPage() {
		if(totalResult%showCount==0)
			totalPage = totalResult/showCount;
		else
			totalPage = totalResult/showCount+1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		if(currentPage>getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

//	//拼接分页 页面及JS函数
//	public String getPageStr() {
//		StringBuffer sb = new StringBuffer();
//		if(this.getTotalResult()>0){
//			sb.append("	<ul class=\"pagination pull-right no-margin\">\n");
//			if(this.getCurrentPage()==1){
//				sb.append("	<li><a>共<font style='color:#126ab8;'>"+this.getTotalResult()+"</font>条</a></li>\n");
////				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
////				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
////				sb.append("	每页显示<select style='display:inline-block;' lay-ignore>\n");
////				sb.append("	<option value='10'>10</option>\n");
////				sb.append("	<option value='20'>20</option>\n");
////				sb.append("	<option value='30'>30</option>\n");
////				sb.append("	<option value='50'>50</option>\n");
////				sb.append("	</select>条\n");
//				sb.append("	<li><a>首页</a></li>\n");
//				sb.append("	<li><a>上页</a></li>\n");
//			}else{
//				sb.append("	<li><a>共<font style='color:#126ab8;'>"+this.getTotalResult()+"</font>条</a></li>\n");
//				//sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
////				//sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
////				sb.append("	每页显示<select style='display:inline-block;' lay-ignore>\n");
////				sb.append("	<option value='10'>10</option>\n");
////				sb.append("	<option value='20'>20</option>\n");
////				sb.append("	<option value='30'>30</option>\n");
////				sb.append("	<option value='50'>50</option>\n");
////				sb.append("	</select>条\n");
//				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">首页</a></li>\n");
//				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(this.getCurrentPage()-1)+")\">上页</a></li>\n");
//			}
//			int showTag = 5;//分页标签显示数量
//			int startTag = 1;
//			if(this.getCurrentPage()>=showTag){
//				startTag = this.getCurrentPage()-1;
//			}
//			int endTag = startTag+showTag-1;
//			for(int i=startTag; i<= this.getTotalPage() && i<=endTag; i++){
//				if(this.getCurrentPage()==i)
//					sb.append("<li class=\"active\"><a><font color='white'>"+i+"</font></a></li>\n");
//				else
//					sb.append("	<li style=\"cursor:pointer;\" onclick=\"nextPage("+i+")\"><a>"+i+"</a></li>\n");
//			}
//			if(this.getCurrentPage()==this.getTotalPage()){
//				sb.append("	<li><a>下页</a></li>\n");
//				sb.append("	<li><a>尾页</a></li>\n");
//			}else{
//				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(this.getCurrentPage()+1)+")\">下页</a></li>\n");
//				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+this.getTotalPage()+")\">尾页</a></li>\n");
//			}
//			sb.append("	<li><a>共"+this.getTotalPage()+"页</a></li>\n");
//			sb.append("每页显示	<select title='显示条数' lay-ignore style=\"display:inline-block;\" onchange=\"changeCount("+(this.getCurrentPage())+",this.value)\">\n");
//			if(10== this.getShowCount()){
//				sb.append("	<option value='10' selected>10</option>\n");
//				sb.append("	<option value='30'>30</option>\n");
//				sb.append("	<option value='50'>50</option>\n");
//			}else if(30==this.getShowCount()){
//				sb.append("	<option value='10' >10</option>\n");
//				sb.append("	<option value='30' selected>30</option>\n");
//				sb.append("	<option value='50'>50</option>\n");
//			}else if(50==this.getShowCount()){
//				sb.append("	<option value='10' >10</option>\n");
//				sb.append("	<option value='30'>30</option>\n");
//				sb.append("	<option value='50' selected>50</option>\n");
//			}else{
//				sb.append("	<option value='10' selected>10</option>\n");
//				sb.append("	<option value='30'>30</option>\n");
//				sb.append("	<option value='50'>50</option>\n");
//			}
//			sb.append("	</select>条\n");
//			sb.append("	\n");
//
//			sb.append("</ul>\n");
//			sb.append("<script type=\"text/javascript\">\n");
//
//			//换页函数
//			sb.append("function nextPage(page){\n");
//			sb.append("	if(document.forms[0].getAttribute(\"type\")=='0'){");
//			sb.append("\n		_GLOBE_DATA(\"reLoadPageParam\")(_GLOBE_DATA(\"createURL\")(rooturl,\"searchbox\",page,"+this.getShowCount()+"));\n");
//			sb.append("\n	}");
//			sb.append("else{\n 		if(true && document.forms[0]){\n");
//			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
//			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		url = url + page + \"&" +(entityOrField?"showCount":"page.showCount")+"="+this.getShowCount()+"\";\n");
//			//sb.append("		$(\"#iframeContent\").empty(); $(\"#iframeContent\").load(url);\n"); // 使用load()
//			sb.append("		document.forms[0].action = url;\n");   //页面使用iframe
//			sb.append("		document.forms[0].submit();\n");
//			sb.append("	}else{\n");
//			sb.append("		var url = document.location+'';\n");
//			sb.append("		if(url.indexOf('?')>-1){\n");
//			sb.append("			if(url.indexOf('currentPage')>-1){\n");
//			sb.append("				var reg = /currentPage=\\d*/g;\n");
//			sb.append("				url = url.replace(reg,'currentPage=');\n");
//			sb.append("			}else{\n");
//			sb.append("				url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";\n");
//			sb.append("			}\n");
//			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		url = url + page + \"&" +(entityOrField?"showCount":"page.showCount")+"="+this.getShowCount()+"\";\n");
//			sb.append("		document.location = url;\n");
//			//sb.append("		      $(\"#iframeContent\").empty(); $(\"#iframeContent\").load(url);\n");
//			sb.append("	}}\n");
//			sb.append("}\n");
//
//			//调整每页显示条数
//			sb.append("function changeCount(page,value){");
//			sb.append("	if(document.forms[0].getAttribute(\"type\")=='0'){");
//			sb.append("\n	debugger;	_GLOBE_DATA(\"reLoadPageParam\")(_GLOBE_DATA(\"createURL\")(rooturl,\"searchbox\",page,value));\n");
//			sb.append("\n	}");
//			sb.append("else{     if(true && document.forms[0]){\n");
//			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
//			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		url = url + \"1&" +(entityOrField?"showCount":"page.showCount")+"=\"+value;\n");
//			sb.append("		document.forms[0].action = url;\n");
//			sb.append("		document.forms[0].submit();\n");
//			sb.append("	}else{\n");
//			sb.append("		var url = document.location+'';\n");
//			sb.append("		if(url.indexOf('?')>-1){\n");
//			sb.append("			if(url.indexOf('currentPage')>-1){\n");
//			sb.append("				var reg = /currentPage=\\d*/g;\n");
//			sb.append("				url = url.replace(reg,'currentPage=');\n");
//			sb.append("			}else{\n");
//			sb.append("				url += \"1&"+(entityOrField?"currentPage":"page.currentPage")+"=\";\n");
//			sb.append("			}\n");
//			sb.append("		}else{url += \"?"+(entityOrField?"currentPage":"page.currentPage")+"=\";}\n");
//			sb.append("		url = url + \"&" +(entityOrField?"showCount":"page.showCount")+"=\"+value;\n");
//			sb.append("		document.location = url;\n");
//			sb.append("	}\n");
//			sb.append("}\n");
//			sb.append("}\n");
//			//
//			////跳转函数
//			//sb.append("function toTZ(){");
//			//sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
//			//sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
//			//sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
//			//sb.append("nextPage(toPaggeVlue);");
//			//sb.append("}\n");
//
//
//			sb.append("</script>\n");
//		}
//		pageStr = sb.toString();
//		return pageStr;
//	}

//	public void setPageStr(String pageStr) {
//		this.pageStr = pageStr;
//	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {

		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getShowCount();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

}
