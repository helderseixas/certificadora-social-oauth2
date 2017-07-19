package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.excecoes;

import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.SegurancaAPI;


public class TokenCriadoException extends ExemploOAuthException {

        private SegurancaAPI segurancaAPI;
        
        public TokenCriadoException(SegurancaAPI segurancaAPI) {
                super("Token ja criado para este usuario.");
                this.segurancaAPI = segurancaAPI;
        }

        public SegurancaAPI getSegurancaAPI() {
                return segurancaAPI;
        }
}
