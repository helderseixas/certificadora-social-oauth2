<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
	<link href="node_modules/material-components-web/dist/material-components-web.css"rel="stylesheet" >
	
	<style>
		body {
			background-color:#C8E6C9;
			display: flex;
			flex-direction:column;
			justify-content:center;
			align-items:center;
		}

		#login_section {
			display: flex;
			justify-content:center;
			align-items:flex-start;
			flex-direction:row-reverse;
			flex-wrap:wrap;
			margin:2em;
		}

		#login {
			order:2;
			display:flex;
			flex-direction:column;
		}
		
		#descricao {
			order:1;
			margin-left:2em;
		}

		.mdc-card {
			background-color:white;
		}
		
		#action_login {
			justify-content:flex-end;
		}

		#google-play-img {
			height: auto;
			width: 200px;
		}
	</style>
<head>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#call').click(function ()
            {                       	
            	var request = $.ajax({
                	method: "POST",
                    dataType: 'json',
                	data: {"username": $('#username').val(), "password": $('#password').val(), "client_id": $('#client_id').val(), "client_secret": $('#client_secret').val(), "grant_type": $('#grant_type').val()},
                    url: "endpoints/seguranca/logar"
                });
            	
            	request.done(function( data ) {            		
            			var dataText = JSON.stringify(data);            			
            			var json = $.parseJSON(dataText);   
            			if(json.access_token != null){            				            				
            				params = {"access_token": json.access_token, "expires_in": json.expires_in};            				
            				//post("http://localhost/processoscoletivos/certificado-social", params);            					
            				post("http://leasdle01.icei.pucminas.br/processoscoletivos/certificado-social", params);
            			}else{
            				$('#output').append(json.error_description).append("<br\>");
            			}
                    });
            	
            	request.fail(function( jqXHR, textStatus ) {
            		$('#output').append(textStatus);
            		});

            });

        });
        
        function post(path, params, method) {
            method = method || "post"; // Set method to post by default if not specified.

            // The rest of this code assumes you are not using a library.
            // It can be made less wordy if you use one.
            var form = document.createElement("form");
            form.setAttribute("method", method);
            form.setAttribute("action", path);

            for(var key in params) {
                if(params.hasOwnProperty(key)) {
                    var hiddenField = document.createElement("input");
                    hiddenField.setAttribute("type", "hidden");
                    hiddenField.setAttribute("name", key);
                    hiddenField.setAttribute("value", params[key]);

                    form.appendChild(hiddenField);
                 }
            }

            document.body.appendChild(form);
            form.submit();
        }
    </script>
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
<title>Certificadora Social</title>
</head>
<body>
		<h1 class="mdc-typography--display2" >Acessar sistema Processos Coletivos</h1>
		
		<br/><br/><br/>
	
		<div id="login_section">
			<div class="mdc-typography--body1" id="descricao">
			
				<p class="mdc-typography--subheading2">
					Para acessar o sistema de Processos Coletivos é necessário ter um certificado social.
				</p>
				
				<p>
					O <spam class="mdc-typography--body2">certificado social</spam> funciona como uma identidade do cidadão na Internet.
					<br/>
					Através dele você pode acessar sistemas que requisitam rigorosa verificação da identidade do usuário.
				</p>
			</div>
	
			<div id="login">
				<div class="mdc-card">
					<section class="mdc-card__primary">
						<h1 class="mdc-card__title mdc-card__title--large">Já tenho certificado social</h1>
					</section>
		
					<section class="mdc-card__supporting-text">
						<div class="mdc-textfield" data-mdc-auto-init="MDCTextfield">
							<input type="text" name="username" id="username" class="mdc-textfield__input" />
							<label for="username" class="mdc-textfield__label">CPF</label>
						</div>
						<br/>
						<div class="mdc-textfield" data-mdc-auto-init="MDCTextfield">
							<input type="password" name="password" id="password" class="mdc-textfield__input" />
							<label for="password" class="mdc-textfield__label">Senha</label>
						</div>
		
						<input type="hidden" name="client_id" id="client_id" value="exemploaplicativocliente">
						<input type="hidden" name="client_secret" id="client_secret" value="9834ba657bb2c60b5bb53de6f4201905">
						<input type="hidden" name="grant_type" id="grant_type" value="password">   
					</section>
			
					<section class="mdc-card__actions" id="action_login">
						<input type="submit" class="mdc-button mdc-button--accent" id="call" value="Entrar" />
					</section>
				</div>
			
				<br/>
			
				<div class="mdc-card">
					<section class="mdc-card__primary">
						<h1 class="mdc-card__title mdc-card__title--large">Não tenho certificado social</h1>
					</section>
		
					<section class="mdc-card__supporting-text">
						Baixe o aplicativo FakeOrReal e faça seu cadastro.   
					</section>
		
					<section class="mdc-card__actions">					
						<a href='https://play.google.com/store/apps/details?id=br.pucminas.fakeorreal&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'>
							<img id="google-play-img" alt='Disponível no Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/pt-br_badge_web_generic.png'/>
						</a>
					</section>
				</div>
			</div>
		</div>
	
		<script src="node_modules/material-components-web/dist/material-components-web.js"></script>
		<script>mdc.autoInit()</script>
</body>
</html>
