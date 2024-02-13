package com.example.Recipe.controller;

import com.example.Recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.example.Recipe.utils.RecipeUtil.readResourceAsRecipeRequest;
import static com.example.Recipe.utils.RecipeUtil.readResourceAsRecipeResponse;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeControllerTest {


        @Test
        public void testCreateRecipePositiveCase() throws Exception {
                var recipeRequest = readResourceAsRecipeRequest("recipe-request.json");
                var recipeResponse= readResourceAsRecipeResponse("recipe-response.json");
                var recipeService = Mockito.mock(RecipeService.class);
          //      Mockito.when(invoiceService.createInvoice(invoiceRequest)).thenReturn(invoiceResponse);
           //     var subjectUnderTest = new InvoiceController(invoiceService);

           //     var response = subjectUnderTest.createInvoice(invoiceRequest);
          //      assertNotNull(response);

        }
//
//        @Test
//        public void testUpdateInvoicePositiveCase() throws Exception {
//        var invoiceId = UUID.randomUUID();
//        var invoiceResponse= readResourceAsInvoiceResponse("invoice-response.json");
//        var invoiceService = Mockito.mock(InvoiceService.class);
//
//        Mockito.when(invoiceService.updateInvoiceStatus(invoiceId, "VOID")).thenReturn(invoiceResponse);
//        var subjectUnderTest = new InvoiceController(invoiceService);
//
//        var response = subjectUnderTest.updateInvoiceStatus(invoiceId,  "VOID");
//        assertNotNull(response);
//    }
//
//        @Test
//        public void testCreateInvoiceshouldReturnInternalServerError() throws Exception {
//        var invoiceRequest = readResourceAsInvoiceRequest("invoice-request.json");
//        var invoiceService = Mockito.mock(InvoiceService.class);
//        Mockito.when(invoiceService.createInvoice(invoiceRequest)).thenThrow(new RuntimeException("Something went wrong"));
//        var subjectUnderTest = new InvoiceController(invoiceService);
//
//        var message = assertThrows(RuntimeException.class, () -> {
//            subjectUnderTest.createInvoice(invoiceRequest);
//        });
//
//        assertEquals("Something went wrong", message.getMessage());
//    }
//
//        @Test
//        public void testUpdateInvoiceshouldReturnInternalServerError() throws Exception {
//        var invoiceId = UUID.randomUUID();
//        var invoiceService = Mockito.mock(InvoiceService.class);
//        Mockito.when(invoiceService.updateInvoiceStatus(invoiceId, "VOID")).thenThrow(new RuntimeException("Something went wrong"));
//        var subjectUnderTest = new InvoiceController(invoiceService);
//
//        var message = assertThrows(RuntimeException.class, () -> {
//            subjectUnderTest.updateInvoiceStatus(invoiceId, "VOID");
//        });
//
//        assertEquals("Something went wrong", message.getMessage());
//    }
//
//        @Test
//        public void testListInvoicesShouldReturnInternalServerError() {
//        var invoiceService = Mockito.mock(InvoiceService.class);
//        Mockito.when(invoiceService.listInvoices()).thenThrow(new RuntimeException("Something went wrong"));
//        var subjectUnderTest = new InvoiceController(invoiceService);
//
//        var message = assertThrows(RuntimeException.class, () -> {
//            subjectUnderTest.listInvoices("");
//        });
//
//        assertEquals("Something went wrong", message.getMessage());
//
//    }




}
