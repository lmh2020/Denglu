package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

public class FileUpload extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse ros)throws ServletException, IOException{

        String path="D:\\单词记忆";
        File file=new File(path);
        if (file.exists()){
            file.mkdir();
        }
        Collection<Part>parts=req.getParts();
        for (Part part:parts){
            part.write(path+File.separator+ UUID.randomUUID().toString()+"_"+part.getSubmittedFileName());
        }
        ros.getWriter().println("ok");
    }

}
