package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.web.util;

import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.SegurancaAPI;

public class SegurancaAPIThreadLocal {

	private static final ThreadLocal<SegurancaAPI> threadLocal = new ThreadLocal<>();

	public static void setSegurancaAPI(SegurancaAPI segurancaAPI) {
		threadLocal.remove();
		threadLocal.set(segurancaAPI);
	}

	public static SegurancaAPI getSegurancaAPI() {
		return threadLocal.get();
	}
}
