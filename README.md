# Aplicativo de geocalização 

Um aplicativo Android que obtém e exibe a localização do dispositivo em tempo real, mostrando latitude e longitude, e informa o horário da última atualização da localização.

# 📝 Funcionalidades

<ul>
  <li><strong>Exibir Localização Atual:</strong> Mostra a latitude e longitude do dispositivo em tempo real.</li>
  <li><strong>Atualização Automática de Localização:</strong> A cada 10 segundos, o aplicativo atualiza a posição do dispositivo.</li>
  <li><strong>Exibir Última Atualização:</strong> Exibe na tela a última vez que a localização foi atualizada, para facilitar o acompanhamento.</li>
</ul>

# 📱 Pré-requisitos

<ul>
  <li><strong>Android SDK</strong> O aplicativo é desenvolvido em Kotlin com suporte para Android 5.0 (API 21) ou superior.</li>
  <li><strong>Permissões:</strong> O aplicativo requer permissão de localização <strong>(ACCESS_FINE_LOCATION)</strong> para acessar a localização do dispositivo.</li>
  <li><strong>Conexão com o Google Play Services:</strong> Utiliza o FusedLocationProviderClient para obter dados de localização precisos.</li>
</ul>


# 🚀 Instalação

# Clonar repositório
<ol>
  <li>Clone o repositório para o seu ambiente local: git clone https://github.com/JoaoVicttor07/geolocalization-app.git
</li>
  <li>Abra o projeto no Android Studio.</li>
</ol>

# Executar no Android Studio
<ol>
  <li>Conecte um dispositivo Android ou configure um emulador.</li>
  <li>No Android Studio, selecione Run > Run 'app' para instalar o aplicativo no dispositivo.</li>
</ol>

# Gerar APK para Instalação
Para rodar o app de forma independente, você pode gerar um arquivo APK:
<ol>
  <li>No Android Studio, vá para Build > Build Bundle(s) / APK(s) > Build APK(s).</li>
  <li>Encontre o APK em <strong>app/build/outputs/apk/</strong> e transfira-o para o dispositivo.</li>
</ol>

# ⚙️ Estrutura do Código
<strong>MainActivity.kt:</strong> Arquivo principal, responsável por:
<ul>
  <li>Gerenciar a permissão de localização.</li>
  <li>Configurar e iniciar o <strong>FusedLocationProviderClient</strong> para obter a localização.</li>
  <li>Definir o callback de localização, que atualiza a latitude e longitude a cada 10 segundos.</li>
  <li>Exibir a última vez que a localização foi atualizada.</li>
</ul>

<strong>activity_main.xml:</strong> Layout principal, que inclui:
<ul>
  <li><strong>TextView</strong> para exibir a localização atual.</li>
  <li><strong>TextView</strong> para mostrar a última atualização.</li>
</ul>

# 🛠️ Tecnologias ultilizadas
<ul>
  <li><strong>Kotlin:</strong> Linguagem de programação principal.</li>
  <li><strong>Google Play Services - Location Services:</strong> Para obter dados de localização do dispositivo.</li>
  <li><strong>AndroidX:</strong> Biblioteca para manter compatibilidade com versões antigas do Android.</li>
</ul>

# 🛡️ Permissões
O aplicativo solicita a seguinte permissão:
<ul>
  <li><strong>ACCESS_FINE_LOCATION:</strong> Necessária para acessar a localização precisa do dispositivo.
</li>
</ul>
<strong>Observação: A permissão é solicitada durante o primeiro uso. Caso o usuário negue a permissão, o aplicativo exibe uma mensagem de erro.</strong>

# 🕹️ Como Usar o Aplicativo
<ol>
  <li><strong>Permitir Localização:</strong> Ao abrir o app pela primeira vez, você será solicitado a permitir o acesso à sua localização. Conceda a permissão para que o app funcione corretamente.</li>
  <li><strong>Ver Localização Atual:</strong> A localização (latitude e longitude) será exibida na tela principal, com uma atualização automática a cada 10 segundos.</li>
  <li><strong>Ver Última Atualização:</strong> Abaixo das coordenadas, o aplicativo exibe o horário da última atualização de localização.</li>
</ol>

# 📄 Licença
Este projeto é de código aberto e está licenciado sob a licença MIT. Consulte o arquivo <strong>LICENSE</strong> para mais detalhes.
