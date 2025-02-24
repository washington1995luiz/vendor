package br.com.washington.vendor.controllers;

import br.com.washington.vendor.service.VendorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(VendorController.class)
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VendorService vendorService;

    @Test
    void shouldCreateNewVendor(

    ){}
}
