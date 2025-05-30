/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.service;


import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaypalService {
    
    @Autowired
    private APIContext apiContext;
    
    public Payment createPayment(
        Double total, 
        String currency,
        String method,
        String intent,
        String descripcion,
        String urlCancel,
        String urlSuccess
    ) throws PayPalRESTException {
        
        // Se define la informacion sobre el monto a procesar
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.US, "%.2f", total));
        
        // Se define la transacción a realizar
        Transaction transaction = new Transaction();
        transaction.setDescription(descripcion);
        transaction.setAmount(amount);
        
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        
        // Se define las opciones del proceso de pago
        Payer payer = new Payer();
        payer.setPaymentMethod(method);
        
        // Se definen opciones dentro de pago
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        
        // Se definen los URL para redirigir los resultados de PayPal
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(urlCancel);
        redirectUrls.setReturnUrl(urlSuccess);  // Se debe cambiar a la URL de éxito
        
        payment.setRedirectUrls(redirectUrls);
        
        return payment.create(apiContext);
    }
    
    public Payment executePayment(String paymentId, String payerId) 
            throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecution);
    }
}
