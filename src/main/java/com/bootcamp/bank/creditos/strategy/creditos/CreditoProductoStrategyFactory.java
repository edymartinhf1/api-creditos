package com.bootcamp.bank.creditos.strategy.creditos;

import com.bootcamp.bank.creditos.model.enums.CreditosTipoTypes;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
@Component
public class CreditoProductoStrategyFactory {
    private Map<CreditosTipoTypes, CreditoProductoStrategy> strategies = new EnumMap<>(CreditosTipoTypes.class);

    public CreditoProductoStrategyFactory() {
        initStrategies();
    }

    public CreditoProductoStrategy getStrategy(CreditosTipoTypes creditosTipoTypes) {
        if (creditosTipoTypes == null || !strategies.containsKey(creditosTipoTypes)) {
            throw new IllegalArgumentException("Invalid " + creditosTipoTypes);
        }
        return strategies.get(creditosTipoTypes);
    }

    private void initStrategies() {
        strategies.put(CreditosTipoTypes.PERSONAL, new CreditoPersonalStrategy());
        strategies.put(CreditosTipoTypes.EMPRESARIAL, new CreditoEmpresarialStrategy());
        strategies.put(CreditosTipoTypes.TARJETA_CREDITO, new CreditoTarjetaCreditoStrategy());
    }
}
