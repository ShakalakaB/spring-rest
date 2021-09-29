package aldora.spring.springrest.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ClientInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("inside client interceptor");
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);
        response.getHeaders().add("x-foo", "bar");
        return response;
    }
}
