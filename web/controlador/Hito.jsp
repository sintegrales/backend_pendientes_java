<%@page import="modelos.HitoParams"%>
<%@page import="modelos.Hito"%>
<%@page import="modelos.Conexion"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>


<%
    response.setHeader("Access-Control-Allow-Origin", "*"); 
    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    response.setContentType("application/json");
    
    // Conexión a la base de datos
    Conexion con = new Conexion();

    String control = request.getParameter("control");
    Hito cate = new Hito(con.conexion);
    Gson gson = new Gson();
    Map<String, Object> vec = new HashMap<>();
    
    switch (control) {
        case "consulta":
            vec.put("resultado", cate.consulta());
            break;
        case "insertar":
            BufferedReader readerInsertar = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String jsonInsertar = readerInsertar.readLine();
            HitoParams paramsInsertar = gson.fromJson(jsonInsertar, HitoParams.class);
            vec.putAll(cate.insertar(paramsInsertar));
            break;
        case "eliminar":
            int idEliminar = Integer.parseInt(request.getParameter("id"));
            vec.putAll(cate.eliminar(idEliminar));
            break;
        case "editar":
            BufferedReader readerEditar = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String jsonEditar = readerEditar.readLine();
            HitoParams paramsEditar = gson.fromJson(jsonEditar, HitoParams.class);
            int idEditar = Integer.parseInt(request.getParameter("id"));
            vec.putAll(cate.editar(idEditar, paramsEditar));
            break;
    }

    String datosj = gson.toJson(vec);
    out.print(datosj);

    
%>
