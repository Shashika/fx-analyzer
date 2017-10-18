package com.shashika.analyzer.servlet;

import com.shashika.analyzer.CSVReader;
import com.shashika.common.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by shashika on 10/15/17.
 */

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet{

    private final static Logger LOGGER = Logger.getLogger(FileUploadServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);

        String directoryPath = getServletContext().getRealPath(Constants.FILE_RESOURCE_PATH);
        File directory = new File(directoryPath);

        if(!directory.exists()) {
            directory.mkdirs();
            LOGGER.log(Level.INFO, "File upload directory created");
        }

        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {

                List<FileItem> multiparts = upload.parseRequest(req);

                for (FileItem item : multiparts) {

                    if (!item.isFormField()) {

                        String name = new File(item.getName()).getName();
                        String ext  = FilenameUtils.getExtension(name);

                        if(!name.equals("") && ext.toLowerCase().equals("csv")){

                            boolean fileCheck = new File(directory, name).exists();

                            if(!fileCheck){

                                item.write(new File(directory + File.separator + name));

                                CSVReader csvReader = new CSVReader(directory + File.separator + name, name);
                                csvReader.read(true);
                                req.setAttribute("type", "success");
                                req.setAttribute("message", "Your file has been uploaded successfully!");
                                LOGGER.log(Level.FINE, "File has been uploaded successfully "+ name);
                            }
                            else {
                                req.setAttribute("type", "error");
                                req.setAttribute("message", "File has been already uploaded!");
                                LOGGER.log(Level.INFO, "File duplication, tried with existed file");
                            }
                        }

                        else {
                            req.setAttribute("type", "error");
                            req.setAttribute("message", "No file or unsupported file attached, check again!, We only allow CSV files");
                            LOGGER.log(Level.SEVERE, "File error, No file or format error");
                        }
                    }
                }

            }
            catch (Exception e)
            {
                req.setAttribute("type", "error");
                req.setAttribute("message", "File Upload Failed due to " + e);
                LOGGER.log(Level.SEVERE, "File Upload Failed due to " + e);
            }
        } else
        {
            req.setAttribute("type", "error");
            req.setAttribute("message", "This Servlet only handles file upload request");
            LOGGER.log(Level.SEVERE, "Error with unknown activities, only support file upload");
        }
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }
}
