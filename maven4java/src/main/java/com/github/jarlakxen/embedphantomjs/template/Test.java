package com.github.jarlakxen.embedphantomjs.template;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Test {
	 Configuration freeMarkerCfg = new Configuration();  
	    Template template = null;  
	    public Test() {  
	        freeMarkerCfg.setClassForTemplateLoading(getClass(),"");  
	        freeMarkerCfg.setObjectWrapper(new DefaultObjectWrapper());  
	        try {  
	            template = freeMarkerCfg.getTemplate("page.ftl");  
	        } catch (IOException e) {  
	            // TODO  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    public void generateRequest(ValueObject obj) {  
	  
	        String reqFileName = "D:\\tmp\\test.xml";  
	        try {  
	            Map<String, Object> parameters = new HashMap<String, Object>();  
	  
	            parameters.put("p", obj);  
	  
	            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(reqFileName), "UTF-8");  
	            template.process(parameters, writer);  
	            writer.flush();  
	        } catch (IOException e) {  
	            e.printStackTrace();// TODO  
	        } catch (TemplateException e) {  
	            e.printStackTrace();// TODO  
	        }  
	  
	    }  
	    public static void main(String[] args) {  
	        ValueObject val = new ValueObject();  
	          
	        val.setFname("Yao");  
	        val.setGname("Yorker");  
	        val.setEmail("test@mail.com");  
	        val.setManager("manager");  
	        val.setAge(12041);
	          
	        new Test().generateRequest(val);  
	    }  
}
