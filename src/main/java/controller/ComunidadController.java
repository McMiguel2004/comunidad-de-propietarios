package controller;

import model.*;
import service.ComunidadService;
import util.FileParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ComunidadController", urlPatterns = {"/procesar-comunidad"})
@MultipartConfig
public class ComunidadController extends HttpServlet {

    private ComunidadService comunidadService = new ComunidadService();
    private FileParser fileParser = new FileParser();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Obtener archivos subidos
            Part comunidadPart = request.getPart("comunidadFile");
            Part gastosPart = request.getPart("gastosFile");
            
            // Procesar archivos
            InputStream comunidadStream = comunidadPart.getInputStream();
            InputStream gastosStream = gastosPart.getInputStream();
            
            Comunidad comunidad = fileParser.parseComunidad(comunidadStream);
            List<Gasto> gastos = fileParser.parseGastos(gastosStream);
            
            comunidad.setGastos(gastos);
            
            // Calcular cuotas
            comunidadService.calcularCuotas(comunidad);
            
            // Guardar en sesión para las vistas
            request.getSession().setAttribute("comunidad", comunidad);
            
            // Redirigir a la vista de resumen
            response.sendRedirect("resumen.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar los archivos: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}