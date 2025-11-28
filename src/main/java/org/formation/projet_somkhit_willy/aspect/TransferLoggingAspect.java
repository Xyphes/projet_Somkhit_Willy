package org.formation.projet_somkhit_willy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class TransferLoggingAspect {

    private static final org.slf4j.Logger transferLogger =
            org.slf4j.LoggerFactory.getLogger("TRANSFER_LOGGER");

    @After("execution(* org.formation.projet_somkhit_willy.service.impl.AccountServiceImpl.transfer(..))")
    public void logTransfer(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        UUID sourceAccount = (UUID) args[0];
        UUID destinationAccount = (UUID) args[1];
        BigDecimal amount = (BigDecimal) args[2];

        transferLogger.info(
                "Transfer completed - Source: {}, Destination: {}, Amount: {}",
                sourceAccount, destinationAccount, amount
        );
    }
}
