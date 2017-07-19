package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos.seguranca.anotacoes;

import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.enums.RoleEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Privado {
        public RoleEnum role();
}
