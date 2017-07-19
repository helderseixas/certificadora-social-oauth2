package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.excecoes;

public class UsuarioOuSenhaInvalidaException extends ExemploOAuthException {

        public UsuarioOuSenhaInvalidaException(String message) {
                super(message);
        }

        public UsuarioOuSenhaInvalidaException(Throwable cause) {
                super(cause);
        }
        
}
