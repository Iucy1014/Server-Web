/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MiServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(miServlet.class.getName());
    private static final String FILE_PATH = "C:\\Users\\Practica\\Documents\\Archi1.txt";

    private void appendToFile(String infoAGrabar) {
        try {
            Files.write(Paths.get(FILE_PATH), (infoAGrabar + System.lineSeparator()).getBytes(), java.nio.file.StandardOpenOption.APPEND, java.nio.file.StandardOpenOption.CREATE);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to file", e);
        }
    }

    private String readFile() {
        StringBuilder content = new StringBuilder();
        try {
            Files.lines(Paths.get(FILE_PATH)).forEach(line -> content.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading from file", e);
        }
        return content.toString();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String infoUsuario = request.getParameter("nombre") + request.getParameter("correo") + request.getParameter("contra");
        appendToFile(infoUsuario);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet miServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet miServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1>Nombre: " + request.getParameter("nombre") + "<br>" + "Correo: " + request.getParameter("correo") + "</h1>");
            out.println("<h1>" + this.readFile() + "</h1>");
            out.println("<h1>Contraseña ;^): *********</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
