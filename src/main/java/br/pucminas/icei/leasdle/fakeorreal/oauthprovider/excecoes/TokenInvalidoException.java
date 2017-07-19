package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.excecoes;

public class TokenInvalidoException extends ExemploOAuthException {

        public TokenInvalidoException(String message) {
                super(message);
        }

        public TokenInvalidoException(Throwable cause) {
                super(cause);
        }
        
}
