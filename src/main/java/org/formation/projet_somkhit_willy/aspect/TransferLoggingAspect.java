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

    // Generic pointcut on any 'transfer' method anywhere in the project packages.
    // This avoids tight coupling to legacy implementation class names and
    // continues to log transfers when the implementation moves to application/usecase.
    @After("execution(* org.formation.projet_somkhit_willy..*.transfer(..))")
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
