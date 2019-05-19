package by.epam.cafe.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize = 1024 * 1024 * 15) // 15MB
@WebServlet(name = "UploadController", urlPatterns = {"/uploadController"})
public class UploadController extends AbstractController {}
