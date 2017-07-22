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
package net.tsz.afinal.http.entityhandler;

import org.apache.http.HttpEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class FileEntityHandler {
	
	private boolean mStop = false;
	
	

	public boolean isStop() {
		return mStop;
	}



	public void setStop(boolean stop) {
		this.mStop = stop;
	}

	/**网上的观点
	 * 根据代码，我们可以看到一个明显的问题——流没有关闭。这个问题好改，自己发现了关闭就行我就不多解释了。还有一个问题，就是这一句
		long count = entity.getContentLength() + current;
        远端文件的大小是被不断增大的，下载任务每被暂停一次，远端文件的大小就被增大一次，增加的大小等于本地已下载的碎片文件的大小。这么做的后果有两个：1、这个断点下载功能完全没有使用，每次下载都是从0开始。2、本地文件由于是续传，越来越大。
	 * @param f
	 */
	//此处为afinal 原代码部分
//	public Object handleEntity(HttpEntity entity, EntityCallBack callback,String target,boolean isResume) throws IOException {
//		if (TextUtils.isEmpty(target) || target.trim().length() == 0)
//			return null;
//
//		File targetFile = new File(target);
//
//		if (!targetFile.exists()) {
//			targetFile.createNewFile();
//		}
//
//		if(mStop){
//			return targetFile;
//		}
//			
//		
//		long current = 0;
//		FileOutputStream os = null;
//		if(isResume){
//			current = targetFile.length();
//			os = new FileOutputStream(target, true);
//		}else{
//			os = new FileOutputStream(target);
//		}
//		
//		if(mStop){
//			return targetFile;
//		}
//			
//		InputStream input = entity.getContent();
//		long count = entity.getContentLength() + current;
//		
//		if(current >= count || mStop){
//			return targetFile;
//		}
//		
//		int readLen = 0;
//		byte[] buffer = new byte[1024];
//		while (!mStop && !(current >= count) && ((readLen = input.read(buffer,0,1024)) > 0) ) {//未全部读取
//			os.write(buffer, 0, readLen);
//			current += readLen;
//			callback.callBack(count, current,false);
//		}
//		callback.callBack(count, current,true);
//		
//		if(mStop && current < count){ //用户主动停止
//			throw new IOException("user stop download thread");
//		}
//		
//		return targetFile;
//	}
	////////////////
	//以下为网上的替代部分
	public void closeRandomAccessFile(RandomAccessFile f){
		try {
			if (f!=null) {
				f.close();
				f=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public Object handleEntity(HttpEntity entity, EntityCallBack callback,
			String target, boolean isResume) throws IOException {
		File save=null;
		RandomAccessFile file = null;
		try {
	        long current = 0;
	        save=new File(target);
	        file = new RandomAccessFile(save, "rw");
	        if (isResume) {
	            current = file.length();
	        }
	        InputStream input = entity.getContent();
	        long count = entity.getContentLength() + current;
	        if (mStop) {
	        	closeRandomAccessFile(file);
	            return save;
	        }
	        // 在这里其实这样写是不对的，之所以如此是为了用户体验，谁都不想自己下载时进度条都走了一大半了，就因为一个暂停一下子少了一大串
	        /**
	         * 这里实际的写法应该是： <br>
	         * current = input.skip(current); <br>
	         * file.seek(current); <br>
	         * 根据JDK文档中的解释：Inputstream.skip(long i)方法跳过i个字节，并返回实际跳过的字节数。<br>
	         * 导致这种情况的原因很多，跳过 n 个字节之前已到达文件末尾只是其中一种可能。这里我猜测可能是碎片文件的损害造成的。
	         */
	        file.seek(input.skip(current));
	 
	        int readLen = 0;
	        byte[] buffer = new byte[1024];
	 
	        while ((readLen = input.read(buffer, 0, 1024)) != -1) {
	            if (mStop) {
	                break;
	            } else {
	                file.write(buffer, 0, readLen);
	                current += readLen;
	                callback.callBack(count, current,false);
	            }
	        }
	        callback.callBack(count, current,true);
	 
	        if (mStop && current < count) { // 用户主动停止
	        	closeRandomAccessFile(file);
	            throw new IOException("user stop download thread");
	        }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        closeRandomAccessFile(file);
        return save;
    }
}
