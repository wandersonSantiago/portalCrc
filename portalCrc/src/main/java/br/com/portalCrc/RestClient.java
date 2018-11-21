package br.com.portalCrc;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient extends RestTemplate {
	
	private String usuario="admin";
	private String senha = "d033e22ae348aeb5660fc2140aec35850c4da997";
    public RestClient() {
    	
    	HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(60000);
        factory.setConnectTimeout(60000);
        this.setRequestFactory(factory);
    	
    	
    	
    	SSLContextBuilder builder = new SSLContextBuilder();
        try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), new AllowAllHostnameVerifier());
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(null, -1),
					new UsernamePasswordCredentials(usuario, senha));
			HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(
					sslsf).setDefaultCredentialsProvider(credsProvider).build();
			setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
		} catch (NoSuchAlgorithmException | KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
        
    }
}