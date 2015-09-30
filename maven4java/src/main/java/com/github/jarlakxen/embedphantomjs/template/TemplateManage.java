package com.github.jarlakxen.embedphantomjs.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.jarlakxen.embedphantomjs.ExecutionTimeout;
import com.github.jarlakxen.embedphantomjs.PhantomJSReference;
import com.github.jarlakxen.embedphantomjs.executor.PhantomJSFileExecutor;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateManage {
	
	private  Configuration freeMarkerCfg = new Configuration();  
	private Template template = null;  
	
	private static TemplateManage tm;
	private TemplateManage(){
		 freeMarkerCfg.setClassForTemplateLoading(getClass(),"");  
	     freeMarkerCfg.setObjectWrapper(new DefaultObjectWrapper());  
	}
	
	public static TemplateManage getInstance(){
		if(tm==null){
			tm = new TemplateManage();
		}
		return tm;
	}
	
	public  File getOutPutFile(String templatePath,WebPageObject wo,File temp){
		 try {  
	            Map<String, Object> parameters = new HashMap<String, Object>();  
	  
	            parameters.put("u", wo);  
	  
	            template = freeMarkerCfg.getTemplate(templatePath);  
	            
	            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(temp), "UTF-8");  
	            template.process(parameters, writer);  
	            writer.flush();  
	        } catch (IOException e) {  
	            e.printStackTrace();// TODO  
	        } catch (TemplateException e) {  
	            e.printStackTrace();// TODO  
	        }  
		return temp;
	}
	
	public static void main(String[] args) throws IOException {
		String file_name = RandomStringUtils.randomAlphabetic(10);
		WebPageObject wo = new WebPageObject();
		wo.setHeight(800);
		wo.setWidth(924);;
		wo.setUrl("http://cn.hao123.com/");
		wo.setOutputPath("d:\\"+file_name+".png");
		
		File tmp = File.createTempFile(RandomStringUtils.randomAlphabetic(10), ".js");
		File f = TemplateManage.getInstance().getOutPutFile("webpage1.ftl",wo, tmp);
		System.out.println(f.getAbsolutePath());
		
		PhantomJSFileExecutor ex = new PhantomJSFileExecutor(PhantomJSReference.create().build(), new ExecutionTimeout(15, TimeUnit.SECONDS));
        try {
			String r = ex.execute(f).get();
			System.out.println(r);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        f.delete();
	}
}
