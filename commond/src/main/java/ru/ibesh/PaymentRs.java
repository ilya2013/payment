package ru.ibesh;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentRs{
    private String RqUID;
    private PaymentStatus Status;
    private String paymentUID;
}
