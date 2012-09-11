/**************************************************************************
	* 	Descricao: Mensagem de confirmação de saída do sistema
	*
	*	Autor: Jefferson de Paula
	***************************************************************************/
	function confirmaSair()
	{
		if(confirm("Deseja realmente sair do sistema?"))
		{
			return true;
		}
		
		return false;
	}
	
	/**************************************************************************
	* 	Descricao: Abre uma nova janela pop up
	*
	*	Autor: Jefferson de Paula
	***************************************************************************/
	function abrirJanela(url, largura, altura) {
		window.open(url,'', 'toolbar=0, location=0, directories=0, status=no, menubar=0, scrollbars=yes, resizable=yes, width=' + largura + ', height=' + altura + ', top=20, left=20');
	}
	
	function abrirMesmaJanela(url) {
		window.location = url;
	}
	
	/**************************************************************************
	* 	Descricao: Desabilita o carregando frota do painel
	*
	*	Autor: Jefferson de Paula
	***************************************************************************/
	function unload() {	
		var objeto = null;
		
		objeto = document.getElementById("load");
		if (objeto != null){
			document.body.removeChild(objeto);
		}
		
		objeto = null;
		
		objeto = document.getElementById("msg_load");
		if (objeto != null){
			document.body.removeChild(objeto);
		}
	}
	
	function confirmaExcluir()
	{
		if(confirm("Deseja realmente excluir esse registro?"))
		{
			return true;
		}
		
		return false;
	}
	
	function confirmaDesativar()
	{
		if(confirm("Deseja realmente desativar esse registro?"))
		{
			return true;
		}
		
		return false;
	}
	
	function confirmaAtivar()
	{
		if(confirm("Deseja realmente ativar esse registro?"))
		{
			return true;
		}
		
		return false;
	}