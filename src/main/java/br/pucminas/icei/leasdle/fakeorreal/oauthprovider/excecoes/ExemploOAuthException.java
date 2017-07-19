package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.excecoes;

public class ExemploOAuthException extends RuntimeException {

        public ExemploOAuthException() {
                super();
        }
        
        public ExemploOAuthException(String message) {
                super(message);
        }

        public ExemploOAuthException(Throwable cause) {
                super(cause);
        }
        
}
