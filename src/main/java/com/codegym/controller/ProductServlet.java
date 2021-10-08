package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.product.IProductService;
import com.codegym.service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                showProductCreateFrom(request, response);
                break;
            }
            case "edit": {
                showProductEditFrom(request, response);
                break;
            }
            case "delete": {
                showProductDeleteFrom(request, response);
                break;
            }
            default: {
                showProductList(request, response);
                break;
            }
        }
    }

    private void showProductDeleteFrom(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showProductEditFrom(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showProductCreateFrom(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showProductList(HttpServletRequest request, HttpServletResponse response) {
        String productName = request.getParameter("q");
        List<Product> products;
        if (productName == null || productName.equals("")) {
            products = productService.getAll();
        } else {
            products = productService.findProductByName(productName);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list.jsp");
        request.setAttribute("products", products);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
