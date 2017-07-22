/**
 * Copyright (c) 2012-2013, Michael Yang 杨福海 (www.yangfuhai.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tsz.afinal.http;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/**
 *
 * @author michael
 *
 * @param <T> 目前泛型支持 String,File, 以后扩展：JSONObject,Bitmap,byte[],XmlDom
 */
public abstract class AjaxCallBack<T> {

	private boolean progress = true;
	private int rate = 1000 * 1;//每秒
	private HttpResponse response=null;
	private HttpRequest request=null;
//	private Class<T> type;
//	
//	public AjaxCallBack(Class<T> clazz) {
//		this.type = clazz;
//	}


	public HttpResponse getResponse() {
		return response;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}

	public boolean isProgress() {
		return progress;
	}

	public int getRate() {
		return rate;
	}

	/**
	 * 设置进度,而且只有设置了这个了以后，onLoading才能有效。
	 * @param progress 是否启用进度显示
	 * @param rate 进度更新频率
	 */
	public AjaxCallBack<T> progress(boolean progress , int rate) {
		this.progress = progress;
		this.rate = rate;
		return this;
	}

	public void onStart(){};
	/**
	 * onLoading方法有效progress
	 * @param count
	 * @param current
	 */
	public void onLoading(long count,long current){};
	public void onSuccess(T t){};
	//public void onSuccess_Origin_String(String t){};

	/**
	 * 只对返回内容中包含了"<soap:Body"或"<soapenv:Body")，做解析；其他的不认为是soap内容
	 * @param s
	 */
	public void onSuccess_SoapContent(String s){};
	public void onFailure(Throwable t,int errorNo ,String strMsg){};
}
