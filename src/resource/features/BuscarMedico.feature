# language: pt
Funcionalidade: Pesquisa de médicos
 
  Cenário: Pesquisar médico
    Dado que eu sou um cliente
    Quando eu pesquisar um medico especialista em "Pediatria" 
    E informar o estado "Rio de Janeiro" e a cidade "Nova Iguaçu" 
    Então serao exibidos resultados para o Rio de Janeiro
    Mas nenhum resultado para os demais estados
    
