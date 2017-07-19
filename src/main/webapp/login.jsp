<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<h1>Acessar sistema Processos Coletivos utilizando seu certificado social</h1>

<br/>
<i>O <b>certificado social</b> funciona como uma identidade do cidadão na Internet. <br/>Através dele você pode acessar sistemas que requisitam rigorosa verificação da identidade do usuário.</i>
<br/><br/>
<h3>Já tenho certificado social</h3>
<div id="output"></div>  
<br/>
CPF:<input type="text" name="username" id="username"/><br/><br/>  
Senha:<input type="password" name="password" id="password"/><br/><br/>  
<input type="submit" value="Entrar" id="call" />	

<input type="hidden" name="client_id" id="client_id" value="exemploaplicativocliente">
<input type="hidden" name="client_secret" id="client_secret" value="9834ba657bb2c60b5bb53de6f4201905">
<input type="hidden" name="grant_type" id="grant_type" value="password">    
  

<br/><br/>
<h3>Não tenho certificado social</h3>
Clique <a href="https://play.google.com/store/apps/details?id=br.pucminas.fakeorreal">aqui</a> para instalar o app e obter seu certificado social.

</body>
</html>