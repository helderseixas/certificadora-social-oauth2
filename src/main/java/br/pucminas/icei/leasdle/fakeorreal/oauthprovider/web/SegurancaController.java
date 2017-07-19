package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.web;

import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.SegurancaAPI;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.enums.RoleEnum;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos.seguranca.SegurancaServico;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos.seguranca.anotacoes.Privado;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos.seguranca.anotacoes.Publico;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.web.util.OAuthBaseController;
import javax.servlet.http.HttpServletRequest;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/seguranca")
public class SegurancaController extends OAuthBaseController{
        
        @Autowired
        private SegurancaServico segurancaServico;
        
        @Publico
        @ResponseBody
        @RequestMapping(value = "/logar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String logar(HttpServletRequest request) {
                OAuthResponse response = segurancaServico.logarOAuth(request);
                return response.getBody();
	}
        
        @Privado(role=RoleEnum.ROLE_GERAL)
        @ResponseBody
        @RequestMapping(value = "/usuario/logado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SegurancaAPI retornarUsuarioLogado(HttpServletRequest request) {
                return segurancaServico.getUsuarioLogado();
	}
}
