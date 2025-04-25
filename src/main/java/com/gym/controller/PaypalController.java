package com.gym.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.gym.service.PaypalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/payment")
public class PaypalController {

    private static final Logger log = LoggerFactory.getLogger(PaypalController.class);

    private final PaypalService paypalService;

    @Autowired
    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/facturar")
    public RedirectView createPayment(@RequestParam("total") double total) {
        if (total > 0) {
            try {
                String urlCancel = "http://localhost/payment/cancel";
                String urlSuccess = "http://localhost/payment/success";
                Payment payment = paypalService.createPayment(
                        total,
                        "USD",
                        "paypal",
                        "sale",
                        "Pago de membresía",
                        urlCancel,
                        urlSuccess
                );
                for (Links links : payment.getLinks()) {
                    if (links.getRel().equals("approval_url")) {
                        return new RedirectView(links.getHref());
                    }
                }
            } catch (PayPalRESTException e) {
                log.error("Error en el pago: ", e);
            }
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId,
                                 @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if ("approved".equals(payment.getState())) {
                return "/paypal/pagoSuccess";
            }
        } catch (PayPalRESTException e) {
            log.error("Error en el pago: ", e);
        }
        return "/paypal/pagoError";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "paypal/pagoCancel";
    }

    @GetMapping("/error")
    public String paymentError() {
        return "paypal/pagoError";
    }
}
