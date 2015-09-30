package com.github.jarlakxen.embedphantomjs.servlet;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.jarlakxen.embedphantomjs.ExecutionTimeout;
import com.github.jarlakxen.embedphantomjs.PhantomJSReference;
import com.github.jarlakxen.embedphantomjs.executor.PhantomJSFileExecutor;
import com.github.jarlakxen.embedphantomjs.template.TemplateManage;
import com.github.jarlakxen.embedphantomjs.template.WebPageObject;

/**
 * Servlet implementation class Url2PictureServlet
 */
public class Url2PictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Url2PictureServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("------------");
		String url1 = request.getParameter("url1");
		String url2 = request.getParameter("url2");
		String url3 = request.getParameter("url3");
		String url4 = request.getParameter("url4");
		List<String> urlList = new ArrayList<String>();
		List<String> imagePaths = new ArrayList<String>();
		
		urlList.add(url1);
		urlList.add(url2);
		urlList.add(url3);
		urlList.add(url4);
		
		/*String file_name = RandomStringUtils.randomAlphabetic(6);
		String img_path = "d:\\"+file_name+".png";
		
		WebPageObject wo = new WebPageObject();
		wo.setHeight(800);
		wo.setWidth(1024);
		wo.setUrl(url1);
		wo.setOutputPath(img_path);
		
		File tmp = File.createTempFile(file_name, ".js");
		File f = TemplateManage.getInstance().getOutPutFile("webpage1.ftl",wo, tmp);*/
		
		PhantomJSFileExecutor ex = new PhantomJSFileExecutor(PhantomJSReference.create().build(), new ExecutionTimeout(90, TimeUnit.SECONDS));
        try {
        	for(String s:urlList){
        		if(StringUtils.isNotEmpty(s)){
        			String file_name = RandomStringUtils.randomAlphabetic(6);
        			String img_path = "d:\\"+file_name+".png";
        			File ff = getWebpagejs(s,file_name,img_path);
        			String r = ex.execute(ff).get();
        			imagePaths.add(img_path);
        			System.out.println("-----"+r);
        			
        			ff.delete();
        		}
        	}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        request.setAttribute("img_path", imagePaths);
        request.getRequestDispatcher("test1.jsp").forward(request, response);
		
	}
	
	public File getWebpagejs(String url,String file_name,String img_path){
		WebPageObject wo = new WebPageObject();
		wo.setHeight(800);
		wo.setWidth(1024);
		wo.setUrl(url);
		wo.setOutputPath(img_path);
		File tmp=null, f = null ;
		try {
			tmp = File.createTempFile(file_name, ".js");
			f = TemplateManage.getInstance().getOutPutFile("webpage1.ftl",wo, tmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f; 
	}

}
