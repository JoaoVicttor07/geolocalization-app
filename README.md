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

<strong>app/build/outputs/apk/</strong>

# 🚀 Instalação

# Clonar repositório
<ol>
  <li>Clone o repositório para o seu ambiente local: git clone https://github.com/seu-usuario/MyApplication2.git
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
