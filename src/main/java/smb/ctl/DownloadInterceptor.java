package smb.ctl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

/**
 * Spring RestTemplate 下载组件
 */
public class DownloadInterceptor implements ClientHttpRequestInterceptor {

    /**
      * 媒体类型,给个默认值
     */
    private MediaType headerValue = MediaType.ALL;

    /**
     * 获取媒体类型
     * @return 媒体类型
     */
    public MediaType getHeaderValue() {
        return headerValue;
    }

    /**
     * 设置媒体类型
     * @param headerValue 媒体类型
     */
    public void setHeaderValue(MediaType headerValue) {
        this.headerValue = headerValue;
    }

    /**
     * Intercept the given request, and return a response. The given {@link ClientHttpRequestExecution} allows
     * the interceptor to pass on the request and response to the next entity in the chain.
     * <p>
     * <p>A typical implementation of this method would follow the following pattern:
     * <ol>
     * <li>Examine the {@linkplain HttpRequest request} and body</li>
     * <li>Optionally {@linkplain HttpRequestWrapper wrap} the request to filter HTTP attributes.</li>
     * <li>Optionally modify the body of the request.</li>
     * <li><strong>Either</strong>
     * <ul>
     * <li>execute the request using {@link ClientHttpRequestExecution#execute(HttpRequest, byte[])},</li>
     * <strong>or</strong>
     * <li>do not execute the request to block the execution altogether.</li>
     * </ul>
     * <li>Optionally wrap the response to filter HTTP attributes.</li>
     * </ol>
     *
     * @param request   the request, containing method, URI, and headers
     * @param body      the body of the request
     * @param execution the request execution
     * @return the response
     * @throws IOException in case of I/O errors
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        List<MediaType> list = new ArrayList<>();
        list.add(headerValue);
        requestWrapper.getHeaders().setAccept(list);
        return execution.execute(requestWrapper, body);
    }
}
